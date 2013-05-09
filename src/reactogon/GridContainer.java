package reactogon;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

/**
 * Grid container holding all grids
 *
 */
public class GridContainer {

	private static final int NUMBER_OF_GRIDS = 6;
	private ImageLoader imageLoader;
	private SoundSequencer soundSequencer;
	private int currentActiveGrid;
	@SuppressWarnings("unused")
	private Constraint constraintBox;
	private int tempoControl;

	private ArrayList<Grid> gridArray;

	/**
	 * Constructor 
	 * @param imageLoader {@link ImageLoader}
	 * @throws InvalidMidiDataException
	 * @throws MidiUnavailableException
	 */
	public GridContainer(ImageLoader imageLoader)
			throws InvalidMidiDataException, MidiUnavailableException {
		soundSequencer = new SoundSequencer(120, 2);
		soundSequencer.setUpSequencer();
		gridArray = new ArrayList<Grid>();
		this.imageLoader = imageLoader;
		currentActiveGrid = 0;
		tempoControl = 1;
	}

	/**
	 * Creates all of the grids and adds them to an array list
	 */
	public void createGrids() {
		for (int i = 0; i < NUMBER_OF_GRIDS; i++) {
			gridArray.add(new Grid(imageLoader, soundSequencer, i));
		}
	}
	
	/**
	 * Gets all the grids
	 * @return {@link ArrayList} {@link Grid}
	 */
	public ArrayList<Grid> getGrids() {
		return gridArray;
	}
	
	/**
	 * Gets a specific grid
	 * @param grid {@link int} ID of grid
	 * @return {@link Grid}
	 */
	public Grid getSpecificGrid(int grid){
		return gridArray.get(grid);
	}

	/**
	 * Calls the paint function of other classes
	 */
	public void paint(Graphics g) {
		g.drawImage(imageLoader.getImage("background"), 0, 0,null);
		gridArray.get(currentActiveGrid).paint(g);


	}

	/**
	 * Get currently active grid
	 * @return {@link int} ID of currently selected grid
	 */
	public int getCurrentActiveGrid() {
		return currentActiveGrid;
	}

	/**
	 * Puts a tile t in position i and j 
	 * @param t {@link Tile}
	 * @param i {@link int}
	 * @param j {@link int}
	 */
	public void putTileInActiveGrid(Tile t, int i, int j) {
		gridArray.get(currentActiveGrid).setTile(t, i, j);
	}

	/**
	 * Remove a tile in position i and j
	 * @param i {@link int}
	 * @param j {@link int}
	 */
	public void removeTileInActiveGrid(int i, int j) {
		gridArray.get(currentActiveGrid).removeTile(i, j, true);
	}

	/**
	 * Gets a tile from an active grid
	 * @param i {@link int}
	 * @param j {@link int}
	 * @return {@link Tile}
	 */
	public Tile getTileInActiveGrid(int i, int j) {
		return gridArray.get(currentActiveGrid).getTile(i, j);
	}

	/**
	 * Sets the current active grid 
	 * @param currentActiveGrid {@link int}
	 */
	public void setCurrentActiveGrid(int currentActiveGrid) {
		this.currentActiveGrid = currentActiveGrid;
	}

	/**
	 * Sets start nodes on the grids
	 */
	public void setStartNodes() {
		for (Grid g : gridArray) {
			g.setStartNode();
		}
	}

	/**
	 * Plays one tick on all grids in the grid array
	 * @throws InvalidMidiDataException
	 */
	public void play() throws InvalidMidiDataException {
		soundSequencer.createTrack();
		if (tempoControl > 16)
			tempoControl = 1;
		if (tempoControl % gridArray.get(currentActiveGrid).getTempo() == 0) {
			gridArray.get(currentActiveGrid).play();
		}
		for (int i = 0; i < NUMBER_OF_GRIDS; i++) {
			if (i != currentActiveGrid) {
				if (tempoControl % gridArray.get(i).getTempo() == 0) {
					gridArray.get(i).play();
				}
			}
		}
		tempoControl++;
		soundSequencer.playTrack();

	}

	/**
	 * Clears the currently active grid
	 */
	public void clearGrid() {
		gridArray.get(currentActiveGrid).clear();
	}

	/**
	 * Stops all grids
	 */
	public void stop() {
		for (int i = 0; i < NUMBER_OF_GRIDS; i++)
			gridArray.get(i).stop();
		tempoControl = 1;
		soundSequencer.createTrack();
		try {
			soundSequencer.playTrack();
		} catch (InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Play a note in a specific i and j
	 * @param index_i {@link int}
	 * @param index_j {@link int}
	 */
	public void playNote(int index_i, int index_j) {
		gridArray.get(currentActiveGrid).playNote(index_i, index_j);
	}

	/**
	 * Sets tempo for the current active grid
	 * @param speed {@link int} must be 1, 2, 4, 8 or 16
	 */
	public void setTempo(int speed) {
		gridArray.get(currentActiveGrid).setTempo(speed);
	}
	
	/**
	 * Sets tempo for the a specific grid
	 * @param speed {@link int} must be 1, 2, 4, 8 or 16
	 * @param grid {@link int} grid ID
	 */
	public void setTempoSpecificGrid(int speed, int grid){
		gridArray.get(grid).setTempo(speed);
	}

	/**
	 * Get tempo of current active grid
	 * @return {@link String}
	 */
	public String getTempo() {
		return "speed" + gridArray.get(currentActiveGrid).getTempo();
	}

	/**
	 * Set start note of a grid
	 * @param grid {@link int}
	 * @param note {@link int}
	 */
	public void setStartNote(int grid, int note) {
		if (grid >= 0 && grid < 6)
			gridArray.get(grid).setStartNote(note);
	}
	
	/**
	 * Get instrument for a given grid
	 * @param grid {@link int} 
	 * @return {@link int}
	 */
	public int getInstrument(int grid){
		return soundSequencer.getInstrument(grid);
	}

	/**
	 * Set instrument on a specific grid
	 * @param grid {@link int}
	 * @param instrument {@link int}
	 */
	public void setInstrument(int grid, int instrument){
		if (grid >= 0 && grid < 6)
			soundSequencer.setInstrument(instrument, grid);
	}

	/**
	 * Undo one step in active grid
	 */
	public void undoActionInActiveGrid() {
		gridArray.get(currentActiveGrid).undo();
		
	}

	/**
	 * Redo one step in active grid
	 */
	public void redoActionInActiveGrid() {
		gridArray.get(currentActiveGrid).redo();		
	}

}
