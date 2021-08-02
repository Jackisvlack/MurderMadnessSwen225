package GUI;

import java.awt.Color;

/**
 * Class to Manage the instance of GamePanel
 * */
public class MainFrame extends javax.swing.JFrame {
	public MainFrame() {
		// create an instance of GamePanel
		GamePanel panel = new GamePanel();
		
		// set up the settings of the GamePanel
		panel.setLocation(0,0);
		panel.setSize(this.getSize());
		panel.setBackground(new Color(0, 0, 0));
		panel.setVisible(true);
		
		// add it to the MainFrame
		this.add(panel);
		
		//TODO: this feature may not be needed, but will keep it here for now.
		// start keyListener
		addKeyListener(new KeyChecker(panel));
	}
}
