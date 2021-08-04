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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Menu extends JPanel implements ActionListener {
	public boolean clicked = false;
	
	public Menu() {
		repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.removeAll();

		GradientPaint col = new GradientPaint(0, 0, new Color(0, 0, 0), 0, screenSize.height,  new Color(255, 255, 255));
		Graphics2D gtd = (Graphics2D) g;	
		gtd.setPaint(col);
        gtd.fillRect(0, 0, screenSize.width, screenSize.height);
        
        Font font = new Font("Verdana", Font.BOLD, 40);
        gtd.setFont(font);
        gtd.setColor(Color.WHITE);
        gtd.drawString("MURDER - MADNESS", this.getSize().width/2-220, this.getSize().height/3);
        
        
        JButton start = new JButton("START THE MADNESS..");
        start.addActionListener(new ActionListener() {
        	        	
			@Override
			public void actionPerformed(ActionEvent e) {
				getInstructions();
			} 
			
        });
		start.setBounds(this.getSize().width/2-100, this.getSize().height/2, 200, 50);
		start.setVisible(true);
		this.add(start);
		start.grabFocus();
	}
	
	public void getInstructions() {
		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
		Instructions ins = new Instructions();
		ins.setLocation(0,0);
		ins.setSize(frame.getSize());
		ins.setBackground(new Color(102, 204, 255));
		ins.setVisible(true);
		frame.remove(this);
		frame.add(ins);
		ins.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {}

}
