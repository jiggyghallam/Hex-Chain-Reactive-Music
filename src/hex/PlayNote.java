package hex;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

/**
 * Thread that plays a note
 *
 */
public class PlayNote extends Thread {
	
	private Sequencer sequencer;
	private Sequence sequence;
	
	/**
	 * Play sequence on the sequencer
	 * @param s {@link Sequencer}
	 * @param se {@link Sequence}
	 * @throws InvalidMidiDataException
	 */
	public PlayNote(Sequencer s, Sequence se) throws InvalidMidiDataException {
		this.sequence = se;
		this.sequencer = s;
	}
	
	/**
	 * Starts the thread
	 */
	public void run() {
		try {
			sequencer.setSequence(sequence);
			sequencer.start();
		} catch (InvalidMidiDataException e) {
			System.err.println("Unable to play sequence");
			e.printStackTrace();
		}
	}

}
