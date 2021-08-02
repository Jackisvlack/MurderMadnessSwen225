package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

public class GamePanel extends javax.swing.JPanel implements ActionListener {
	
	Timer gameTimer;
	
	public GamePanel() {
		
		gameTimer = new Timer();
		gameTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				repaint();
			}
			
		}, 0, 10);
		
		repaint();
	}
	
	//TODO: Just experimenting with things, feel free to take this out or change it.
	/**
	 * method to paint the background of the graphics pane
	 * */
	public void paint(Graphics g) {
		super.paint(g);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// Create the gradient from black to white
		GradientPaint col = new GradientPaint(0, 0, new Color(0, 0, 0), 0, Toolkit.getDefaultToolkit().getScreenSize().height,  new Color(255, 255, 255));
		// Create a 2D graphics object from the Graphics object passed into function
		Graphics2D gtd = (Graphics2D) g;	
		// set paint to gradient and then fill the screen to a specified size
		gtd.setPaint(col);
        gtd.fillRect(0, 0, screenSize.width, screenSize.height);
        
        for (int i = 0; i < 30; i++) {
        	if (i % 2 == 0) {
		        gtd.setColor(Color.RED);
		        Font font = new Font("Verdana", Font.BOLD, 12+i);
		        gtd.setFont(font);
		        gtd.drawString("MURDER - MADNESS", this.size().width/2-65-(5*i), 0+(10*i));
        	} else {
        		gtd.setColor(Color.WHITE);
		        Font font = new Font("Verdana", Font.BOLD, 12+i);
		        gtd.setFont(font);
		        gtd.drawString("MURDER - MADNESS", this.size().width/2-65-(5*i), 0+(10*i));
        	}
        }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void keyPressed(KeyEvent e) {
//		if (String.valueOf(e.getKeyChar()).equalsIgnoreCase("a")) { repaint();  System.out.println("Hi"); }
//		if (String.valueOf(e.getKeyChar()).equalsIgnoreCase("w")) player.keyUp = true;
//		if (String.valueOf(e.getKeyChar()).equalsIgnoreCase("d")) {player.keyRight = true; this.direction = 1;}
//		if (String.valueOf(e.getKeyChar()).equalsIgnoreCase("s")) player.keyDown = true; 
	}

	public void keyReleased(KeyEvent e) {
//		if (String.valueOf(e.getKeyChar()).equalsIgnoreCase("a")) {}
//		if (String.valueOf(e.getKeyChar()).equalsIgnoreCase("w")) player.keyUp = false;
//		if (String.valueOf(e.getKeyChar()).equalsIgnoreCase("d")) player.keyRight = false;
//		if (String.valueOf(e.getKeyChar()).equalsIgnoreCase("s")) player.keyDown = false;
	}

}
