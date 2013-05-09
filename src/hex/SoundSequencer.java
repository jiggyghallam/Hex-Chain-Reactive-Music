package hex;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import javax.sound.midi.*;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Sound sequencer can create tracks
 * 
 */
public class SoundSequencer {

	private Sequencer sequencer;
	private Sequence sequence;
	private Track track;
	@SuppressWarnings("unused")
	private ArrayList<Track> trackArray;
	@SuppressWarnings("unused")
	private int currentTrack;
	private int velocity;
	private int noteLength;
	private int[] instrumentArray = { 0, 0, 0, 0, 0, 0 };

	/**
	 * Default constructor
	 */
	public SoundSequencer() {
	}

	/**
	 * Sound sequencer, takes velocity (volume) and the length of each note
	 * 
	 * @param velocity
	 *            {@link int}
	 * @param noteLength
	 *            {@link int}
	 */
	public SoundSequencer(int velocity, int noteLength) {
		this.velocity = velocity;
		this.noteLength = noteLength;
		trackArray = new ArrayList<Track>();
		currentTrack = 0;
		final JFXPanel fxPanel = new JFXPanel();
	}

	/**
	 * Sets up the sequencer with the default output
	 * 
	 * @throws InvalidMidiDataException
	 * @throws MidiUnavailableException
	 */
	public void setUpSequencer() throws InvalidMidiDataException,
			MidiUnavailableException {
		sequencer = MidiSystem.getSequencer();
		sequencer.open();
		sequence = new Sequence(Sequence.PPQ, 1);
		//createMp3Player();

	}
	/**
	 * Creates an mp3 player and adds in a mp3 from file
	 */
	private void createMp3Player() {
		MediaPlayer mediaPlayer;
		File f  = new File("res/mp3/test.mp3");
		String mp3 = f.toURI().toString();
		Media song = new Media(mp3);
		mediaPlayer = new MediaPlayer(song);
		mediaPlayer.play();
	}

	/**
	 * Plays the current track
	 * 
	 * @throws InvalidMidiDataException
	 */
	public void playTrack() throws InvalidMidiDataException {
		// new PlayNote(sequencer, sequence).start();
		PlayNote thread = new PlayNote(sequencer, sequence);
		thread.setPriority(Thread.MAX_PRIORITY);
		thread.start();
	}

	/**
	 * Plays a sequence
	 * 
	 * @param sequence
	 *            {@link Sequence}
	 * @throws InvalidMidiDataException
	 */
	public void playTrack(Sequence sequence) throws InvalidMidiDataException {
		PlayNote thread = new PlayNote(sequencer, sequence);
		thread.setPriority(Thread.MAX_PRIORITY);
		thread.start();
	}

	/**
	 * Creates a track with a spesified note and sends it to a channels
	 * 
	 * @param note
	 *            {@link int} MIDI notes
	 * @param channel
	 *            {@link int} MIDI channel
	 * @throws InvalidMidiDataException
	 */
	public void playNote(int note, int channel) throws InvalidMidiDataException {
		Sequence sequence = new Sequence(Sequence.PPQ, 1);
		Track track = sequence.createTrack();
		track.add(addInstrumentToTrack(instrumentArray[channel], channel));
		track.add(createNoteOnEvent(note, -1, channel));
		track.add(createNoteOffEvent(note, noteLength, channel));
		sequencer.setSequence(sequence);
		sequencer.start();
	}

	/**
	 * Creates a new track
	 */
	public void createTrack() {
		sequence.deleteTrack(track);
		track = sequence.createTrack();

	}

	/**
	 * Opens Sequencer
	 * 
	 * @throws MidiUnavailableException
	 */
	public void open() throws MidiUnavailableException {
		sequencer.open();
	}

	/**
	 * Closes sequencer *DON'T USE* Unless you know what you want to do with
	 * this
	 */
	public void close() {
		sequencer.close();
	}

