package hex;

import java.awt.Graphics;

/**
 * Contains the Warp tile
 *
 */
public class WarpContainer {
	private static final int I_W = ReactogonConstants.WARP_IMAGE_WIDTH;
	private static final int I_H = ReactogonConstants.WARP_IMAGE_HEIGHT;
	private static final int L_X = ReactogonConstants.WARP_LOCATION_X;
	private static final int L_Y = ReactogonConstants.WARP_LOCATION_Y;
	private ImageLoader imageLoader;

	/**
	 * Sets the constaints and attributes of the warp tile within the control panel
	 * @param imageLoader {@link ImageLoader}
	 * @param constraintBox {@link Constraint}
	 */
	public WarpContainer(ImageLoader imageLoader, Constraint constraintBox) {
		this.imageLoader = imageLoader;
		constraintBox.addItem(new Constraint("warp", L_X, L_Y, I_W, I_H, false));
	}

	/**
	 * Draws the warp tile to the control panel
	 * @param g {@link Graphics}
	 */
	public void paint(Graphics g) {
		g.drawImage(imageLoader.getImage("warp"), L_X, L_Y, null);


	}
}
