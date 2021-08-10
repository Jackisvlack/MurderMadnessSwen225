package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

class GameGUI extends JPanel implements ActionListener {
    private Game game;
    private JFrame frame;
    private int squarelWidth, squareHeight = 25;
    private int moves;


    public GameGUI (Game game, JFrame frame){
        this.game = game;
        this.frame = frame;
        repaint();
    }

    public void paint(Graphics g) { 
        super.paint(g);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = this.getSize().width;
		int y = this.getSize().height;
		Graphics2D gtd = (Graphics2D) g;
		

        drawBoard(gtd, x, y);
        displayCurrentPlayer(gtd);
        addMoveButtons(x, y);
        addButtons(x, y, gtd);
    }

    public void displayCurrentPlayer(Graphics2D gtd){
        String name = game.getCurrentPlayerName();
        gtd.setColor(Color.black);
        Font font = new Font("Verdana", Font.BOLD, 12);
        gtd.setFont(font);
        gtd.drawString(name + " it's your turn", 510, 20);
    }

    public void displayInformation(Graphics2D gtd, String string){
        String name = game.getCurrentPlayerName();
        gtd.setColor(Color.black);
        Font font = new Font("Verdana", Font.BOLD, 12);
        gtd.setFont(font);
        gtd.drawString(name + " it's your turn", 510, 20);
    }

    public void addButtons(int x, int y, Graphics2D gtd){
        JButton roll = new JButton("ROLL");
		roll.addActionListener(new ActionListener() {
        	        	
			@Override
			public void actionPerformed(ActionEvent e) {
				moves = game.roll();
                JOptionPane.showMessageDialog(null, "You have " + moves + " moves!" );
			} 
        });

        roll.setBounds(x-200, y-100-210, 100, 100);
		roll.setVisible(true);
		this.add(roll);
        roll.grabFocus();

        JButton guess = new JButton("GUESS");
		guess.addActionListener(new ActionListener() {
        	        	
			@Override
			public void actionPerformed(ActionEvent e) {
				
			} 
        });

        guess.setBounds(x-200, y-100-100, 100, 100);
		guess.setVisible(true);
		this.add(guess);
        guess.grabFocus();
    }

    public void addMoveButtons(int x, int y) {
		JButton north = new JButton("N");
		north.addActionListener(new ActionListener() {
        	        	
			@Override
			public void actionPerformed(ActionEvent e) {
				game.moveNorth(moves);
                repaint();
			} 
        });

        JButton west = new JButton("W");
		west.addActionListener(new ActionListener() {
        	        	
			@Override
			public void actionPerformed(ActionEvent e) {
				game.moveWest(moves);
                repaint();
			} 
        });

        JButton east = new JButton("E");
		east.addActionListener(new ActionListener() {
        	        	
			@Override
			public void actionPerformed(ActionEvent e) {
				game.moveEast(moves);
                repaint();
			} 
        });

        JButton south = new JButton("S");
		south.addActionListener(new ActionListener() {
        	        	
			@Override
			public void actionPerformed(ActionEvent e) {
				game.moveSouth(moves);
                repaint();
			} 
        });

        north.setBounds(x/2-100, y-100-100, 50, 50);
		north.setVisible(true);
		this.add(north);
        north.grabFocus();

        west.setBounds(x/2-150, y-100-50, 50, 50);
		west.setVisible(true);
		this.add(west);
        west.grabFocus();

        east.setBounds(x/2-50, y-100-50, 50, 50);
		east.setVisible(true);
		this.add(east);
        east.grabFocus();

        south.setBounds(x/2-100, y-100-50, 50, 50);
		south.setVisible(true);
		this.add(south);
        south.grabFocus();
    
    }

    public void drawBoard(Graphics2D gtd, int x, int y){
        Board board = game.getBoard();
        Location[][] locations = board.getLocationSet();

        int left = 10;
        int top = 10;
        for (int i = 0; i < 24; i++){
            for (int j = 0; j < 24; j++){
                if(locations[i][j].getTypeIdentifier().equals("---")){
                    gtd.setColor(Color.white);
                    gtd.fillRect(left, top, 20, 20);
                    gtd.setColor(Color.black);
                    gtd.drawRect(left, top, 20, 20);
                    left += 20;
                } else if (locations[i][j].getTypeIdentifier().equals("[ ]")){
                    gtd.setColor(Color.white);
                    gtd.fillRect(left, top, 20, 20);
                    gtd.setColor(Color.white);
                    gtd.drawRect(left, top, 20, 20);
                    left += 20;
                } else {
                    gtd.setColor(Color.blue);
                    gtd.fillRect(left, top, 20, 20);
                    gtd.setColor(Color.black);
                    gtd.drawRect(left, top, 20, 20);
                    left += 20;
                }
                
            }
            top += 20;
            left = 10;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }

}