	/**
	 * Sets instrument to a channel
	 * 
	 * @param instrument
	 *            {@link int}
	 * @param channel
	 *            {@link int}
	 */
	public void setInstrument(int instrument, int channel) {
		instrumentArray[channel] = instrument;
	}

	/**
	 * Adds instrument to a channel
	 * 
	 * @param instrument
	 *            {@link int}
	 * @param channel
	 *            {@link int}
	 * @return {@link MidiEvent}
	 */
	private MidiEvent addInstrumentToTrack(int instrument, int channel) {
		try {
			ShortMessage mm = new ShortMessage();
			mm.setMessage(ShortMessage.PROGRAM_CHANGE, channel, instrument, 0);
			MidiEvent me = new MidiEvent(mm, -1);
			return me;
		} catch (InvalidMidiDataException e) {
			System.err.println("Unable to set instrument " + instrument
					+ " on channel " + channel);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Play Note on a Channel
	 * 
	 * @param note
	 *            {@link int}
	 * @param channel
	 *            {@link int}
	 */
	public void queueNote(int note, int channel) {
		// System.out.println("Note length = " + noteLength + " Note = " + note
		// + " track length before " + track.size() + " channel " + channel);
		track.add(addInstrumentToTrack(instrumentArray[channel], channel));
		track.add(createNoteOnEvent(note, -1, channel));
		track.add(createNoteOffEvent(note, noteLength, channel));
		// System.out.println("Track length after " + track.size() +
		// " Velocity " + velocity);
	}

	/**
	 * Creates a MIDI Note on MIDIEvent
	 * 
	 * @param nKey
	 *            {@link int} note
	 * @param lTick
	 *            {@link int} when to play it
	 * @param channel
	 *            {@link int }
	 * @return {@link MidiEvent}
	 */
	private MidiEvent createNoteOnEvent(int nKey, long lTick, int channel) {
		return createNoteEvent(ShortMessage.NOTE_ON, nKey, velocity, lTick,
				channel);
	}

	/**
	 * Creates note off event
	 * 
	 * @param nKey
	 *            {@link int}
	 * @param lTick
	 *            {@link int} : When the note will be turned off
	 * @param channel
	 *            {@link int}
	 * @return {@link MidiEvent}
	 */
	private MidiEvent createNoteOffEvent(int nKey, long lTick, int channel) {
		return createNoteEvent(ShortMessage.NOTE_OFF, nKey, 0, lTick, channel);
	}

	/**
	 * Creates the MIDI event
	 * 
	 * @param nCommand
	 *            {@link int} : Short message
	 * @param nKey
	 *            {@link int} note to play
	 * @param nVelocity
	 *            {@link int} volume
	 * @param lTick
	 *            {@link int} length
	 * @param channel
	 *            {@link int}
	 * @return {@link MidiEvent}
	 */
	private MidiEvent createNoteEvent(int nCommand, int nKey, int nVelocity,
			long lTick, int channel) {
		ShortMessage message = new ShortMessage();
		try {
			message.setMessage(nCommand, channel, nKey, nVelocity);
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
			System.exit(1);
		}
		MidiEvent event = new MidiEvent(message, lTick);
		return event;
	}

	/**
	 * Set velocity
	 * 
	 * @param velocity
	 *            {@link int}
	 */
	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	/**
	 * Get velocity
	 * 
	 * @return velocity {@link int}
	 */
	public int getVelocity() {
		return velocity;
	}

	/**
	 * Set the note length
	 * 
	 * @param noteLength
	 *            {@link int}
	 */
	public void setNoteLength(int noteLength) {
		this.noteLength = noteLength;
	}

	/**
	 * Gets note length
	 * 
	 * @return note length {@link int}
	 */
	public int getNoteLength() {
		return noteLength;
	}

	/**
	 * Get instrument for a specified channel
	 * 
	 * @param channel
	 *            {@link int}
	 * @return instrument {@link int}
	 */
	public int getInstrument(int channel) {
		return instrumentArray[channel];
	}

}
