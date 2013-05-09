package hex;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Contains all action listeners for the menubar
 *
 */
public class MenuActionListener implements ActionListener {

	private SaveLoad sl;
	private GridContainer gc;
	private MenuBar menu;
	private boolean opened = false;
	private String path;

	/**
	 * Takes a gridcontainer and a menubar to get and set information
	 * @param gc {@link GridContainer}
	 * @param menu {@link MenuBar}
	 */
	public MenuActionListener(GridContainer gc, MenuBar menu) {
		this.gc = gc;
		this.menu = menu;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == menu.getNew()) {
			// set opened to false unpon creating a new board
			opened = false;
			gc.stop();
			// reset all aspects of the grids
			for (int i = 0; i <= 5; i++) {
				gc.getSpecificGrid(i).clear();
				gc.getSpecificGrid(i).setTempo(4);
				gc.getSpecificGrid(i).setStartNote(
						ReactogonConstants.START_NOTE);
			}

		} else if (event.getSource() == menu.getSave()) {
			// only open filechooser if user hasn't opened a file
			if (opened == true) {
				sl = new SaveLoad(gc);
				sl.save(path);
			} else {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"HEX files", "hex");
				fileChooser.setFileFilter(filter);
				fileChooser.setDialogTitle("Save");
				fileChooser.setApproveButtonText("Save");
				fileChooser.setApproveButtonToolTipText("Save hex file");
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					path = selectedFile.getAbsolutePath();
					sl = new SaveLoad(gc);
					sl.save(path);
				}
			}
		} else if (event.getSource() == menu.getSaveAs()) {
			// always open filechooser
			JFileChooser fileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"HEX files", "hex");
			fileChooser.setFileFilter(filter);
			fileChooser.setDialogTitle("Save As");
			fileChooser.setApproveButtonText("Save As");
			fileChooser.setApproveButtonToolTipText("Save hex file");
			int returnValue = fileChooser.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				path = selectedFile.getAbsolutePath();
				sl = new SaveLoad(gc);
				sl.save(path);
			}
		} else if (event.getSource() == menu.getExit()) {
			int selectedOption = JOptionPane.showConfirmDialog(null,
					"Are You Sure You Want to exit?", "Choose",
					JOptionPane.YES_NO_OPTION);
			if (selectedOption == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		} else if (event.getSource() == menu.getLoad()) {
			sl = new SaveLoad(gc);
			JFileChooser fileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"HEX files", "hex");
			fileChooser.setFileFilter(filter);
			int returnValue = fileChooser.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				opened = true;
				File selectedFile = fileChooser.getSelectedFile();
				path = selectedFile.getAbsolutePath();
				sl.open(path);
			}
		} else if (event.getSource() == menu.getPreferences()) {
			new Preferences(gc);
		} else if (event.getSource() == menu.getRedo()) {
			gc.getSpecificGrid(gc.getCurrentActiveGrid()).redo();
		} else if (event.getSource() == menu.getUndo()) {
			gc.getSpecificGrid(gc.getCurrentActiveGrid()).undo();
		} else if (event.getSource() == menu.getClear()) {
			gc.getSpecificGrid(gc.getCurrentActiveGrid()).clear();
		} else if (event.getSource() == menu.getPlayButton()) {
			gc.setStartNodes();
		} else if (event.getSource() == menu.getSelectAll()) {
			menu.setAllGrids();
			for (int i = 0; i <= 5; i++) {
				gc.getSpecificGrid(i).setPlay(true);
			}
		} else if (event.getSource() == menu.getGrid1()) {
			if (menu.getGrid1().isSelected() == false) {
				menu.setGrid1(false);
				gc.getSpecificGrid(0).setPlay(false);
			} else {
				menu.setGrid1(true);
				gc.getSpecificGrid(0).setPlay(true);
			}
		} else if (event.getSource() == menu.getGrid2()) {
			if (menu.getGrid2().isSelected() == false) {
				menu.setGrid2(false);
				gc.getSpecificGrid(1).setPlay(false);
			} else {
				menu.setGrid2(true);
				gc.getSpecificGrid(1).setPlay(true);
			}
		} else if (event.getSource() == menu.getGrid3()) {
			if (menu.getGrid3().isSelected() == false) {
				menu.setGrid3(false);
				gc.getSpecificGrid(2).setPlay(false);
			} else {
				menu.setGrid3(true);
				gc.getSpecificGrid(2).setPlay(true);
			}
		} else if (event.getSource() == menu.getGrid4()) {
			if (menu.getGrid4().isSelected() == false) {
				menu.setGrid4(false);
				gc.getSpecificGrid(3).setPlay(false);
			} else {
				menu.setGrid4(true);
				gc.getSpecificGrid(3).setPlay(true);
			}
		} else if (event.getSource() == menu.getGrid5()) {
			if (menu.getGrid5().isSelected() == false) {
				menu.setGrid5(false);
				gc.getSpecificGrid(4).setPlay(false);
			} else {
				menu.setGrid5(true);
				gc.getSpecificGrid(4).setPlay(true);
			}
		} else if (event.getSource() == menu.getGrid6()) {
			if (menu.getGrid6().isSelected() == false) {
				menu.setGrid6(false);
				gc.getSpecificGrid(5).setPlay(false);
			} else {
				menu.setGrid6(true);
				gc.getSpecificGrid(5).setPlay(true);
			}
		} else if (event.getSource() == menu.getHelp()) {
			gc.stop();
			new HelpWindow();
		} else if (event.getSource() == menu.getAbout()) {
			new About();
		}
	}

}
