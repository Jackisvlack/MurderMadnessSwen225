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

           
            
            
            Collections.shuffle(weaponCards);
            Collections.shuffle(estateCards);
            Collections.shuffle(charCards);
            murderSet.add(weaponCards.get(0));
            murderSet.add(charCards.get(0));
            murderSet.add(estateCards.get(0));
          
            /**
             * Dummy Initialization (to be completed...)
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

            while (!weaponCards.isEmpty()){
                for (Player player : players){
                    player.cards.add(weaponCards.get(0));
                }
            }

            while (!estateCards.isEmpty()){
                for (Player player : players){
                    player.cards.add(estateCards.get(0));
                }
            }

            while (!characterCards.isEmpty()){
                for (Player player : players){
                    player.cards.add(characterCards.get(0));
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
