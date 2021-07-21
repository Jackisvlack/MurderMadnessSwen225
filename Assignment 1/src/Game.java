import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class Game {
    Board board;
    ArrayList<Player> players;
    HashSet<Card> murderSet;
    ArrayList<String> characters = {"lucilla", "bert", "maline", "percy"};
    ArrayList<weaponCard> weaponCards = {new weaponCard("broom"), new weaponCard("scissors"), new weaponCard("knife"), new weaponCard("shovel"), new weaponCard("ipad")};
    ArrayList<charCard> characterCards = {new charCard("lucilla"), new charCard("bert"), new charCard("maline"), new charCard("percy")};
    ArrayList<estateCard> estateCards = {new esateCard("manic manor"), new esateCard("villa celia"), new esateCard("haunted house"), new esateCard("calamity castle"),new esateCard("peril palace")};

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
             * Randomizes each set of card and takes one of each for the murder set
             */
            Collections.shuffle(weaponCards);
            Collections.shuffle(estateCards);
            Collections.shuffle(characterCards);
            murderSet.add(weaponCards.get(0));
            murderSet.add(charCards.get(0));
            murderSet.add(estateCards.get(0));
          
            /**
             * Combine all cards (correct me if I'm wrong about this rule interpretation:
             * "The remaining weapon, estate and character cards are then combined and distributed at random to players.
                Some players may end up with more cards than others but only at most one more"
                -At the moment this rule is followed due to: Some players may end up with more cards than others but only at most one more
             */
            HashSet<Card> cards = new HashSet<Card>();
            cards.addAll(weaponCards);
            cards.addAll(estateCards);
            cards.addAll(characterCards);
            Collections.shuffle(cards);
            
            Collections.shuffle(characters);
            players.add(new Player("player1", characters.get(0), new HashSet<Card>()));
            Collections.shuffle(characters);
            players.add(new Player("player1", characters.get(0), new HashSet<Card>()));
            for (int i = 3 ; i <= numplayers ; i++){
                Collections.shuffle(characters);
                players.add(new Player("Player" + i, characters.get(0), new HashSet<Card>()));
            }

            while (!cards.isEmpty()){
                for (Player player : players){
                    player.cards.add(cards.get(0));
                }
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
