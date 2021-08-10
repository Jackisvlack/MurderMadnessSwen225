package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JPanel;

class GameGUI extends JPanel implements ActionListener {
    private Game game;
    private JPanel frame;

    public GameGUI (Game game){
        this.setVisible(true);
        this.setSize(500, 500);
        this.game = game;
        redraw();
    }

    public void redraw(){
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }

}