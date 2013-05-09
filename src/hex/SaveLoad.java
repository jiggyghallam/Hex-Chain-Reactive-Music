package hex;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Contains methods for loading and saving
 * 
 */
public class SaveLoad {

	static final int GRID_WIDTH = ReactogonConstants.GRID_WIDTH;
	static final int GRID_HEIGHT = ReactogonConstants.GRID_HEIGHT;

	private GridContainer gc;
	private Boolean[] gridBools = new Boolean[6];
	private int layerCount = 0;
	private JComboBox<String> comboBox;

	private Grid[] gridArray = new Grid[6];

	/**
	 * Takes a gridcontainer to get and set information
	 * 
	 * @param gc
	 *            {@link GridContainer
	 */
	public SaveLoad(GridContainer gc) {

		this.gc = gc;
		for (int i = 0; i <= 5; i++) {
			gridArray[i] = gc.getSpecificGrid(i);
		}
	}

	/**
	 * Save all grids to a csv file given the 'fileName'
	 * 
	 * @param fileName
	 *            {@link String}
	 */
	public void save(String fileName) {

		gc.stop();

		JCheckBox check0, check1, check2, check3, check4, check5;

		check0 = new JCheckBox("Grid 1");
		check1 = new JCheckBox("Grid 2");
		check2 = new JCheckBox("Grid 3");
		check3 = new JCheckBox("Grid 4");
		check4 = new JCheckBox("Grid 5");
		check5 = new JCheckBox("Grid 6");

		JComponent[] inputs = new JComponent[] { check0, check1, check2,
				check3, check4, check5 };
		int result = JOptionPane.showConfirmDialog(null, inputs,
				"Select grids to save", JOptionPane.OK_CANCEL_OPTION);

		if (result == 0) {

			gridBools[0] = check0.isSelected();
			gridBools[1] = check1.isSelected();
			gridBools[2] = check2.isSelected();
			gridBools[3] = check3.isSelected();
			gridBools[4] = check4.isSelected();
			gridBools[5] = check5.isSelected();

			BufferedWriter bw;

			if (!fileName.matches("^.*\\.hex$")) {
				fileName = fileName + ".hex";
			}

			try {
				bw = new BufferedWriter(new FileWriter(fileName));

				for (int k = 0; k <= 5; k++) {
					if (gridBools[k] == true) {
						layerCount++;
					}
				}

				// store number of layers and base tempo at start of file
				bw.write("Layers: " + layerCount);
				bw.newLine();
				bw.write("Base tempo: " + ReactogonConstants.TEMPO);
				bw.newLine();

				// loop for all grids that were selected by the user
				// store the layer, tempo, instrument and start note
				// along with all the cells that have a tile present
				// x + y coords, type, direction
				for (int k = 0; k <= 5; k++) {
					if (gridBools[k] == true) {

						bw.write("Layer: " + k);
						bw.newLine();
						bw.write("Layer tempo: " + gridArray[k].getTempo());
						bw.newLine();
						bw.write("Layer instrument: " + gc.getInstrument(k));
						bw.newLine();
						bw.write("Layer start note: "
								+ gridArray[k].getStartNote());
						bw.newLine();

						for (int i = 0; i < GRID_HEIGHT; i++) {
							for (int j = 0; j < GRID_WIDTH; j++) {

								HexCell[][] hc = gridArray[k].getGrid();

								if (hc[i][j].getTile() != null) {

									// hexcell x, y, direction, type
									bw.write(i + "," + j + ","
											+ hc[i][j].getTile().getDirection()
											+ ","
											+ hc[i][j].getTile().getType()
											+ "," + hc[i][j].getTile().life);
									bw.newLine();
								}

							}
						}
					}
				}
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Load all tiles and grid preferences to their corresponding grids Goes
	 * through the file line by line using regex to gather and store the correct
	 * information
	 * 
	 * @param path
	 *            {@link String}
	 */
	public void open(String path) {

		gc.stop();

		boolean[] gridsToLoad = new boolean[6];
		int numberOfGridsToLoad = 0;
		int baseTempo = 100;

		BufferedReader buffread;
		try {
			buffread = new BufferedReader(new FileReader(path));
			String line = buffread.readLine();
			String[] lineArray;
			while (line != null) {
				if (line.matches("^(Layers):.*$")) {
					lineArray = line.split(" ");
					numberOfGridsToLoad = Integer.parseInt(lineArray[1]);
				} else if (line.matches("^(Layer):.*$")) {
					lineArray = line.split(" ");
					gridsToLoad[Integer.parseInt(lineArray[1])] = true;
				} else if (line.matches("^(Base tempo):.*$")) {
					lineArray = line.split(" ");
					baseTempo = Integer.parseInt(lineArray[2]);
				}
				line = buffread.readLine();
			}
			buffread.close();

			String[] labels = { "Don't load", "1", "2", "3", "4", "5", "6" };
			comboBox = new JComboBox<String>(labels);
			JComponent[] gridLabels = new JComponent[6];

			for (int i = 0; i <= 5; i++) {
				if (gridsToLoad[i] == true) {
					gridLabels[i] = new JLabel("Grid " + (i + 1));
				}
			}

			for (int i = 0; i <= 5; i++) {
				if (gridLabels[i] != null) {
					JComponent[] input = new JComponent[] { gridLabels[i],
							comboBox };
					int result = JOptionPane.showConfirmDialog(null, input,
							"Where to load?", JOptionPane.OK_CANCEL_OPTION);
					if (result == 0) {
						if (comboBox.getSelectedIndex() != 0) {
							buffread = new BufferedReader(new FileReader(path));
							line = buffread.readLine();
							gc.setCurrentActiveGrid(comboBox.getSelectedIndex() - 1);
							gc.clearGrid();
							while (line != null) {
								if (line.matches("^(Layer):.*$")) {
									lineArray = line.split(" ");
									if (Integer.parseInt(lineArray[1]) == i) {
										line = buffread.readLine();
										try {
											while (!line
													.matches("^(Layer):.*$")) {
												lineArray = line.split(" ");
												if (line.startsWith("Layer")) {
													if (line.matches("^(Layer tempo):.*$")) {
														gc.setTempoSpecificGrid(
																Integer.parseInt(lineArray[2]),
																comboBox.getSelectedIndex() - 1);
													} else if (line
															.matches("^(Layer instrument):.*$")) {
														gc.setInstrument(
																comboBox.getSelectedIndex() - 1,
																Integer.parseInt(lineArray[2]));
													} else if (line
															.matches("^(Layer start note):.*$")) {
														gc.setStartNote(
																comboBox.getSelectedIndex() - 1,
																Integer.parseInt((lineArray[3])));
													}
												} else {
													String[] tileString = line
															.split(",");
													HexCell[][] hc = gc
															.getSpecificGrid(
																	comboBox.getSelectedIndex() - 1)
															.getGrid();
													int x = Integer
															.parseInt(tileString[0]);
													int y = Integer
															.parseInt(tileString[1]);
													int direction = Integer
															.parseInt(tileString[2]);
													String type = tileString[3];
													int life = Integer
															.parseInt(tileString[4]);
													Tile tile = new Tile(type,
															direction);
													tile.life = life;
													if (type.matches("^(play).*$")) {
														gc.getSpecificGrid(
																comboBox.getSelectedIndex() - 1)
																.setTile(tile,
																		y, x);
													} else {
														hc[x][y].setTile(tile);
													}
												}
												line = buffread.readLine();
											}
										} catch (NullPointerException e) {

										}
									}
								}
								line = buffread.readLine();
							}
						}
					}
				}
			}
			buffread.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
