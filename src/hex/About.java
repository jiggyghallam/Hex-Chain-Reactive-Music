package hex;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * About menu item
 *
 */
public class About {
	
	private JLabel logo, credits, george, ryan, jon, michael, stevie, krystal, space, copyright;

	
	/**
	 * Constructor to create the about menu
	 */
	public About(){
		JFrame frame = new JFrame();
		frame.setTitle("About HEX");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(new Dimension(400, 400));
		frame.setLocation(435, 200);
		
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(6, 5, 5, 5);
		
		ImageIcon image = new ImageIcon("res/images/hexLogo.png");
		
		logo = new JLabel(image);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(logo, gbc);

		credits = new JLabel("Credits:");
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(credits, gbc);
		
		george = new JLabel("George Hallam");
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(george, gbc);
		
		ryan = new JLabel("Ryan Fulton");
		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(ryan, gbc);
		
		jon = new JLabel("Jon Sherry");
		gbc.gridx = 0;
		gbc.gridy = 4;
		panel.add(jon, gbc);
		
		michael = new JLabel("‎Michael Tawafig");
		gbc.gridx = 0;
		gbc.gridy = 5;
		panel.add(michael, gbc);
		
		krystal = new JLabel("Da Huo");
		gbc.gridx = 0;
		gbc.gridy = 6;
		panel.add(krystal, gbc);
		
		stevie = new JLabel("Stevie Cooke");
		gbc.gridx = 0;
		gbc.gridy = 7;
		panel.add(stevie, gbc);
		
		space = new JLabel("   ");
		gbc.gridx = 0;
		gbc.gridy = 8;
		panel.add(space, gbc);
		
		copyright = new JLabel("\u00a9 Copyright 2013. All rights reserved.");
		gbc.gridx = 0;
		gbc.gridy = 9;
		panel.add(copyright, gbc);
		
		
		frame.add(panel);
		
	}
}
