package hex;

import java.awt.Color;
import java.awt.Graphics;
import java.util.*;

import javax.sound.midi.InvalidMidiDataException;

/**
 * A single grid
 */
public class Grid {
	// Final values that will define how the grid will look
	static final int GRID_WIDTH = ReactogonConstants.GRID_WIDTH;
	static final int GRID_HEIGHT = ReactogonConstants.GRID_HEIGHT;
	@SuppressWarnings("unused")
	private final int RADIUS = ReactogonConstants.RADIUS;
	private int startNote = ReactogonConstants.START_NOTE;
	private static final int HEXAGON_SIDES = 6;
	private static final int[] NEIGHBORS_I = { 0, 1, 1, 0, -1, -1 };
	private static final int[][] NEIGHBORS_J = { { -1, -1, 0, 1, 0, -1 },
			{ -1, 0, 1, 1, 1, 0 } };

	// 2D array holding hexagon cells
	private HexCell[][] hexGrid;
	private ImageLoader imageLoader;
	private HashMap<Object, Pulse> hashMap;
	private ArrayList<Tile> warpArray;
	private ArrayList<Object> keyHolder;
	private ArrayList<Object> keyHolderTemp;
	private Iterator<Object> pulseIterator;
	private SoundSequencer soundSequencer;
	private int channel;
	private Node startNode;
	private int startDirection;
	private int tempo;
	private int warpID;
	private Stack<Task> undoStack;
	private Stack<Task> redoStack;
	private boolean modified;
	private boolean play;

	private int[] directions = { 3, 4, 5, 0, 1, 2 };

	/**
	 * Constructor for the grid
	 * 
	 * @param imageLoader
	 *            {@link ImageLoader}
	 * @param ss
	 *            {@link SoundSequencer}
	 * @param channel
	 *            {@link int}
	 */
	public Grid(ImageLoader imageLoader, SoundSequencer ss, int channel) {
		setPlay(true);
		this.imageLoader = imageLoader;
		this.soundSequencer = ss;
		this.channel = channel;
		hexGrid = new HexCell[GRID_HEIGHT + 1][GRID_WIDTH + 1];
		hashMap = new HashMap<Object, Pulse>();
		keyHolder = new ArrayList<Object>();
		keyHolderTemp = new ArrayList<Object>();
		warpArray = new ArrayList<Tile>();
		startNode = null;
		warpID = 0;
		startDirection = 0;
		undoStack = new Stack<Task>();
		redoStack = new Stack<Task>();
		modified = false;
		setTempo(4);
		createGrid();
	}

	public void paint(Graphics g) {
		for (int j = 0; j < GRID_HEIGHT; j++) {
			for (int i = 0; i < GRID_WIDTH; i++) {
				HexCell hc = hexGrid[j][i];
				g.setColor(Color.CYAN);
				g.drawImage(hc.getImage(), hc.getCornerX()
						+ ReactogonConstants.GRID_LOCATION_OFFSET_X,
						hc.getCornerY()
								+ ReactogonConstants.GRID_LOCATION_OFFSET_Y,
						null);

				if (hc.getTile() != null)
					if (hc.getTile().life > -1)
						g.drawString(
								Integer.toString(hc.getTile().life),
								hc.getCornerX()
										+ ReactogonConstants.GRID_LOCATION_OFFSET_X
										+ 45,
								hc.getCornerY()
										+ ReactogonConstants.GRID_LOCATION_OFFSET_Y
										+ 15);

			}
		}
	}

	/**
	 * Creates the grid
	 */
	public void createGrid() {
		int note = startNote;
		for (int j = 0; j < GRID_HEIGHT; j++) {
			note = startNote - (7 * j);
			for (int i = 0; i < GRID_WIDTH; i++) {
				note = (note + 4) - (((i % 2) * 7));
				hexGrid[j][i] = new HexCell(imageLoader, i, j, note);
			}
		}

	}

	/**
	 * Returns the array of hexagons
	 * 
	 * @return 2D Array of {@link HexCell}
	 */
	public HexCell[][] getGrid() {
		return hexGrid;
	}

	/**
	 * Sets the start node
	 */
	public void setStartNode() {
		if (startNode != null) {
			Object key = calculateKey(startNode, startDirection);
			keyHolder.add(key);
			hashMap.put(key, new Pulse(startNode, soundSequencer,
					startDirection));
		}

	}

