package hex;

import java.awt.Graphics;

/**
 * Contains the tiles
 *
 */
public class TileContainer {
	//Im trying to win the award for most confusing variable names ;)
	private static final int I_W = ReactogonConstants.TILE_IMAGE_WIDTH;
	private static final int I_H = ReactogonConstants.TILE_IMAGE_HEIGHT;
	private static final int L_X = ReactogonConstants.TILE_LOCATION_X;
	private static final int L_Y = ReactogonConstants.TILE_LOCATION_Y;
	private static final int S_X = ReactogonConstants.TILE_SPACE_X;
	private static final int S_Y = ReactogonConstants.TILE_SPACE_Y;
	private ImageLoader imageLoader;

	/**
	 * Sets the constraints and attributes of the tiles in the control panel
	 * @param imageLoader {@link ImageLoader}
	 * @param constraintBox {@link Constraint}
	 */
	public TileContainer(ImageLoader imageLoader, Constraint constraintBox) {
		this.imageLoader = imageLoader;
		constraintBox.addItem(new Constraint("changeDirT", L_X, L_Y, I_W, I_H, false));
		constraintBox.addItem(new Constraint("changeDirF", L_X, L_Y + ( 1 * I_H) + S_Y, I_W, I_H, false));
		constraintBox.addItem(new Constraint("explosionT", L_X + ( 1 * ( I_W+ S_X)), L_Y, I_W, I_H, false));
		constraintBox.addItem(new Constraint("explosionF", L_X + ( 1 * ( I_W+ S_X)), L_Y + ( 1 * I_H) + S_Y, I_W, I_H, false));
		constraintBox.addItem(new Constraint("stopT", L_X + ( 2 * ( I_W+ S_X)), L_Y, I_W, I_H, false));
		constraintBox.addItem(new Constraint("stopF",  L_X + ( 2 * (I_W+ S_X)), L_Y + ( 1 * I_H) + S_Y, I_W, I_H, false));
		constraintBox.addItem(new Constraint("playT", L_X + ( 3 * ( I_W+ S_X)), L_Y, I_W, I_H, false));
		constraintBox.addItem(new Constraint("playF", L_X + ( 3 * (I_W + S_X)), L_Y + ( 1 * I_H) + S_Y, I_W, I_H, false));
		//constraintBox.addItem(new Constraint("start", 1000, 500, 90, 48, false));

	}

	/**
	 * Draws the tiles to the control panel
	 * @param g {@link Graphics}
	 */
	public void paint(Graphics g) {
		g.drawImage(imageLoader.getImage("changeDirT0On"), L_X, L_Y, null);
		g.drawImage(imageLoader.getImage("changeDirF0On"), L_X, L_Y + ( 1 * I_H) + S_Y, null);
		g.drawImage(imageLoader.getImage("explosionTOn"), L_X + ( 1 * ( I_W+ S_X)), L_Y, null);
		g.drawImage(imageLoader.getImage("explosionFOn"), L_X + ( 1 * ( I_W+ S_X)), L_Y + ( 1 * I_H) + S_Y, null);
		g.drawImage(imageLoader.getImage("stopTOn"), L_X + ( 2 * ( I_W+ S_X)), L_Y, null);
		g.drawImage(imageLoader.getImage("stopFOn"), L_X + ( 2 * (I_W+ S_X)), L_Y + ( 1 * I_H) + S_Y, null);
		g.drawImage(imageLoader.getImage("playT0On"), L_X + ( 3 * ( I_W+ S_X)), L_Y, null);
		g.drawImage(imageLoader.getImage("playF0On"), L_X + ( 3 * (I_W + S_X)), L_Y + ( 1 * I_H) + S_Y, null);
		//g.drawImage(imageLoader.getImage("start"), 1000, 500, null);

	}

}
