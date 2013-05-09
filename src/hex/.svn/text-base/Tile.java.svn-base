package reactogon;

/**
 * A single tile
 *
 */
public class Tile {
	private String type;
	private int direction;
	private boolean playsNote; 
	private int ID;
	private Node node;
	public int life;

	
	/**
	 * Creates a tile based on the type input
	 * Setting the default direction to straight up, 0
	 * @param type {@link String}
	 */
	public Tile (String type) {
		this.type = type;
		this.direction = 0;
		life = -1;
	}
	
	/**
	 * Creates a tile based on the type input
	 * Setting the direction to the input
	 * @param type {@link String}
	 * @param direction {@link int}
	 */
	public Tile (String type, int direction) {
		
		this.type = type;
		this.direction = direction;
		life = -1;
	}
	
	/**
	 * Creates a tile based on input information
	 * @param type {@link String}
	 * @param ID {@link int}
	 * @param n {@link Node}
	 * @param dir {@link int}
	 */
	public Tile (String type, int ID, Node n, int dir) {
		this.type = type;
		this.direction = dir;
		this.setID(ID);
		this.setNode(n);
		life = -1;
	}
	
	/**
	 * Set type of selected tile
	 * @param type {@link String}
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Returns type of selected tile
	 * @return type {@link String}
	 */
	public String getType() {
		return type;
	}

	/**
	 * Returns the direction of the selected tile
	 * @return direction {@link int}
	 */
	public int getDirection() {
		//System.out.println("Direction: " + direction);
		return direction;
	}

	/**
	 * Sets the direction of the selected tile
	 * @param direction {@link int}
	 */
	public void setDirection(int direction) {
		if (direction < 0)
			direction = 5;
		this.direction = direction;
	}

	public void incrementDirection() {
		direction = direction > 4 ? 0: direction +1;
	}

	/**
	 * Returns the node of selected tile
	 * @return node {@link Node}
	 */
	public Node getNode() {
		return node;
	}

	/**
	 * Sets the node of the selected tile
	 * @param node {@link Node}
	 */
	public void setNode(Node node) {
		this.node = node;
	}

	/**
	 * Returns the id of the selected tile
	 * @return ID {@link int}
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Sets the id of the selected tile
	 * @param iD {@link int}
	 */
	public void setID(int iD) {
		ID = iD;
	}

}
