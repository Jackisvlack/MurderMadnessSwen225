import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class Game {
    private static Board board;
    ArrayList<Player> players;
    Guess murderCircumstance;
    ArrayList<String> characters;
    ArrayList<Card> weaponCards;
    ArrayList<Card> characterCards;
    ArrayList<Card> estateCards; 
    Player currentPlayer;
    boolean solved = false;

    public Game() {
        
        while (!solved){
            turn();
        }
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
             * Create all of the cards
             * {"lucilla", "bert", "maline", "percy"};
             */
            characters.add("lucilla");
            characters.add("bert");
            characters.add("maline");
            characters.add("percy");
            characterCards.add(new Card("lucilla"));
            characterCards.add(new Card("bert"));
            characterCards.add(new Card("maline"));
            characterCards.add(new Card("percy"));
            weaponCards.add(new Card("broom"));
            weaponCards.add(new Card("scissors"));
            weaponCards.add(new Card("knife"));
            weaponCards.add(new Card("shovel"));
            weaponCards.add(new Card("ipad"));
            estateCards.add(new Card("manic manor"));
            estateCards.add(new Card("villa celia"));
            estateCards.add(new Card("haunted house"));
            estateCards.add(new Card("calamity castle"));
            estateCards.add(new Card("calamity castle"));

            /**
             * Randomizes each set of card and takes one of each for the murder set
             */
            Collections.shuffle(weaponCards);
            Collections.shuffle(estateCards);
            Collections.shuffle(characterCards);
            Card murderWeapon = (weaponCards.get(0));
            Card murderCharacter = (characterCards.get(0));
            Card murderEstate = (estateCards.get(0));
            murderCircumstance = new Guess(murderEstate.getName(), murderWeapon.getName(), murderCharacter.getName());
          
            /**
             * Combine all cards (correct me if I'm wrong about this rule interpretation:
             * "The remaining weapon, estate and character cards are then combined and distributed at random to players.
                Some players may end up with more cards than others but only at most one more"
                -At the moment this rule is followed due to: Some players may end up with more cards than others but only at most one more
             */
            ArrayList<Card> cards = new ArrayList<Card>();
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

            /**
             * Selects the player who starts at random
             */
            Collections.shuffle(players);
            currentPlayer = players.get(0);

        } catch (IOException e) {
            throw e;
        }
    }

    public void turn(){
     

    }

    /**
     * 
     */
    public void move(){

    }

    /**
     * 
     */
    public void makeGuess(){

    }

    /**
     * Method to simlate two 6 sided dice
     * @return int: the sum of two dice
     */
    public int roll(){
        int diceOne = (int) (Math.random()*6 + 1);
        int diceTwo = (int) (Math.random()*6 + 1);
        return diceOne + diceTwo;
    }

    public static void main(String... args) throws IOException {
        Game newGame = new Game();
        newGame.startGame();
        board.drawBoard();
    }
}