	/**
	 * Main method to move the pulse one space
	 */
	@SuppressWarnings("unchecked")
	public void play() {
		if (isPlay()) {
			try {
				if (!keyHolder.isEmpty()) {
					flipStates();
					pulseIterator = keyHolder.iterator();
					while (pulseIterator.hasNext()) {
						Pulse p = hashMap.remove(pulseIterator.next());
						if (p != null) {
							Node currentNode = p.getCurrent();
							int direction = p.getDirection();
							HexCell hexCellCurrent = hexGrid[currentNode.getJ()][currentNode
									.getI()];
							Tile t = hexCellCurrent.getTile();
							hexCellCurrent.setState(true);
							if (t == null) {
								p.setNext(getMove(direction, currentNode),
										direction);
							} else {
								if (t.life != -1)
									t.life--;
								String tile = t.getType();
								switch (tile) {
								case "explosionT":
									soundSequencer.queueNote(
											hexCellCurrent.getNote(), channel);
								case "explosionF":
									// for all directions add a new pulse apart
									// from
									// the
									// way it came
									for (int dir = 0; dir < HEXAGON_SIDES; dir++) {
										if (dir != directions[direction]) {
											Node next = getMove(dir,
													currentNode);
											if (next != null) {
												Object key = calculateKey(next,
														dir);
												keyHolderTemp.add(key);
												hashMap.put(
														key,
														new Pulse(getMove(dir,
																currentNode),
																soundSequencer,
																dir));
											}
										}
									}
									p.setNext(null);
									break;
								case "warp":
									Tile currentWarp = hexGrid[p.getCurrent()
											.getJ()][p.getCurrent().getI()]
											.getTile();

									p.setNext(null);
									for (Tile warpTile : warpArray) {
										if (warpTile.getID() != currentWarp
												.getID()) {

											int dir = p.getDirection();
											Node warpNext = getMove(dir,
													warpTile.getNode());
											if (warpNext != null) {
												Object key = calculateKey(
														warpNext, dir);
												keyHolderTemp.add(key);
												hashMap.put(
														key,
														new Pulse(
																getMove(dir,
																		warpTile.getNode()),
																soundSequencer,
																dir));
											}
										}
									}
									break;
								case "stopT":
									soundSequencer.queueNote(
											hexCellCurrent.getNote(), channel);
								case "stopF":
									p.setNext(null);
									break;
								case "changeDirT":
									soundSequencer.queueNote(
											hexCellCurrent.getNote(), channel);
								case "changeDirF":
									p.setNext(
											getMove(t.getDirection(),
													currentNode), t
													.getDirection());
									break;
								case "playT":
									soundSequencer.queueNote(
											hexCellCurrent.getNote(), channel);
								case "playF":
									p.setNext(
											getMove(t.getDirection(),
													currentNode), t
													.getDirection());
									break;
								default:
									p.setNext(null);
								}
								if (t.life == 0) {
									removeTile(currentNode.getI(), currentNode.getJ(), false);
								}
							}
							p.setPrevious();
							p.setCurrent();

							if (p.getCurrent() != null) {
								Object key = calculateKey(p.getCurrent(),
										p.getDirection());
								keyHolderTemp.add(key);
								hashMap.put(key, p);
							}

						}
					}

					keyHolder.clear();
					if (!keyHolderTemp.isEmpty()) {
						keyHolder
								.addAll((Collection<? extends Object>) (keyHolderTemp
										.clone()));
						keyHolderTemp.clear();
					}
					if (keyHolder.size() > 1000) {
						stop();
						System.err
								.println("Wayyyy to many notes, your just trying to break the system now");
					}
					// System.out.println(keyHolder.size());
				} else {
					flipAllOff();
				}
			} catch (Exception e) {
				stop();
				System.err.println("Cant play pulse right now");
			}
		}

	}

	/**
	 * Turns all states to off
	 */
	private void flipAllOff() {
		for (int j = 0; j < ReactogonConstants.GRID_HEIGHT; j++)
			for (int i = 0; i < ReactogonConstants.GRID_WIDTH; i++)
				hexGrid[j][i].setState(false);
	}

	/**
	 * Flips the correct tiles to the on or off state
	 */
	private void flipStates() {
		for (int j = 0; j < ReactogonConstants.GRID_HEIGHT; j++)
			for (int i = 0; i < ReactogonConstants.GRID_WIDTH; i++)
				hexGrid[j][i].setState(false);

		for (Object o : keyHolder) {
			Pulse p = hashMap.get(o);
			if (p != null) {

				hexGrid[p.getCurrent().getJ()][p.getCurrent().getI()]
						.setState(true);

			}
		}
	}

	/**
	 * Calculates the move that a pulse can do returns a node
	 * 
	 * @param direction
	 *            {@link int} the pulse is going
	 * @param n
	 *            {@link Node} current i and j in the form of a node the pulse
	 *            is on
	 * @return Node {@link Node}
	 */
	private Node getMove(int direction, Node n) {
		int j = n.getJ();
		int i = n.getI();

		j += NEIGHBORS_J[i % 2][direction];
		i += NEIGHBORS_I[direction];

		if (i < 0 || j < 0 || i >= GRID_WIDTH || j >= GRID_HEIGHT)
			return null;
		return new Node(i, j);
	}

