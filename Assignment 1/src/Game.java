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
    Guess murderCircumstance;
    ArrayList<String> characters;
    ArrayList<WeaponCard> weaponCards;
    ArrayList<CharCard> characterCards;
    ArrayList<EstateCard> estateCards; 

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
             * Create all of the cards
             * {"lucilla", "bert", "maline", "percy"};
             */
            characters.add("lucilla");
            characters.add("bert");
            characters.add("maline");
            characters.add("percy");
            characterCards.add(new CharCard("lucilla"));
            characterCards.add(new CharCard("bert"));
            characterCards.add(new CharCard("maline"));
            characterCards.add(new CharCard("percy"));
            weaponCards.add(new WeaponCard("broom"));
            weaponCards.add(new WeaponCard("scissors"));
            weaponCards.add(new WeaponCard("knife"));
            weaponCards.add(new WeaponCard("shovel"));
            weaponCards.add(new WeaponCard("ipad"));
            estateCards.add(new EstateCard("manic manor"));
            estateCards.add(new EstateCard("villa celia"));
            estateCards.add(new EstateCard("haunted house"));
            estateCards.add(new EstateCard("calamity castle"));
            estateCards.add(new EstateCard("calamity castle"));

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

            
        } catch (IOException e) {
            throw e;
        }
    }

    public static void main(String... args) throws IOException {
        Game newGame = new Game();
        newGame.startGame();
        Board board = new Board();
        board.drawBoard();
    }
}
