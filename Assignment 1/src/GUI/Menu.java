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
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Menu extends JPanel implements ActionListener {
	
	Timer gameTimer;
	
	public Menu() {
		repaint();
//		gameTimer = new Timer();
//		gameTimer.schedule(new TimerTask() {
//
//			@Override
//			public void run() {
//				repaint();
//			}
//			
//		}, 0, 10);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		if (this.getComponentCount() > 0) {
			for (int i = 0; i < this.getComponentCount(); i++) {
				this.remove(i);
			}
		}

		GradientPaint col = new GradientPaint(0, 0, new Color(0, 0, 0), 0, screenSize.height,  new Color(255, 255, 255));
		Graphics2D gtd = (Graphics2D) g;	
		gtd.setPaint(col);
        gtd.fillRect(0, 0, screenSize.width, screenSize.height);
        
        Font font = new Font("Verdana", Font.BOLD, 40);
        gtd.setFont(font);
        gtd.setColor(Color.WHITE);
        gtd.drawString("MURDER - MADNESS", this.getSize().width/2-220, this.getSize().height/3);
        
        
        JButton start = new JButton("START THE MADNESS..");
		start.setBounds(this.getSize().width/2-100, this.getSize().height/2, 200, 50);
		start.setVisible(true);
		start.grabFocus();
		this.add(start);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
