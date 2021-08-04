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
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

public class Instructions extends JPanel implements ActionListener {
	public Instructions() {
		repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.removeAll();

		GradientPaint col = new GradientPaint(0, 0, new Color(201, 13, 0), 0, screenSize.height,  new Color(0, 0, 0));
		Graphics2D gtd = (Graphics2D) g;	
		gtd.setPaint(col);
        gtd.fillRect(0, 0, screenSize.width, screenSize.height);
        
        Font font = new Font("Verdana", Font.BOLD, 40);
        gtd.setFont(font);
        gtd.setColor(Color.WHITE);
        gtd.drawString("MURDER - MADNESS", this.getSize().width/2-220, this.getSize().height/7);
        
        font = new Font("Verdana", Font.BOLD, 20);
        gtd.setFont(font);
        gtd.drawString("INSTRUCTIONS/RULES", this.getSize().width/2-220, this.getSize().height/5);
        
        font = new Font("Verdana", Font.BOLD, 13);
        gtd.setFont(font);
        
        ArrayList<String> rules = new ArrayList<>(Arrays.asList(
        		"Players cannot move through walls, or other players.",
        		"A players goal is to get in to an estate, only in an estate can a guess attempt be made.\n",
        		"One of the 4 characters is a murderer, a player may move around the board trying to get",
        		"in to one of the five estates. Once inside an estate, a player can guess the murderer\n",
        		"and their murder weapon (choosing the respective cards) and whichever estate they\n",
        		"are in will be chosen as the third aspect of that guess. If the accused player and\n",
        		"murder weapon are not already inside that estate, they will be moved to sed estate.\n",
        		"Players then go around refuting the current players accusation by presenting cards which\n",
        		"match the guessed cards. If a refutation can be made, it must. If a player has more\n",
        		"than one elegible card, it is up to the player to choose between them. If none of the\n",
        		"players are able to present a refutation card, the current players accusation is likely ",
        		"close to a winning guess. If a player guesses the exact circumstances of the murder, \n",
        		"they win. however, if a player does not get the guess correct, that player is excluded\n",
        		"for the rest of the game from making accusations and winning. An excluded player can still",
        		"present refuation cards. Should every player fail to guess the murder circumstances, \n",
        		"the murderer has won :O\""));
        for (int i = 0; i < rules.size(); i++) {
        	gtd.drawString(rules.get(i), this.getSize().width/28, this.getSize().height/4+(14*i));
        }
        
        
        JButton start = new JButton("I UNDERSTAND, LET'S GO!");
        start.addActionListener(new ActionListener() {
        	        	
			@Override
			public void actionPerformed(ActionEvent e) {
				
			} 
			
        });
		start.setBounds(this.getSize().width-300, this.getSize().height-150, 200, 50);
		start.setVisible(true);
		this.add(start);
		start.grabFocus();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
