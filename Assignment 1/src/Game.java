import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Game {
    private static Board board;
    ArrayList<Player> players = new ArrayList<>();
    Guess murderCircumstance;
    ArrayList<String> characters = new ArrayList<>();
    ArrayList<Card> weaponCards = new ArrayList<>();
    ArrayList<Card> characterCards = new ArrayList<>();
    ArrayList<Card> estateCards = new ArrayList<>(); 
    Player currentPlayer;
    boolean solved = false;

    public Game() {
        

    }
    //TODO need to look at this class, could not get to work in its native way, had to comment out the try/catch
    //TODO had to also work above the while loop on lines 104 to 108. Seems to get stuck in this loop
    public void startGame() throws IOException {
        board = new Board();
        board.drawBoard();
        /*
        try {*/
            /*
        	InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            */
            int numplayers  = 4;/*br.read();*/
            
            
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
            characterCards.add(new Card("malina"));
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
          

            ArrayList<Card> cards = new ArrayList<Card>();
            cards.addAll(weaponCards);
            cards.addAll(estateCards);
            cards.addAll(characterCards);
            Collections.shuffle(cards);
            
            //Note: have changed so that locations hold players, easier to have board read that info from Location rater than Players


            Collections.shuffle(characters); //TODO must set the starting locations setHasPlayer to true for all starting locs of players 
            players.add(new Player("player1", characters.get(0), new HashSet<Card>())); 
            players.add(new Player("player2", characters.get(0), new HashSet<Card>()));
            for (int i = 3 ; i <= numplayers ; i++){
                players.add(new Player("Player" + i, characters.get(0), new HashSet<Card>())); //TODO must fix locations of these characters, currently is null
            }

            board.placeCharacters(players);
            board.drawBoard();
            
            /**
             * Selects the player who starts at random
             */
            Collections.shuffle(players);
            currentPlayer = players.get(0);
            //turn(); // initiates turn, here temporarily 
            
            //TODO fix while loop, gets stuck as list of cards never empties
           // while (!cards.isEmpty()){
           //     for (Player player : players) {
           //         player.cards.add(cards.get(0));
           //     }
           // }
            
            /**
             * Selects the player who starts at random
             */
            
            
            //Board will manage players, as it is boards responsibility to draw. Locations now hold players.
            

        /*} catch (IOException e) {
            throw e;
        }*/
    }
    
    /**
     * A method to 
     * */
    public void turn(){
    	int moves = 0;
    	String line = "";
    	
    	// greet player
    	System.out.println("Hello, " + currentPlayer.getPlayerName());
    	System.out.println("Please roll the dice when ready by typing 'roll'");
    	
    	// wait
    	getInput();
    	
    	// roll
    	System.out.println("Rolling...");
    	moves = roll();
    	System.out.println("Moves available: " + moves);
    	
    	// present instructions / get move order
    	System.out.println("North = Up - East = Left - West = Right - South = Down");
    	System.out.println("To move, type the number of squares to move, space, the first letter of a given direction");
    	System.out.println("Example: 5 N - 5 squares North, 3 E, 3 squares East - not case sensitive");
    	line = getInput();
    	
    	// length error check: line.length() cannot be shorter than 3 and longer 
    	// than 4 (nature of the two dice total & one character direction input)
    	if (line.length() < 3 || line.length() > 4) {
    		System.out.println("Error recognizing distance or direction, try again.");
    		getInput(); // temporary solution to catching incorrect input, not actual solution
    	}
    	
    	move(line);
    }
    
    /**
     * Simple helper function to pause game and wait for input.
     * */
    public String getInput() {
    	Scanner userInput = new Scanner(System.in);
    	String line = "";
    	line = userInput.nextLine();
    	return line;
    }
    
    /**
     * Simple helper method that checks validity of order format
     * */
    public void checkOrderValidity(int moves, String direction) {
    	List<String> dirList = Arrays.asList("S", "W", "E", "N", "s", "w", "e", "n");
    	if (moves < 1 || moves > 12 || !dirList.contains(direction)) {
    		System.out.println("Please type a valid distance and direction: ");
    		move(getInput());
    	} 
    }

    /**
     * 
     */
    public void move(String mOrder){
    	int moves = 0;
    	String direction = "";
    	
    	if (mOrder.length() == 3) {
    		moves = Integer.valueOf(String.valueOf(mOrder.charAt(0)));
    		direction = String.valueOf(mOrder.charAt(2));
    	} else {
    		moves = Integer.valueOf(mOrder.substring(0,2));
    		direction = String.valueOf(mOrder.charAt(3));
    	}
    	
    	checkOrderValidity(moves, direction);
    	
    	for (Player p : players) {
    		System.out.println(p.charName);
    	}
    	
    	// Cannot walk through walls or grey areas
        
    	
    	// if on estate entrance, player is in the estate
    	
    	// Preemptive check to see if the square player
    	// has chosen to move to is valid place for player or
    	// if the place chosen is estate entrance or
    	// if move sequence is valid
    }

    /**
     * 
     */
    public void makeGuess(){
    	// Guess is made when an estate is entered
    	// Guess comprised of player choosing two cards
    	// Weapon and Player - Estate entered is 
    	// a given card in the guess
    	
    	// Player makes guess, then each player starting one after
    	// the player that made the guess, going around all players
    	
    	// Guess is refuted by producing a card that matches 
    	// one of the suggest murder circumstances
    	
    	// if a refutation can be made, it must
    	
    	// if player has choice of two refutation cards, they can choose
    	
    	// if solve guess matches exactly the cards chosen as the murder
    	// circumstances, they win. If not, the player is excluded from making futher
    	// Guesses
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
    }

}
