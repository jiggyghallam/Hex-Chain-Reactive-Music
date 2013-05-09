package reactogon;

import java.awt.Graphics;

/**
 * Contains undo and redo buttons
 *
 */
public class UndoRedoContainer {
	private static final int I_W = ReactogonConstants.UNDO_REDO_IMAGE_WIDTH;
	private static final int I_H = ReactogonConstants.UNDO_REDO_IMAGE_HEIGHT;
	private static final int L_X = ReactogonConstants.UNDO_REDO_LOCATION_X;
	private static final int L_Y = ReactogonConstants.UNDO_REDO_LOCATION_Y;
	private static final int S_X = ReactogonConstants.UNDO_REDO_SPACE_X;
	private ImageLoader imageLoader;

	/**
	 * Sets the constraints and attributes for the undo and redo within control panel
	 * @param imageLoader {@link ImageLoader}
	 * @param constraintBox {@link Constraint}
	 */
	public UndoRedoContainer(ImageLoader imageLoader, Constraint constraintBox) {
		this.imageLoader = imageLoader;
		constraintBox.addItem(new Constraint("undo", L_X, L_Y, I_W, I_H, false));
		constraintBox.addItem(new Constraint("redo", L_X + (I_W + S_X), L_Y, I_W, I_H, false));
	}

	/**
	 * Draws the undo and redo to the control panel
	 * @param g {@link Graphics}
	 */
	public void paint(Graphics g) {
		g.drawImage(imageLoader.getImage("undo"), L_X, L_Y, null);
		g.drawImage(imageLoader.getImage("redo"), L_X + (I_W + S_X), L_Y, null);
	}
}
