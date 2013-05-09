package hex;

import java.awt.image.BufferedImage;

/**
 * A HexCell is an individual cell in a grid
 *
 */
public class HexCell {

	private final int RADIUS = ReactogonConstants.RADIUS;
	private final int WIDTH, HEIGHT, SIDE;
	private final int HEXAGON_SIDES = 6; 
	private final int I_INDEX_IN_ARRAY; // Needed to calculate the offset
	private final int J_INDEX_IN_ARRAY;
	private final int[] OFFSET_X; // Calculate in computeCorners
	private final int[] OFFSET_Y;

	private int[] CORNERS_X = { 0, 0, 0, 0, 0, 0 };
	private int[] CORNERS_Y = { 0, 0, 0, 0, 0, 0 };

	private Tile tile; // Tile that is present in the cell

	private int note;
	private int cornerX;
	private int cornerY;

	public int life;
	
	private boolean state;
	private boolean playNote;
	private boolean hasTile; // ## Maybe not needed could test of if tile ==
								// null ##

	private ImageLoader imageLoader;
	private BufferedImage image; // Will store the current image


	/**
	 * Constructor creates a HexCell
	 * @param imageLoader {@link ImageLoader}
	 * @param i {@link int} position in the grid
	 * @param j {@link int} position in the grid
	 * @param note {@link int} start note
	 */
	public HexCell(ImageLoader imageLoader, int i, int j, int note) {
		this.imageLoader = imageLoader;
		this.I_INDEX_IN_ARRAY = i;
		this.J_INDEX_IN_ARRAY = j;
		WIDTH = 2 * RADIUS;
		SIDE = RADIUS * 3 / 2;
		HEIGHT = (int) ((RADIUS) * Math.sqrt(3));
		int[] x = { RADIUS / 2, SIDE, WIDTH, SIDE, RADIUS / 2, 0 };
		int[] y = { 0, 0, HEIGHT / 2, HEIGHT, HEIGHT, HEIGHT / 2 };
		OFFSET_X = x;
		OFFSET_Y = y;
		setState(false); // So that lights are set OFF
		tile = null; // No tile is present
		setImage(tile, state); // No tile is present upon creation so null is
								// passed
		
		this.note = note;		//no note setyet
		setPlayNote(false);
		life = -1;
		computeCorners();
		cornerX = i * SIDE;
		cornerY = HEIGHT * (2 * j + (i % 2)) / 2;

	}

	/**
	 * Refreshes image to make sure the cell has the correct image loaded
	 */
	public void refreshImage() {
		image = imageLoader.getImage(tile, state);
	}

	/**
	 * Takes a tile and sets the respective image
	 * 
	 * @param tile {@link Tile}
	 *            if null, it will set it as a blank hexagon
	 * @param state {@link boolean}
	 */
	public void setImage(Tile tile, boolean state) {
		image = imageLoader.getImage(tile, state);
	}

	/**
	 * Returns the image for the cell
	 * 
	 * @return {@link BufferedImage}
	 */
	public BufferedImage getImage() {
		return this.image;
	}

	/**
	 * Sets the state of the hexagon, if true it will set the hexagon to light
	 * up, if false it will not
	 * 
	 * @param state {@link boolean}
	 *            true will set the hexagon to be in an ON state
	 */
	public void setState(boolean state) {
		this.state = state;
		image = imageLoader.getImage(tile, state);
	}

	/**
	 * Gets the state of the cell, if TRUE it will light up
	 * 
	 * @return state {@link boolean} a boolean of which state the cell is in (TRUE means light
	 *         will be on and visa versa)
	 */
	public boolean getState() {
		return this.state;
	}

	/**
	 * Flips the state, if it is in the ON state (true) will turn it to the OFF
	 * state (false)
	 */
	public void flipState() {
		state = (state == true ? false : true);
		image = imageLoader.getImage(tile, state);

	}

	/**
	 * Sets the tile that should be placed in the hexagon cell
	 * 
	 * @param tile {@link Tile}
	 */
	public void setTile(Tile tile) {
		this.tile = tile;
		image = imageLoader.getImage(tile, state);
	}

	/**
	 * Gets tile that is in the hexagon
	 * 
	 * @return {@link Tile}
	 */
	public Tile getTile() {
		return this.tile;
	}

	/**
	 * It sets if that cell should play a note
	 * 
	 * @param playNote {@link boolean}
	 */
	public void setPlayNote(boolean playNote) {
		this.playNote = playNote;
	}

	/**
	 * It returns if a hexagon should play a note
	 * 
	 * @return {@link boolean}
	 */
	public boolean getPlayNote() {
		return this.playNote;
	}

	/**
	 * Sets what note should be played by this hexagon cell
	 * 
	 * @param note {@link int}
	 */
	public void setNote(int note) {
		this.note = note;
	}

	/**
	 * Gets what note should be played by this hexagon cell
	 * 
	 * @return {@link int}
	 */
	public int getNote() {
		return this.note;
	}

	/**
	 * Maybe not needed becasue you can test tile == null, but... it sets a
	 * boolean of is that cell has a tile in it
	 * 
	 * @param hasTile {@link boolean}
	 */
	public void setHasTile(boolean hasTile) {
		this.hasTile = hasTile;
	}

	/**
	 * Maybe not needed becasue you can test tile == null, but... returns if the
	 * hexegon has a tile in it
	 * 
	 * @return {@link boolean} true if has a tile
	 */
	public boolean getHasTile() {
		return this.hasTile;
	}

	/**
	 * Gets the co-ordinates of all the corners of a hexagon (these points are
	 * where they will be drawn)
	 * 
	 * @return {@link int[]}
	 */
	public int[] getCornersX() {
		return this.CORNERS_X;
	}

	/**
	 * sets the co-ordinates of all the corners of a hexagon (these points are
	 * where they will be drawn)
	 * 
	 * @return {@link int[]}
	 */
	public int[] getCornersY() {
		return this.CORNERS_Y;
	}

	/**
	 * Computes x and y co-ordinates of the corners of where the hexagon shall
	 * be placed
	 */
	private void computeCorners() {
		for (int i = 0; i < HEXAGON_SIDES; i++) {
			CORNERS_X[i] = I_INDEX_IN_ARRAY + OFFSET_X[i];
			CORNERS_Y[i] = J_INDEX_IN_ARRAY + OFFSET_Y[i];
		}
	}

	/**
	 * get X co-ord of corner
	 * @return {@link int}
	 */
	public int getCornerX() {
		return this.cornerX;
	}

	/**
	 * get Y co-ord of corner
	 * @return {@link int}
	 */
	public int getCornerY() {
		return this.cornerY;
	}

}
