package hex;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Creates instruction manual frame
 *
 */
public class HelpWindow implements ActionListener {

	private JButton about = null, whatswhat = null, tiles = null, controls = null, grids = null, tempo = null, saveload = null, preferences = null, shortcuts = null;
	private JLabel image = null;
	private ImageIcon ic = new ImageIcon("res/instructionimages/logoTitle.png");

	/**
	 * Help menu / instruction manual 
	 * Sets the JButtons and action listeners for each item
	 */
	public HelpWindow() {
		JFrame frame = new JFrame();
		frame.setTitle("HEX Insruction Manual");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(new Dimension(600, 700));
		frame.setLocation(435, 200);
		
		JPanel panel2 = new JPanel();
		JScrollPane scroll = new JScrollPane(panel2);
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(6, 5, 5, 5);
		
		JSplitPane jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel, scroll);
		
		
		about = new JButton("About");
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(about, gbc);
		
		whatswhat = new JButton("What's what");
		gbc.gridx = 1;
		gbc.gridy = 0;
		panel.add(whatswhat, gbc);

		tiles = new JButton("Tiles");
		gbc.gridx = 2;
		gbc.gridy = 0;
		panel.add(tiles, gbc);
		
		controls = new JButton("Controls");
		gbc.gridx = 3;
		gbc.gridy = 0;
		panel.add(controls, gbc);
		
		grids = new JButton("Grids");
		gbc.gridx = 4;
		gbc.gridy = 0;
		panel.add(grids, gbc);
		
		tempo = new JButton("Tempo");
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(tempo, gbc);
		
		saveload = new JButton("Save & Load");
		gbc.gridx = 1;
		gbc.gridy = 1;
		panel.add(saveload, gbc);
		
		preferences = new JButton("Preferences");
		gbc.gridx = 2;
		gbc.gridy = 1;
		panel.add(preferences, gbc);
		
		shortcuts = new JButton("Shortcuts");
		gbc.gridx = 3;
		gbc.gridy = 1;
		panel.add(shortcuts, gbc);
		
		image = new JLabel(ic);
		//gbc.gridx = 0;
		//gbc.gridy = 2;
		panel2.add(image);
		
		frame.add(jsp);
		
		about.addActionListener(this);
		whatswhat.addActionListener(this);
		tiles.addActionListener(this);
		controls.addActionListener(this);
		grids.addActionListener(this);
		tempo.addActionListener(this);
		saveload.addActionListener(this);
		preferences.addActionListener(this);
		shortcuts.addActionListener(this);
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == about){
			ic = new ImageIcon("res/instructionimages/about.png");
			image.setIcon(ic);
		}else if(e.getSource() == whatswhat){
			ic = new ImageIcon("res/instructionimages/whatswhat.png");
			image.setIcon(ic);
		}else if(e.getSource() == tiles){
			ic = new ImageIcon("res/instructionimages/tiles.png");
			image.setIcon(ic);
		}else if(e.getSource() == controls){
			ic = new ImageIcon("res/instructionimages/controls.png");
			image.setIcon(ic);
		}else if(e.getSource() == grids){
			ic = new ImageIcon("res/instructionimages/grids.png");
			image.setIcon(ic);
		}else if(e.getSource() == tempo){
			ic = new ImageIcon("res/instructionimages/tempo.png");
			image.setIcon(ic);
		}else if(e.getSource() == saveload){
			ic = new ImageIcon("res/instructionimages/saveload.png");
			image.setIcon(ic);
		}else if(e.getSource() == preferences){
			ic = new ImageIcon("res/instructionimages/preferences.png");
			image.setIcon(ic);
		}else if(e.getSource() == shortcuts){
			ic = new ImageIcon("res/instructionimages/shortcuts.png");
			image.setIcon(ic);
		}
	}
	
	
}