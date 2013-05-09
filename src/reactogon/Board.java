package reactogon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.swing.JPanel;

//import javax.swing.Timer;

/**
 * Board is the main class
 * 
 */
public class Board extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final int SCREEN_RES_X = 1280;
	@SuppressWarnings("unused")
	private static final int SCREEN_RES_y = 720;
	private static ArrayList<Constraint> constraintBoxes;
	private static final int TEMPO = ReactogonConstants.TEMPO;
	private ImageLoader il;
	private Timer timer;
	private MouseHandler mouseHandler;
	private GridContainer gc;
	private TileContainer tc;
	private ControlContainer cc;
	private TabContainer tabc;
	private TempoContainer tempoc;
	private WarpContainer wc;
	private UndoRedoContainer uc;
	private boolean play;

	/**
	 * Constructor
	 */
	public Board() {
		il = new ImageLoader();
		constraintBoxes = new ArrayList<Constraint>();
		mouseHandler = new MouseHandler();
		timer = new Timer();
		timer.scheduleAtFixedRate(new Hex(), 0, TEMPO);
		play = false;
		setBackground(Color.BLACK);

	}

	/**
	 * Creates the layout
	 * 
	 * @throws InvalidMidiDataException
	 * @throws MidiUnavailableException
	 */
	public void createLayout() throws InvalidMidiDataException,
			MidiUnavailableException {

		addMouseListener(this);
		// Adds Grid to constraint box
		constraintBoxes.add(new Constraint("Grid",
				ReactogonConstants.GRID_LOCATION_OFFSET_X,
				ReactogonConstants.GRID_LOCATION_OFFSET_Y, 906, 516, true));
		gc = new GridContainer(il);
		gc.createGrids();

		// Adds Tile constraint box
		constraintBoxes.add(new Constraint("Tile",
				ReactogonConstants.TILE_LOCATION_X,
				ReactogonConstants.TILE_LOCATION_Y,
				ReactogonConstants.TILE_HIT_BOX_WIDTH,
				ReactogonConstants.TILE_HIT_BOX_HEIGHT, true));
		tc = new TileContainer(il,
				constraintBoxes.get(constraintBoxes.size() - 1));

		// Adds control constraint box
		constraintBoxes.add(new Constraint("Control",
				ReactogonConstants.CONTROL_LOCATION_X,
				ReactogonConstants.CONTROL_LOCATION_Y,
				ReactogonConstants.CONTROL_HIT_BOX_WIDTH,
				ReactogonConstants.CONTROL_HIT_BOX_HEIGHT, true));
		cc = new ControlContainer(il, constraintBoxes.get(constraintBoxes
				.size() - 1));

		// Adds tab constraint box
		constraintBoxes.add(new Constraint("Tab",
				ReactogonConstants.TAB_LOCATION_OFFSET_X,
				ReactogonConstants.TAB_LOCATION_OFFSET_Y,
				ReactogonConstants.TAB_IMAGE_WIDTH,
				ReactogonConstants.TAB_IMAGE_HEIGHT, true));
		tabc = new TabContainer(il,
				constraintBoxes.get(constraintBoxes.size() - 1));

		// Adds Tempo constraint box
		constraintBoxes.add(new Constraint("Tempo",
				ReactogonConstants.TEMPO_LOCATION_X,
				ReactogonConstants.TEMPO_LOCATION_Y,
				ReactogonConstants.TEMPO_IMAGE_WIDTH,
				ReactogonConstants.TEMPO_IMAGE_HEIGHT, true));
		tempoc = new TempoContainer(il, constraintBoxes.get(constraintBoxes
				.size() - 1));

		// Adds warp constraint box
		constraintBoxes.add(new Constraint("Warp",
				ReactogonConstants.WARP_LOCATION_X,
				ReactogonConstants.WARP_LOCATION_Y,
				ReactogonConstants.WARP_HIT_BOX_WIDTH,
				ReactogonConstants.WARP_IMAGE_HEIGHT, true));
		wc = new WarpContainer(il,
				constraintBoxes.get(constraintBoxes.size() - 1));

		// Adds UndoRedo constraint box
		constraintBoxes.add(new Constraint("UndoRedo",
				ReactogonConstants.UNDO_REDO_LOCATION_X,
				ReactogonConstants.UNDO_REDO_LOCATION_Y,
				ReactogonConstants.UNDO_REDO_HIT_BOX_WIDTH,
				ReactogonConstants.CONTROL_HIT_BOX_HEIGHT, true));
		uc = new UndoRedoContainer(il, constraintBoxes.get(constraintBoxes
				.size() - 1));

		setPlay(false);

	}

	/**
	 * Get the grid container
	 * 
	 * @return {@link GridContainer}
	 */
	public GridContainer getGc() {
		return gc;
	}

	/**
	 * Get all constraintboxes
	 * 
	 * @return {@link ArrayList} {@link Constraint}
	 */
	public static ArrayList<Constraint> getConstraintBoxes() {
		return constraintBoxes;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		gc.paint(g);
		tc.paint(g);
		cc.paint(g);
		tabc.paint(g);
		tempoc.paint(g);
		wc.paint(g);
		uc.paint(g);
		g.drawImage(il.getImage("forground"), 0, 0, null);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			String s = mouseHandler.click(e.getX(), e.getY());
			if (s != null) {
				String[] stringParts = s.split(" ");
				switch (stringParts[0]) {
				case "Grid":
					gridClick();
					break;
				case "Control":
					controlClick(stringParts[1]);
					break;
				case "Tab":
					tabClick(Integer.parseInt(stringParts[1]));
					break;
				case "Tempo":
					tempoClick(stringParts[1]);
					break;
				case "UndoRedo":
					undoRedoClick(stringParts[1]);
					break;
				default:
					break;
				}
			}
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			String s = mouseHandler.click(e.getX(), e.getY());
			if (s != null) {
				String[] stringParts = s.split(" ");
				switch (stringParts[0]) {
				case "Grid":
					addLife();
					break;
				default:
					break;
				}
			}
		}

	}

	private void addLife() {
		int index_i = mouseHandler.getCurrentlySelectedCell().getI();
		int index_j = mouseHandler.getCurrentlySelectedCell().getJ();
		Tile tileToAddLife = gc.getTileInActiveGrid(index_i, index_j);
		if (tileToAddLife != null) {
			if (tileToAddLife.life == -1)
				tileToAddLife.life = 1;
			else
				tileToAddLife.life++;
		}

	}

	/**
	 * Undo or redo if clicked
	 * 
	 * @param action
	 *            undo or redo
	 */
	private void undoRedoClick(String action) {
		switch (action) {
		case "undo":
			gc.undoActionInActiveGrid();
			break;
		case "redo":
			gc.redoActionInActiveGrid();
			break;
		}

	}

	/**
	 * Used for mouse click of tempo button
	 * 
	 * @param string
	 *            {@link String }Speed of the tempo to be set must be a 1 or a
	 *            power of 2
	 */
	private void tempoClick(String string) {
		int speed = Integer.parseInt(string.substring(5));
		gc.setTempo(speed);
		tempoc.setSpeed(string);
	}

	// Runs the software
	class Hex extends TimerTask {
		long startTime;
		long finishTime;

		public void run() {
			startTime = System.currentTimeMillis();
			repaint();
			if (play) {
				try {
					gc.play();
				} catch (InvalidMidiDataException e) {
					System.err.println("Cannot play due to midi exception");
				}
			}
			finishTime = System.currentTimeMillis();
		}

	}

	/**
	 * Method called when a click within the grid is detected
	 */
	private void gridClick() {
		int index_i = mouseHandler.getCurrentlySelectedCell().getI();
		int index_j = mouseHandler.getCurrentlySelectedCell().getJ();
		Tile tileToAdd = mouseHandler.getCurrentTileSelected();
		if (tileToAdd != null) {
			gc.putTileInActiveGrid(tileToAdd, index_i, index_j);
			mouseHandler.setCurrentTileSelectedToNull();
		} else {
			switch (mouseHandler.getState()) {
			// Play note
			case 0:
				gc.playNote(index_i, index_j);
				break;
			// Rotate
			case 1:
				Tile tileToRotate = gc.getTileInActiveGrid(index_i, index_j);
				if (tileToRotate != null) {
					tileToRotate.incrementDirection();
					gc.putTileInActiveGrid(tileToRotate, index_i, index_j);
				}
				break;
			// Delete
			case 2:
				gc.removeTileInActiveGrid(index_i, index_j);
				break;
			}
		}

	}

	/**
	 * Used for mouse clicks in the control area
	 * 
	 * @param action
	 *            {@link String} what item has been clicked
	 */
	private void controlClick(String action) {
		switch (action) {
		case "start":
			gc.setStartNodes();
			cc.setStartState(true);
			setPlay(true);
			play = true;
			break;
		case "remove":
			if (mouseHandler.getState() == 2) {
				mouseHandler.setPreviousState();
				cc.setDeleteState(false);
			} else {
				cc.setDeleteState(true);
				mouseHandler.setState(2);
			}
			break;

		case "clear":
			gc.clearGrid();
			break;
		case "end":
			cc.setStartState(false);
			play = false;
			gc.stop();
			break;
		case "rotateSwitch":
			switch (mouseHandler.getState()) {
			// Play note
			case 0:
				cc.setRotateSwitchState(action + "T");
				mouseHandler.setState(1);
				break;
			// Rotate
			case 1:
				cc.setRotateSwitchState(action + "F");
				mouseHandler.setState(0);
				break;
			// Delete
			case 2:
				break;
			}
			break;
		}
	}

	/**
	 * Used for mouse clicks in the tab area
	 * 
	 * @param t
	 *            {@link int} what tab has been clicked
	 */
	private void tabClick(int t) {
		gc.setCurrentActiveGrid(t);
		tempoc.setSpeed(gc.getTempo());
		tabc.setTab("tab" + t);
	}

	/**
	 * Sets if the system should be playing or not
	 * 
	 * @param p
	 *            {@link boolean} true for playing, false for not playing
	 */
	public void setPlay(boolean p) {
		play = p;
	}
}
