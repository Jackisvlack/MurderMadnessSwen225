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

class GameGUI extends JPanel implements ActionListener {
    private Game game;
    private JFrame frame;
    private int squarelWidth, squareHeight = 25;

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