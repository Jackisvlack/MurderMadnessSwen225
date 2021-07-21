import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class Game {
    Board board;
    ArrayList<Player> players;

    public Game() {
    }

    public void startGame() throws IOException {
        board = new Board();
        board.drawBoard();
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            int numplayers  = br.read();
            players = new ArrayList<>();


            /**
             * Dummy Initialization (to be completed...)
             */
            HashSet<Card> cards = new HashSet<Card>();
            cards.add(new weaponCard("broom"));
            players.add(new Player("player1", "bert", cards));
            for (int i = 0 ; i < numplayers ; i++){
                players.add(new Player("Player" + i, "lucy", cards  ));
            }
        } catch (IOException e) {
            throw e;
        }
    }

    public static void main(String... args) throws IOException {
        Game newGame = new Game();
        newGame.startGame();
    }
}
