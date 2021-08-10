package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

class GameGUI extends JPanel implements ActionListener {
    private Game game;
    private JFrame frame;
    private JPanel panel;
    private int squarelWidth, squareHeight = 25;

    public GameGUI (Game game, JFrame frame){
        this.game = game;
        this.frame = frame;
    }

    public void redraw(){
        Board board = game.getBoard();
        Location[][] locations = board.getLocationSet();
        panel = new JPanel();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }

}