package reactogon;

import java.awt.Graphics;

/**
 * Contains the tabs
 *
 */
public class TabContainer {
	private static final int NUMBER_OF_TABS = 6;
	private static final int IMAGE_HEIGHT = ReactogonConstants.TAB_IMAGE_HEIGHT;
	private static final int IMAGE_WIDTH = ReactogonConstants.TAB_IMAGE_WIDTH;
	private static final int OFFSET_X = ReactogonConstants.TAB_LOCATION_OFFSET_X;
	private static final int OFFSET_Y = ReactogonConstants.TAB_LOCATION_OFFSET_Y;
	private int hitBoxWidth;
	private String tab;
	

	private ImageLoader imageLoader;
	private Constraint constraintBox;

	/**
	 * Adds constraints of all tabs
	 * @param il {@link ImageLoader}
	 * @param box {@link Constraint}
	 */
	public TabContainer(ImageLoader il, Constraint box) {
		imageLoader = il;
		constraintBox = box;
		setTab("tab0");

		
		hitBoxWidth = IMAGE_WIDTH / NUMBER_OF_TABS;
		constraintBox
				.addItem(new Constraint("Tab 0", OFFSET_X, OFFSET_Y, hitBoxWidth, IMAGE_HEIGHT, false));
		constraintBox
				.addItem(new Constraint("Tab 1", OFFSET_X + hitBoxWidth, OFFSET_Y, hitBoxWidth, IMAGE_HEIGHT, false));
		constraintBox
				.addItem(new Constraint("Tab 2", OFFSET_X + hitBoxWidth*2, OFFSET_Y, hitBoxWidth, IMAGE_HEIGHT, false));
		constraintBox
				.addItem(new Constraint("Tab 3", OFFSET_X + hitBoxWidth*3, OFFSET_Y, hitBoxWidth, IMAGE_HEIGHT, false));
		constraintBox
				.addItem(new Constraint("Tab 4", OFFSET_X + hitBoxWidth*4, OFFSET_Y, hitBoxWidth, IMAGE_HEIGHT, false));
		constraintBox
				.addItem(new Constraint("Tab 5", OFFSET_X + hitBoxWidth*5, OFFSET_Y, hitBoxWidth, IMAGE_HEIGHT, false));
	}

	/**
	 * Draws tab image to the screen
	 * @param g {@link Graphics}
	 */
	public void paint(Graphics g) {
			
			g.drawImage(imageLoader.getImage(tab), OFFSET_X, OFFSET_Y, IMAGE_WIDTH, IMAGE_HEIGHT, null);
	}

	/**
	 * Returns the string id of the currently selected tab
	 * @return tab {@link String}
	 */
	public String getTab() {
		return tab;
	}

	/**
	 * Sets the current tab to the inputed string id
	 * @param tab {@link String}
	 */
	public void setTab(String tab) {
		this.tab = tab;
	}

}
