package hex;

import javax.swing.*;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Main class to contain preferences
 *
 */
public class Preferences extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTabbedPane jtp;

	
	/**
	 * Takes in a grid container that can be passed to each new gridpreference
	 * @param gc {@link GridContainer}
	 */
	public Preferences(GridContainer gc) {

		setTitle("Preferences");
		setSize(480, 250);

		jtp = new JTabbedPane();
		setLayout(new BorderLayout());
		JPanel mainArea = new JPanel();
		mainArea.setLayout(new BorderLayout());
		mainArea.add(jtp);
		add(mainArea);

		jtp.addTab("Grid 1", new GridPreference(gc, 0));
		jtp.addTab("Grid 2", new GridPreference(gc, 1));
		jtp.addTab("Grid 3", new GridPreference(gc, 2));
		jtp.addTab("Grid 4", new GridPreference(gc, 3));
		jtp.addTab("Grid 5", new GridPreference(gc, 4));
		jtp.addTab("Grid 6", new GridPreference(gc, 5));

		setVisible(true);
	}

}