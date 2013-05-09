package reactogon;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 * Preferences of each grid
 *
 */
public class GridPreference extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JSlider volumeSlider, noteLengthSlider, startNoteSlider;
	private JComboBox<String> instrumentComboBox;
	private JButton ok;
	private GridContainer gc;
	private int grid;

	
	/**
	 * Takes a gridcontainer and a grid to get/set values
	 * 
	 * @param gc {@link GridContainer}
	 * @param grid {@link int}
	 */
	public GridPreference(GridContainer gc, int grid) {
		this.gc = gc;
		this.grid = grid;
		drawGUI();
		setVolume();
		setNoteLength();
		setStartNote();
		setInstrument();
	}
	
	
	/**
	 * Draws the preference interface for each individual grid 
	 */
	private void drawGUI() {

		volumeSlider = new JSlider();
		volumeSlider.setBorder(BorderFactory.createTitledBorder("Volume"));
		volumeSlider.setMajorTickSpacing(20);
		volumeSlider.setMinorTickSpacing(5);
		volumeSlider.setPaintTicks(true);
		add(volumeSlider, BorderLayout.WEST);

		noteLengthSlider = new JSlider(JSlider.HORIZONTAL, 0, 20, 5);
		noteLengthSlider.setBorder(BorderFactory
				.createTitledBorder("Note Length"));
		noteLengthSlider.setMajorTickSpacing(5);
		noteLengthSlider.setMinorTickSpacing(1);
		noteLengthSlider.setPaintTicks(true);
		noteLengthSlider.setPaintLabels(true);
		add(noteLengthSlider, BorderLayout.WEST);

		startNoteSlider = new JSlider(JSlider.HORIZONTAL, 60, 120, 80);
		startNoteSlider.setBorder(BorderFactory
				.createTitledBorder("Select Which Note to Start"));
		startNoteSlider.setMajorTickSpacing(20);
		startNoteSlider.setMinorTickSpacing(5);
		startNoteSlider.setPaintTicks(true);
		startNoteSlider.setPaintLabels(true);
		add(startNoteSlider, BorderLayout.EAST);

		String[] instrumentNames = {"Piano 1                     ",
				"Piano 2", "Piano 3", "Honky-tonk", "E.Piano 1", "E.Piano 2",
				"Harpsichord", "Clav", "Celesta", "Glockenspiel", "Music Box",
				"Vibraphone", "Marimba", "Xylophone", "Tubular-bell", "Santur",
				"Organ 1", "Organ 2", "Organ 3", "Church Org.1", "Reed Organ",
				"Accordion Fr", "Harmonica", "Bandoneon", "Nylon-str.Gt",
				"Steel-str.Gt", "Jazz Gt.", "Clean Gt.", "Muted Gt.",
				"Overdrive Gt", "DistortionGt", "Gt.Harmonics", "Acoustic Bs.",
				"FingeredBs.", "Picked Bs.", "Fretless Bs.", "Slap Bass 1",
				"Slap Bass 2", "Synth Bass1", "Synth Bass 2", "Violin",
				"Viola", "Cello", "Contrabass", "Tremolo Str", "PizzicatoStr",
				"Harp", "Timpani", "Strings", "Slow Strings", "Syn.Strings1",
				"Syn.Strings2", "Choir Aahs", "Voice Oohs", "SynVox",
				"OrchestraHit", "Trumpet", "Trombone", "Tuba", "MutedTrumpet",
				"French Horns", "Brass 1", "Synth Brass1", "Synth Brass2",
				"Soprano Sax", "Alto Sax", "Tenor Sax", "Baritone Sax", "Oboe",
				"English Horn", "Bassoon", "Clarinet", "Piccolo", "Flute",
				"Recorder", "Pan Flute", "Bottle Blow", "Shakuhachi",
				"Whistle", "Ocarina", "Square Wave", "Saw Wave",
				"Syn.Calliope", "Chiffer Lead", "Charang", "Solo Vox",
				"5th Saw", "Bass & Lead", "Fantasia", "Warm Pad", "Polysynth",
				"Space Voice", "Bowed Glass", "Metal Pad", "HaloPad",
				"Sweep Pad", "Ice Rain", "Soundtrack", "Crystal", "Atmosphere",
				"Brightness", "Goblin", "Echo Drops", "StarTheme", "Sitar",
				"Banjo", "Shamisen", "Koto", "Kalimba", "Bagpipe", "Fiddle",
				"Tinkle", "Agogo", "Steel Drums", "Woodblock", "Taiko",
				"Melo. Tom 1", "Synth Drum", "Reverse Cym.", "Gt.FretNoise",
				"Breath Noise", "Seashore", "Bird", "Telephone 1",
				"Helicopter", "Applause", "Gun Shot" };
		instrumentComboBox = new JComboBox<String>(instrumentNames);
		instrumentComboBox.setBorder(BorderFactory
				.createTitledBorder("Select an Instrument"));
		add(instrumentComboBox, BorderLayout.EAST);

		setVisible(true);

		ok = new JButton("Set for grid " + (grid+1));
		add(ok, BorderLayout.PAGE_END);

		ok.addActionListener(this);

	}

	
	/**
	 * gets the volume of the selected grid preference
	 * @return volume {@link int}
	 */
	public int getVolume() {
		return volumeSlider.getValue();
	}

	/**
	 * gets the note length of the selected grid preference
	 * @return noteLength {@link int}
	 */
	public int getNoteLength() {
		return noteLengthSlider.getValue();
	}

	/**
	 * gets the start note of the selected grid preference
	 * @return startNote {@link int}
	 */
	public int getStartNote() {
		return startNoteSlider.getValue();
	}

	/**
	 * gets the instrument of the selected grid preference
	 * @return instrument {@link int}
	 */
	public int getInstrument() {
		return instrumentComboBox.getSelectedIndex();
	}

	/**
	 * gets the current grids volume
	 */
	public void setVolume() {
		int volume = gc.getSpecificGrid(grid).getVelocity();
		volumeSlider.setValue(volume);
	}

	/**
	 * gets the current grids note length
	 */
	public void setNoteLength() {
		int noteLength = gc.getSpecificGrid(grid).getNoteLength();
		noteLengthSlider.setValue(noteLength);
	}

	/**
	 * gets the current grids start note
	 */
	public void setStartNote() {
		int startNote = gc.getSpecificGrid(grid).getStartNote();
		startNoteSlider.setValue(startNote);
	}

	/**
	 * gets the current grids intrument
	 */
	public void setInstrument() {
		int instrument = gc.getInstrument(grid);
		instrumentComboBox.setSelectedIndex(instrument);
	}

	/**
	 * Status of the ok JButton
	 * @return ok {@link JButton}
	 */
	public JButton getOk() {
		return ok;
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == getOk()) {
			gc.getSpecificGrid(grid).setVelocity(getVolume());
			gc.setStartNote(grid, getStartNote());
			gc.getSpecificGrid(grid).setNoteLength(getNoteLength());
			gc.setInstrument(grid, getInstrument());
		}

	}

}
