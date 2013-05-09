package hex;

/**
 * Task is an item used inside undo redo
 */
public class Task {

	private int type;
	private Node node;
	private Tile tile;
	private Tile newTile;
	
	
	/**
	 * Task
	 * @param type {@link int} 0: Undo, 1: redo
	 * @param node {@link Node} 
	 * @param tile {@link Tile} 
	 * @param newTile {@link Tile}
	 */
	public Task (int type, Node node, Tile tile, Tile newTile) {
		this.setNewTile(newTile);
		this.setType(type);
		this.setNode(node);
		this.setTile(tile);
	}


	/**
	 * Gets the type of task 0: undo, 1: redo
	 * @return type {@link int}
	 */
	public int getType() {
		return type;
	}


	/**
	 * Sets the type of task 0: undo, 1: redo
	 * @param type {@link int}
	 */
	public void setType(int type) {
		this.type = type;
	}


	/**
	 * Gets a node
	 * @return node {@link Node}
	 */
	public Node getNode() {
		return node;
	}


	/**
	 * Sets a node
	 * @param node {@link Node}
	 */
	public void setNode(Node node) {
		this.node = node;
	}


	/**
	 * gets a tile
	 * @return tile {@link Tile}
	 */
	public Tile getTile() {
		return tile;
	}


	/**
	 * Sets a tile
	 * @param tile {@link Tile}
	 */
	public void setTile(Tile tile) {
		this.tile = tile;
	}


	/**
	 * Get new tile
	 * @return newTile {@link Tile}
	 */
	public Tile getNewTile() {
		return newTile;
	}


	/**
	 * Set new tile
	 * @param newTile {@link Tile}
	 */
	public void setNewTile(Tile newTile) {
		this.newTile = newTile;
	}

}
