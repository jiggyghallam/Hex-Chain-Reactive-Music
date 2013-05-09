package reactogon;

import java.util.ArrayList;

/**
 * class to hold the contraints of where things will be placed in the UI Usefull
 * for mouse detection
 */
public class Constraint {

	private int x, y, dx, dy;
	private String name;
	private ArrayList<Constraint> insideImages;

	/**
	 * Takes the contraints of the area that an image/place of images is in
	 * 
	 * @param x {@link int }
	 *            top left co-ord X
	 * @param y {@link int}
	 *            top left co-ord Y
	 * @param dx {@link int}
	 *            length X
	 * @param dy {@link int}
	 *            length Y
	 */
	public Constraint(int x, int y, int dx, int dy) {
		insideImages = new ArrayList<Constraint>();
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		name = null;
	}

	/**
	 * Used for adding image/objects inside the contraints grid
	 * 
	 * @param name {@link int}
	 *            name of the item placed
	 * @param x {@link int}
	 *            top left co-ord X
	 * @param y {@link int}
	 *            top left co-ord Y
	 * @param dx {@link int}
	 *            length X
	 * @param dy {@link int}
	 *            length Y
	 * @param createSubConstraints  {@link boolean} if set to true will create a new arraylist
	 */
	public Constraint(String name, int x, int y, int dx, int dy, boolean createSubConstraints) {
		if (createSubConstraints)
			insideImages = new ArrayList<Constraint>();
		this.name = name;
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
	}

	/**
	 * Adds an items to be placed inside the contraints box
	 * 
	 * @param c {@link Constraint}
	 *            this should have a name inside!
	 */
	public void addItem(Constraint c) {
		if (c.getName() != null)
			insideImages.add(c);
	}

	/**
	 * Given a co-ordinate. it will check to see if it is inside the given
	 * constraint box
	 * 
	 * @param x
	 *            co-ordinate
	 * @param y
	 *            co-ordinate
	 * @return true / false
	 */
	public boolean checkInside(int x, int y) {
		return (x >= this.x && x <= this.x + dx && y >= this.y && y <= this.y
				+ dy);
	}

	/**
	 * Checks to see if an image/object/a rectangle exists within a given
	 * constraint box
	 * 
	 * @param x {@link int}
	 *            co-ordinate
	 * @param y {@link int}
	 *            co-ordinate
	 * @return Name {@link String} the name of the image found, will give a null if its not
	 *         found
	 */
	public String checkImageExists(int x, int y) {
		for (Constraint c : insideImages)
			if (c.checkInside(x, y))
				return c.getName();

		return null;
	}

	/**
	 * Gets the name of the box
	 * @return {@link String} Name
	 */
	public String getName() {
		return name;
	}

}