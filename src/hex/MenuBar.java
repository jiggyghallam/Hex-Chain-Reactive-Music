package hex;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * Contains the MenuBar and all corresponding items
 *
 */
public class MenuBar {
	private JMenuBar menu;

	private JMenuItem nuw, load, save, saveas, exit, undo, redo, clear,
			allgrids, helpItem, about, preferences, playButton;
	private JCheckBoxMenuItem grid1, grid2, grid3, grid4, grid5, grid6;
	private JMenu file, edit, play, help;

	/**
	 * Sets all items and levels of the menu bar
	 * Action listeners are within TempMain
	 */
	public MenuBar() {

		menu = new JMenuBar();
		file = new JMenu("File");
		edit = new JMenu("Edit");
		play = new JMenu("Play");
		help = new JMenu("Help");

		menu.add(file);
		menu.add(edit);
		menu.add(play);
		menu.add(help);

		nuw = new JMenuItem("New");
		nuw.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				ActionEvent.CTRL_MASK));
		load = new JMenuItem("Load");
		load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				ActionEvent.CTRL_MASK));
		save = new JMenuItem("Save");
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK));
		saveas = new JMenuItem("Save As");
		saveas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));
		exit = new JMenuItem("Exit");

		file.add(nuw);
		file.add(load);
		file.add(save);
		file.add(saveas);
		file.add(exit);

		undo = new JMenuItem("Undo");
		undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
				ActionEvent.CTRL_MASK));
		redo = new JMenuItem("Redo");
		redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,
				ActionEvent.CTRL_MASK));
		clear = new JMenuItem("Clear");
		clear.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,
				ActionEvent.CTRL_MASK));
		preferences = new JMenuItem("Preferences");

		edit.add(undo);
		edit.add(redo);
		edit.add(clear);
		edit.add(preferences);

		playButton = new JMenuItem("Start");
		playButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE,
				ActionEvent.CTRL_MASK));
		allgrids = new JMenuItem("Select All");
		grid1 = new JCheckBoxMenuItem("Grid 1");
		grid1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD1,
				ActionEvent.CTRL_MASK));
		grid2 = new JCheckBoxMenuItem("Grid 2");
		grid2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD2,
				ActionEvent.CTRL_MASK));
		grid3 = new JCheckBoxMenuItem("Grid 3");
		grid3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD3,
				ActionEvent.CTRL_MASK));
		grid4 = new JCheckBoxMenuItem("Grid 4");
		grid4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD4,
				ActionEvent.CTRL_MASK));
		grid5 = new JCheckBoxMenuItem("Grid 5");
		grid5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD5,
				ActionEvent.CTRL_MASK));
		grid6 = new JCheckBoxMenuItem("Grid 6");
		grid6.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD6,
				ActionEvent.CTRL_MASK));

		grid1.setSelected(true);
		grid2.setSelected(true);
		grid3.setSelected(true);
		grid4.setSelected(true);
		grid5.setSelected(true);
		grid6.setSelected(true);

		play.add(playButton);
		play.add(allgrids);
		play.add(grid1);
		play.add(grid2);
		play.add(grid3);
		play.add(grid4);
		play.add(grid5);
		play.add(grid6);

		helpItem = new JMenuItem("Reactagon Help");
		about = new JMenuItem("About");

		help.add(helpItem);
		help.add(about);
	}
	
	/**
	 * Getters and setters for appropriate menu items
	 */

	public JMenuBar getMenu() {
		return menu;
	}

	public JMenuItem getSave() {
		return save;
	}

	public JMenuItem getSaveAs() {
		return saveas;
	}

	public JMenuItem getNew() {
		return nuw;
	}

	public JMenuItem getExit() {
		return exit;
	}

	public JMenuItem getLoad() {
		return load;
	}

	public JMenuItem getPreferences() {
		return preferences;
	}

	public JMenuItem getRedo() {
		return redo;
	}

	public JMenuItem getUndo() {
		return undo;
	}

	public JMenuItem getClear() {
		return clear;
	}

	public JMenuItem getPlayButton() {
		return playButton;
	}

	public JMenuItem getSelectAll() {
		return allgrids;
	}

	public JCheckBoxMenuItem getGrid1() {
		return grid1;
	}

	public JCheckBoxMenuItem getGrid2() {
		return grid2;
	}

	public JCheckBoxMenuItem getGrid3() {
		return grid3;
	}

	public JCheckBoxMenuItem getGrid4() {
		return grid4;
	}

	public JCheckBoxMenuItem getGrid5() {
		return grid5;
	}

	public JCheckBoxMenuItem getGrid6() {
		return grid6;
	}

	public void setGrid1(boolean check) {
		grid1.setSelected(check);
	}

	public void setGrid2(boolean check) {
		grid2.setSelected(check);
	}

	public void setGrid3(boolean check) {
		grid3.setSelected(check);
	}

	public void setGrid4(boolean check) {
		grid4.setSelected(check);
	}

	public void setGrid5(boolean check) {
		grid5.setSelected(check);
	}

	public void setGrid6(boolean check) {
		grid6.setSelected(check);
	}

	public void setAllGrids() {
		grid1.setSelected(true);
		grid2.setSelected(true);
		grid3.setSelected(true);
		grid4.setSelected(true);
		grid5.setSelected(true);
		grid6.setSelected(true);
	}

	public JMenuItem getHelp() {
		return helpItem;
	}

	public JMenuItem getAbout() {
		return about;
	}

}
