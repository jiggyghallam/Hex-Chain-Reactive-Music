package reactogon;

import java.awt.Graphics;

/**
 * Contains the tempo
 *
 */
public class TempoContainer {

	private static final int IMAGE_WIDTH = ReactogonConstants.TEMPO_IMAGE_WIDTH;
	private static final int IMAGE_HEIGHT = ReactogonConstants.TEMPO_IMAGE_HEIGHT;
	private static final int TEMPO_LOCATION_X = ReactogonConstants.TEMPO_LOCATION_X;
	private static final int TEMPO_LOCATION_Y = ReactogonConstants.TEMPO_LOCATION_Y;
	private ImageLoader imageLoader;
	private Constraint constraintBox;
	private String speed;
	

	/**
	 * Sets the starting tempo of 4
	 * Sets the locations and constraints of the tempo area
	 * @param il {@link ImageLoader}
	 * @param box {@link Constraint}
	 */
	public TempoContainer(ImageLoader il, Constraint box) {
		setSpeed("speed4");
		this.imageLoader = il;
		this.constraintBox = box;
		int hitBoxWidth = IMAGE_WIDTH / 5;
		constraintBox
				.addItem(new Constraint("speed1", TEMPO_LOCATION_X, TEMPO_LOCATION_Y, hitBoxWidth, 44, false));
		constraintBox
				.addItem(new Constraint("speed2", TEMPO_LOCATION_X + hitBoxWidth, TEMPO_LOCATION_Y, hitBoxWidth, 44, false));
		constraintBox
				.addItem(new Constraint("speed4", TEMPO_LOCATION_X + hitBoxWidth*2, TEMPO_LOCATION_Y, hitBoxWidth, 44, false));
		constraintBox
				.addItem(new Constraint("speed8", TEMPO_LOCATION_X + hitBoxWidth*3, TEMPO_LOCATION_Y, hitBoxWidth, 44, false));
		constraintBox
				.addItem(new Constraint("speed16", TEMPO_LOCATION_X + hitBoxWidth*4, TEMPO_LOCATION_Y, hitBoxWidth, 44, false));

	}

	/**
	 * Draws the tempos to the screen
	 * @param g {@link Graphics}
	 */
	public void paint(Graphics g) {
		g.drawImage(imageLoader.getImage(speed), TEMPO_LOCATION_X, TEMPO_LOCATION_Y, null);
	}

	/**
	 * Returns current tempo
	 * @return speed {@link String}
	 */
	public String getSpeed() {
		return speed;
	}

	/**
	 * Sets tempo based on string input
	 * @param speed {@link String}
	 */
	public void setSpeed(String speed) {
		this.speed = speed;
	}

}
