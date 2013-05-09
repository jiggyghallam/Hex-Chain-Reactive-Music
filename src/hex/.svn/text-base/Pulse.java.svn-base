package reactogon;

/**
 * Pulse that can be placed in a grid
 *
 */
public class Pulse {
	
	
	private Node currentNode;
	private Node nextNode;
	private Node previousNode;

	private int direction; 			//not needed maybe?
	@SuppressWarnings("unused")
	private SoundSequencer soundSequencer;
	
	

	/**
	 * Creates a new pulse, this is useful for the first pulse created by the play button
	 * @param n {@link Node} holds the current position (in the form of a node) in the hexCell
	 * @param ss {@link SoundSequencer} passes the sound sequecner (this may not be needed in the future)
	 * @param direction {@link int} the direction the pulse is going
	 */
	public Pulse (Node n, SoundSequencer ss, int direction) {
		this.currentNode = n;
		this.soundSequencer = ss;
		this.direction = direction;
		previousNode = null; 		//Because when created will not have a previous value
	}
	
	/**
	 * Creates a new pulse, this is useful for the when an explosion tile is used, or a pulse that is created mid play
	 * @param n {@link Node} holds the current position (in the form of a node) in the hexCell
	 * @param ss {@link Node} passes the sound sequecner (this may not be needed in the future)
	 * @param previousN {@link SoundSequencer} can take a previous value if it has one
	 * @param direction {@link int} the direction the pulse is going
	 */
	public Pulse (Node n, Node previousN, SoundSequencer ss, int direction) {
		this.currentNode = n;
		this.soundSequencer = ss;
		this.previousNode = previousN; 	
		this.direction = direction;
	}

	/**
	 * Gets direction of the pulse
	 * @return {@link int}
	 */
	public int getDirection() {
		return direction;
	}

	/**
	 * Sets the direction of the pulse from 0 to 5, 0 being North
	 * @param direction {@link int}
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	/**
	 * Set next pulse
	 * @param n {@link Node}
	 * @param direction {@link int}
	 */
	public void setNext(Node n, int direction) {
		this.nextNode = n;
		this.direction = direction;
	}
	
	/**
	 * Set next node
	 * @param n {@link Node}
	 */
	public void setNext(Node n) {
		this.nextNode = n;
	}
	
	/**
	 * Get next node
	 * @return {@link Node}
	 */
	public Node getNext() {
		return nextNode;
	}
	
	/**
	 * Returns the co-ordinates in the form of a node that the pulse is CURRENTLY in
	 * @return {@link Node}
	 */
	public Node getCurrent() {
		return currentNode;
	}
	
	/**
	 * Sets current pulse to be the next pulse
	 */
	public void setCurrent() {
		currentNode = nextNode;
	}
	
	/**
	 * Returns the co-ordinates in the form of a node that the pulse was PREVIOUSLY in
	 * @return {@link Node}
	 */
	public Node getPrevious() {
		return previousNode;
	}
	
	/**
	 * Sets previous node to be current node
	 */
	public void setPrevious() {
		previousNode = currentNode;
	}
	
		

}
