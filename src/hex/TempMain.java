package hex;

import java.awt.BorderLayout;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Main class to get stuff rolling
 *
 */
public class TempMain extends JFrame {

	private static final long serialVersionUID = 7164910088989168298L;
	private static final int SCREEN_RES_X = 1285;
	private static final int SCREEN_RES_y = 735;
	private static MenuBar menu = new MenuBar();

	public TempMain() {
	}

	/**
	 * Initial starting point runs the createAndShowGUI method
	 * @param args {@link String[]}
	 */
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createAndShowGUI();
			}
		});
	}

	/**
	 * Creates the main frame initialising the board
	 * Adds the action listeners for the menu bar
	 */
	private static void createAndShowGUI() {
		JFrame frame = new JFrame();
		frame.setSize(SCREEN_RES_X, SCREEN_RES_y);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle("Hexagon Grid");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(menu.getMenu(), BorderLayout.NORTH);

		Board b = new Board();
		try {
			b.createLayout();
		} catch (InvalidMidiDataException e) {
			System.err.println("Unable to create sound sequencer");
			e.printStackTrace();
		} catch (MidiUnavailableException e) {
			System.err.println("Unable to create sound sequencer");
			e.printStackTrace();
		}

		MenuActionListener mal = new MenuActionListener(b.getGc(), menu);
		menu.getNew().addActionListener(mal);
		menu.getSave().addActionListener(mal);
		menu.getSaveAs().addActionListener(mal);
		menu.getLoad().addActionListener(mal);
		menu.getUndo().addActionListener(mal);
		menu.getRedo().addActionListener(mal);
		menu.getClear().addActionListener(mal);
		menu.getPlayButton().addActionListener(mal);
		menu.getSelectAll().addActionListener(mal);
		menu.getGrid1().addActionListener(mal);
		menu.getGrid2().addActionListener(mal);
		menu.getGrid3().addActionListener(mal);
		menu.getGrid4().addActionListener(mal);
		menu.getGrid5().addActionListener(mal);
		menu.getGrid6().addActionListener(mal);
		menu.getExit().addActionListener(mal);
		menu.getPreferences().addActionListener(mal);
		menu.getHelp().addActionListener(mal);
		menu.getAbout().addActionListener(mal);

		
		frame.add(b);
	}
}