	/**
	 * Used for placing a tile in the grid, also used to set the start node
	 * 
	 * @param t
	 *            {@link Tile} Tile you want to set
	 * @param i
	 *            {@link int} index in the grid
	 * @param j
	 *            {@link int} index in the grid
	 */
	public void setTile(Tile t, int i, int j) {
		Tile oldTile = null;
		if (hexGrid[j][i].getTile() != null) {
			oldTile = new Tile(hexGrid[j][i].getTile().getType());
			oldTile.setDirection(hexGrid[j][i].getTile().getDirection() - 1);
		}
		modified = true;

		if (i >= 0 && i < ReactogonConstants.GRID_WIDTH && j >= 0
				&& j < ReactogonConstants.GRID_HEIGHT) {
			undoStack.push(new Task(0, new Node(i, j), oldTile, t));
			removeTile(i, j, false);
			hexGrid[j][i].setTile(t);
			if (t != null) {
				if (t.getType().contains("play")) {
					startNode = new Node(i, j);
					startDirection = t.getDirection();
				}
				if (t.getType().contains("warp")) {
					t.setNode(new Node(i, j));
					t.setID(warpID++);
					addWarp(t);
				}
			}
		}

	}

	/**
	 * Calculates key for the hashmap
	 * 
	 * @param n
	 *            {@link Node}
	 * @param direction
	 *            {@link int}
	 * @return {@link int} key
	 */
	public int calculateKey(Node n, int direction) {
		return (n.getI() * 36) + (n.getJ() * 6) + direction;
	}

	/**
	 * Returns the tile in the given i and j in a grid
	 * 
	 * @param i
	 *            {@link int}
	 * @param j
	 *            {@link int}
	 * @return {@link Tile} the tile in the given i and j
	 */
	public Tile getTile(int i, int j) {
		return hexGrid[j][i].getTile();
	}

	/**
	 * Returns a {@link HexCell} in the given i and j
	 * 
	 * @param i
	 *            {@link int}
	 * @param j
	 *            {@link int}
	 * @return {@link HexCell}
	 */
	public HexCell getHexCell(int i, int j) {
		return hexGrid[i][j];
	}

	/**
	 * Clears the grid
	 */
	public void clear() {
		for (int j = 0; j < GRID_HEIGHT; j++)
			for (int i = 0; i < GRID_WIDTH; i++)
				removeTile(i, j, true);
		startNode = null;
	}

	/**
	 * Clears all arrays
	 */
	public void stop() {
		keyHolderTemp.clear();
		keyHolder.clear();
		hashMap.clear();
		flipAllOff();
	}

	/**
	 * Play a note at a given i and j in a grid
	 * 
	 * @param index_i
	 *            {@link int}
	 * @param index_j
	 *            {@link int}
	 */
	public void playNote(int index_i, int index_j) {
		try {
			soundSequencer.playNote(hexGrid[index_j][index_i].getNote(),
					channel);
		} catch (InvalidMidiDataException e) {
			System.err.println("Unable to play that note?");
			e.printStackTrace();
		}
	}

	/**
	 * Gets the tempo of a grid
	 * 
	 * @return {@link int}
	 */
	public int getTempo() {
		return tempo;
	}

	/**
	 * Remove a tile from a given i and j in the grid
	 * 
	 * @param i
	 *            {@link int}
	 * @param j
	 *            {@link int}
	 * @param b
	 *            {@link Boolean} If the add to the undo stack or not
	 */
	public void removeTile(int i, int j, boolean b) {
		modified = true;
		Tile tile = null;
		if (hexGrid[j][i].getTile() != null) {
			tile = new Tile(hexGrid[j][i].getTile().getType());
			tile.setDirection(hexGrid[j][i].getTile().getDirection());
		}
		Tile t = hexGrid[j][i].getTile();
		hexGrid[j][i].setTile(null);
		if (t != null)
			if (t.getType().contains("warp"))
				removeWarp(t);
		if (b)
			undoStack.push(new Task(1, new Node(i, j), t, null));
	}

	/**
	 * Sets the tempo of a grid
	 * 
	 * @param tempo
	 *            {@link int}
	 */
	public void setTempo(int tempo) {
		this.tempo = tempo;
	}

	/**
	 * Add a warp tile to warp array
	 * 
	 * @param t
	 *            {@link Tile}
	 */
	public void addWarp(Tile t) {
		warpArray.add(t);
	}

	/**
	 * Remove warp tile from warp array
	 * 
	 * @param t
	 *            {@link Tile}
	 */
	public void removeWarp(Tile t) {
		warpArray.remove(t);
	}

	/**
	 * Get start note of the grid, will be a MIDI number
	 * 
	 * @return {@link int}
	 */
	public int getStartNote() {
		return startNote;
	}

