package hex;

import java.util.ArrayList;
/**
 * Handles all mouse events
 *
 */
public class MouseHandler {
	@SuppressWarnings("unused")
	private static final int SCREEN_RES_X = 1280;
	@SuppressWarnings("unused")
	private static final int SCREEN_RES_y = 720;
	private static final int START_STATE = 0;
	private final int RADIUS = ReactogonConstants.RADIUS;
	@SuppressWarnings("unused")
	private final int WIDTH, SIDE, HEIGHT;

	private int cellIndex_i;
	private int cellIndex_j;
	private int state;
	private int x;
	private int y;
	private Tile currentTileSelected;
	@SuppressWarnings("unused")
	private Grid grid;
	@SuppressWarnings("unused")
	private boolean rotate;
	@SuppressWarnings("unused")
	private Constraint c;
	private Node node;
	private int previousState;

	/**
	 * Constructor 
	 */
	public MouseHandler() {
		WIDTH = 2 * RADIUS;
		SIDE = RADIUS * 3 / 2;
		HEIGHT = (int) ((RADIUS) * Math.sqrt(3));
		grid = null;
		currentTileSelected = null;
		rotate = true;
		node = null;
		state = START_STATE;
		previousState = START_STATE;
	}

	
	/**
	 * Click is the main event in mouse handler, takes in the x and y co-ordinates of the mouse
	 * @param x {@link int} co-ordinate of the mouse
	 * @param y {@link int} co-ordinate of the mouse
	 * @return Item clicked {@link String} of what container has been clicked in. If no container is clicked, returns null
	 */
	public String click(int x, int y) {
		this.x = x;
		this.y = y;
		ArrayList<Constraint> constraintBoxes = Board.getConstraintBoxes();
		StringBuilder sb = new StringBuilder("");
		String tile;
		for (Constraint c : constraintBoxes) {
			if (c.checkInside(x, y)) {
				switch (c.getName()) {
				case "Grid":
					calculateCellindex();
					if (isValidHexCell()) {
						node = new Node(cellIndex_i, cellIndex_j);
						return c.getName();
					}
					break;
				case "Tile":
					tile = c.checkImageExists(x, y);
					if (tile != null)
						currentTileSelected = (new Tile(
								tile));
					return c.getName();
				case "Control":
					sb.append("Control ");
					sb.append(c.checkImageExists(x, y));
					return sb.toString();
				case "Tab":
					sb.append(c.checkImageExists(x, y));
					return sb.toString();
				case "Tempo":
					sb.append("Tempo ");
					sb.append(c.checkImageExists(x, y));
					return sb.toString();
				case "Warp":
					tile = c.checkImageExists(x, y);
					if (tile != null)
						currentTileSelected = (new Tile(tile));
					return c.getName();
				case "UndoRedo":
					sb.append("UndoRedo ");
					sb.append(c.checkImageExists(x, y));
					return sb.toString();
				default:
					currentTileSelected = null;
					return null;
				}
			}
			currentTileSelected = null;
		}
		return null;
	}

	
	/**
	 * Checks if the cell clicked is within the grid
	 * @return {@link boolean} True if it is vaid, false if it isnt
	 */
	private boolean isValidHexCell() {
		if (cellIndex_i >= 0 && cellIndex_i < ReactogonConstants.GRID_WIDTH
				&& cellIndex_j >= 0
				&& cellIndex_j < ReactogonConstants.GRID_HEIGHT)
			return true;
		return false;
	}
	
	/**
	 * Calculates the index in the array in the place you click
	 */
	private void calculateCellindex() {
		int offsetX = x - ReactogonConstants.GRID_LOCATION_OFFSET_X;
		int offsetY = y - ReactogonConstants.GRID_LOCATION_OFFSET_Y;
		int ci = (int) Math.floor((float) offsetX / (float) SIDE);
		int cx = offsetX - SIDE * ci;
		int ty = offsetY - (ci % 2) * HEIGHT / 2;
		int cj = (int) Math.floor((float) ty / (float) HEIGHT);
		int cy = ty - HEIGHT * cj;
		if (cx > Math.abs(RADIUS / 2 - RADIUS * cy / HEIGHT)) {
			cellIndex_j = cj;
			cellIndex_i = ci;
		} else {
			cellIndex_j = cj + (ci % 2) - ((cy < HEIGHT / 2) ? 1 : 0);
			cellIndex_i = ci - 1;
		}
	}

	/**
	 * Gets currently selected tile
	 * @return {@link Tile}
	 */
	public Tile getCurrentTileSelected() {
		return currentTileSelected;
	}

	/**
	 * Sets the value of currentTileSelected to null
	 */
	public void setCurrentTileSelectedToNull() {
		this.currentTileSelected = null;
	}

	/**
	 * Gets currently selected cell
	 * @return {@link Node}
	 */
	public Node getCurrentlySelectedCell() {
		return node;
	}

	/**
	 * Get state
	 * @return {@link int} 0: Play, 1: Rotate, 2: Delete
	 */
	public int getState() {
		return state;
	}


	/** 
	 * Set state 0: Play, 1: Rotate, 2: Delete
	 * @param state {@link int}
	 */
	public void setState(int state) {
		if (state == 2 && this.state != 2) {
			previousState = this.state;
		}
		this.state = (this.state == state ? previousState : state);
	}


	/**
	 * Revert to previous state
	 */
	public void setPreviousState() {
		this.state = previousState;
	}
}
