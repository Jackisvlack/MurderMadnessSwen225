package GUI;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Class to create a MainFrame and set the window settings
 * */
public class StartGUI {
	
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		
		// size of window
		frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width/2, Toolkit.getDefaultToolkit().getScreenSize().height/2);
		
		// center the window
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((int)(screenSize.getWidth()/2 - frame.getSize().getWidth()/2),
						  (int)(screenSize.getHeight()/2 - frame.getSize().getHeight()/2));
		
		// other window settings
		frame.setResizable(true);
		frame.setTitle("Murder Madness");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
}