	/**
	 * Starts start note for a grid
	 * 
	 * @param startNote
	 *            {@link int} MIDI note
	 */
	public void setStartNote(int startNote) {
		this.startNote = startNote;
		int note = startNote;
		for (int j = 0; j < GRID_HEIGHT; j++) {
			note = startNote - (7 * j);
			for (int i = 0; i < GRID_WIDTH; i++) {
				note = (note + 4) - (((i % 2) * 7));
				hexGrid[j][i].setNote(note);
			}
		}
	}

	/**
	 * Undo a step
	 */
	public void undo() {
		if (!undoStack.isEmpty()) {
			modified = false;
			Task t = undoStack.pop();
			Tile oldTile;
			int type = t.getType();
			Tile tile = t.getTile();
			Tile newTile = t.getNewTile();
			Node n = t.getNode();

			switch (type) {
			case ReactogonConstants.PLACE_TILE:
				oldTile = hexGrid[n.getJ()][n.getI()].getTile();
				hexGrid[n.getJ()][n.getI()].setTile(tile);
				if (newTile != null)
					if (newTile.getType().contains("warp")) {
						removeWarp(newTile);
					}
				redoStack.push(new Task(ReactogonConstants.REMOVE_TILE, n,
						oldTile, newTile));
				break;
			case ReactogonConstants.REMOVE_TILE:
				oldTile = hexGrid[n.getJ()][n.getI()].getTile();
				hexGrid[n.getJ()][n.getI()].setTile(tile);
				if (tile != null) {
					if (tile.getType().contains("play")) {
						startNode = new Node(n.getI(), n.getJ());
						startDirection = tile.getDirection();
					}
					if (tile.getType().contains("warp")) {
						tile.setNode(new Node(n.getI(), n.getJ()));
						tile.setID(warpID++);
						addWarp(tile);
					}
				}
				redoStack.push(new Task(ReactogonConstants.PLACE_TILE, n,
						oldTile, tile));
				break;
			default:
				System.out.println("Duno how you get here");
			}
		}
	}

	/**
	 * Redo a step
	 */
	public void redo() {
		if (modified)
			redoStack.clear();
		if (!redoStack.isEmpty()) {
			Tile oldTile;
			Task t = redoStack.pop();
			int type = t.getType();
			Tile tile = t.getTile();
			Tile newTile = t.getNewTile();
			Node n = t.getNode();

			switch (type) {
			case ReactogonConstants.PLACE_TILE:
				oldTile = hexGrid[n.getJ()][n.getI()].getTile();
				hexGrid[n.getJ()][n.getI()].setTile(tile);
				if (newTile != null)
					if (newTile.getType().contains("warp")) {
						removeWarp(newTile);
					}
				undoStack.push(new Task(ReactogonConstants.REMOVE_TILE, n,
						newTile, oldTile));
				break;
			case ReactogonConstants.REMOVE_TILE:
				oldTile = hexGrid[n.getJ()][n.getI()].getTile();
				hexGrid[n.getJ()][n.getI()].setTile(tile);
				if (tile != null) {
					if (tile.getType().contains("play")) {
						startNode = new Node(n.getI(), n.getJ());
						startDirection = tile.getDirection();
					}
					if (tile.getType().contains("warp")) {
						tile.setNode(new Node(n.getI(), n.getJ()));
						tile.setID(warpID++);
						addWarp(tile);
					}
				}
				undoStack.push(new Task(ReactogonConstants.PLACE_TILE, n,
						oldTile, tile));
				break;
			default:
				System.out.println("Duno how you get here");
			}
		}
	}

	/**
	 * Is the grid playing
	 * 
	 * @return {@link boolean} True if the grid is playing
	 */
	public boolean isPlay() {
		return play;
	}

	/**
	 * Sets the grid to play
	 * 
	 * @param play
	 *            {@link boolean} True to start the grid
	 */
	public void setPlay(boolean play) {
		this.play = play;
	}

	/**
	 * Sets the volume of the notes in that grid
	 * 
	 * @param velocity
	 *            {@link int}
	 */
	public void setVelocity(int velocity) {
		soundSequencer.setVelocity(velocity);
	}

	/**
	 * Gets the volume of the grid
	 * 
	 * @return {@link int}
	 */
	public int getVelocity() {
		return soundSequencer.getVelocity();
	}

	/**
	 * Note length for that grid
	 * 
	 * @param noteLength
	 *            {@link int }
	 */
	public void setNoteLength(int noteLength) {
		soundSequencer.setNoteLength(noteLength);
	}

	/**
	 * Get the note length of the grid
	 * 
	 * @return {@link int}
	 */
	public int getNoteLength() {
		return soundSequencer.getNoteLength();
	}

}