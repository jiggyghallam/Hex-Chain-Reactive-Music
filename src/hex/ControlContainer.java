package hex;

import java.awt.Graphics;
/**
 * Control container 
 */
public class ControlContainer {
	private static final int I_W = ReactogonConstants.CONTROL_IMAGE_WIDTH;
	private static final int I_H = ReactogonConstants.CONTROL_IMAGE_HEIGHT;
	private static final int L_X = ReactogonConstants.CONTROL_LOCATION_X;
	private static final int L_Y = ReactogonConstants.CONTROL_LOCATION_Y;
	private static final int S_X = ReactogonConstants.CONTROL_SPACE_X;
	private static final int S_Y = ReactogonConstants.CONTROL_SPACE_Y;
	private static final int R_L_X = ReactogonConstants.ROTATE_LOCATION_X;
	private static final int R_L_Y = ReactogonConstants.ROTATE_LOCATION_Y;
	private static final int R_I_W = ReactogonConstants.ROTATE_IMAGE_WIDTH;
	private static final int R_I_H = ReactogonConstants.ROTATE_IMAGE_HEIGHT;
	
	private ImageLoader imageLoader;
	private String rotateSwitchState;
	private String startState;
	private String endState;
	private String deleteState;
	
	/**
	 * Constructor to contain the grid control
	 * @param imageLoader {@link ImageLoader}
	 * @param constraintBox {@link Constraint}
	 */
	public ControlContainer (ImageLoader imageLoader, Constraint constraintBox) {
		rotateSwitchState = "rotateSwitchF";
		startState = "startOff";
		endState = "endOn";
		deleteState = "removeOff";
		this.imageLoader = imageLoader;
		
		constraintBox.addItem(new Constraint("start", L_X, L_Y, I_W, I_H, false));
		constraintBox.addItem(new Constraint("end", L_X + (I_W + S_X), L_Y, I_W, I_H, false));
		constraintBox.addItem(new Constraint("remove", L_X, L_Y + (I_H + S_Y), I_W, I_H, false));
		constraintBox.addItem(new Constraint("clear", L_X + (I_W + S_X), L_Y + (I_H + S_Y), I_W, I_H, false));
		constraintBox.addItem(new Constraint("rotateSwitch", R_L_X, R_L_Y, R_I_W, R_I_H, false));
	}
	
	public void paint(Graphics g) {
		g.drawImage(imageLoader.getImage(startState), L_X, L_Y, null);
		g.drawImage(imageLoader.getImage(endState), L_X + (I_W + S_X), L_Y, null);
		g.drawImage(imageLoader.getImage(deleteState), L_X, L_Y + (I_H + S_Y), null);
		g.drawImage(imageLoader.getImage("clear"), L_X + (I_W + S_X), L_Y + (I_H + S_Y), null);
		g.drawImage(imageLoader.getImage(rotateSwitchState), R_L_X, R_L_Y, null);
	}

	/**
	 * Gets the rotate switch string
	 * @return {@link String} switch
	 */
	public String getRotateSwitchState() {
		return rotateSwitchState;
	}

	/**
	 * Sets the string for rotate switch
	 * @param rotateSwitchState {@link String} 
	 */
	public void setRotateSwitchState(String rotateSwitchState) {
		this.rotateSwitchState = rotateSwitchState;
	}
	
	/**
	 * Sets the state of the start button
	 * @param state {@link boolean} true sets the start light on
	 */
	public void setStartState(boolean state) {
		if (state) {
			startState = "startOn";
			endState = "endOff";
		} else {
			startState = "startOff";
			endState = "endOn";
		}
	}
	
	/**
	 * Sets the state of the delete button
	 * @param state {@link boolean} if true turns on the delete light
	 */
	public void setDeleteState(boolean state) {
		deleteState = state ? "removeOn" : "removeOff";
	}

}
