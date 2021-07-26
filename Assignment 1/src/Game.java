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
import java.util.concurrent.TimeUnit;

public class Game {
    private static Board board;
    ArrayList<Player> players = new ArrayList<>();
    Guess murderCircumstance;
    ArrayList<Card> weaponCards = new ArrayList<>();
    ArrayList<Card> characterCards = new ArrayList<>();
    ArrayList<Card> estateCards = new ArrayList<>(); 
    Player currentPlayer;
    boolean solved = false;
	private int movesLeft;
	
	public enum Characters {
		lucilla,
		bert,
		malina,
		percy
	}

    public Game() {
        

    }



    //TODO need to look at this class, could not get to work in its native way, had to comment out the try/catch
    //TODO had to also work above the while loop on lines 104 to 108. Seems to get stuck in this loop
    public void startGame(int np) throws IOException {
        board = new Board();
        /*
        try {*/
            /*
        	InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            */
            int numplayers  = np;/*br.read();*/
            
            
            players = new ArrayList<>();

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
            
            players.add(new Player("player1", String.valueOf(Characters.lucilla), new HashSet<Card>())); 
            players.add(new Player("player2", String.valueOf(Characters.bert), new HashSet<Card>()));
            players.add(new Player("player3", String.valueOf(Characters.malina), new HashSet<Card>()));
            players.add(new Player("player4", String.valueOf(Characters.percy), new HashSet<Card>()));
            


            /**
             * Sets starting locations of players
             */
            board.placeCharactersStart(players);
            board.drawBoard();
                        
            int c = 0;
            for (int i = 0; i < cards.size()-1; i++) {
            	if (c == players.size()-1) { c = 0; }
            	players.get(c).cards.add(cards.get(i));
            	c++;
            }
            
            
            
            /**
             * Selects the player who starts at random
             */
            Collections.shuffle(players);
            currentPlayer = players.get(0);
            turn(); 
            
        /*} catch (IOException e) {
            throw e;
        }*/
    }
    
    public void wait(int sec) {
    	try {
			TimeUnit.SECONDS.sleep(sec);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void startScreen() {
    	System.out.println("Welcome to Murder Madness!");
    	System.out.println("Loading...");
    	wait(1);
    	System.out.println(
    			"------------------\n"
    			+"|#----------------|\n"
    			+"------------------"
    			);
    	wait(1);
    	System.out.println("Loading...");
    	System.out.println(
    			"------------------\n"+
    			"|###--------------|\n"+
    			"------------------"
    			);
    	wait(1);
    	System.out.println("Loading...");
    	System.out.println(
    			"------------------\n"+
    			"|########---------|\n"+
    			"------------------"
    			);
    	wait(1);
    	System.out.println("Loading...");
    	System.out.println(
    			"------------------\n"+
    			"|#################|\n"+
    			"------------------"
    			);
    	wait(1);
    	System.out.println(
    	"				#################################\n"
       +"				#  M U R D E R - M A D N E S S  #\n"
       +"				#################################\n");
    	
    	System.out.println(
				   "                                  _____________________________\r\n"
				   + "                           _.-''``------------------------|`. |``''--..__\r\n"
				   + "                      _.-'` ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' | : |          ``'';';--..__\r\n"
				   + "                 _.-'`                                    | : |         '   :';       ```';\r\n"
				   + "            _.-'`                           ________/\\_/\\_|.'_|_       '   :';           /\r\n"
				   + "       _.-'`                         _.-''``                    ``''--:.__;';           _|\r\n"
				   + "     .'`                        _.-'`                                     `'`''-._     /\r\n"
				   + "   .`                       _.-'                                                  `'-./\r\n"
				   + " .'                    _.-'`\r\n"
				   + "/               __..-'`\r\n"
				   + "``'''----'''````"
    			);
    	wait(2);
    	System.out.println(
    	    	"				#################################\n"
    	       +"				#    P R E S S  - E N T E R     #\n"
    	       +"				#################################\n");
    	getInput();
    	System.out.println("please enter the number of players playing (minimum 2, maximum 4): ");
    	String players = getInput();
    	System.out.println(
    			"##########################################################################################################################################\n"+
    			"#		R	U	L	E	S                                                                                        #\n"+
    			"#  Players cannot move through walls (+++), or other players (P,L,M,B)                                                                   #\n"+
    			"#  A players goal is to get in to an estate, only in an estate can a guess attempt be made.                                              #\n"+
    			"#  One of the 4 characters is a murderer, a player may move around the board trying to get in to one of the five estates.                #\n"
    			+ "#  Once inside an estate, a player can guess the murderer and their murder weapon (choosing the respective cards) and which-             #\n"
    			+ "#  ever estate they are in will be chosen as the third aspect of that guess.                                                             #\n"
    			+ "#  If the accused player and murder weapon are not already inside that estate, they will be moved to sed estate.                         #\n"
    			+ "#  Players then go around refuting the current players accusation by presenting cards which match the guessed cards.                     #\n"
    			+ "#  If a refutation can be made, it must. If a player has more than one elegible card, it is up to the player to choose between them.     #\n"
    			+ "#  If none of the players are able to present a refutation card, the current players accusation is likely close to a winning guess.      #\n"
    			+ "#  If a player guesses the exact circumstances of the murder, they win. however, if a player does not get the guess correct, that player #\n"
    			+ "#  is excluded for the rest of the game from making accusations and winning. An excluded player can still present refuation cards.       #\n"
    			+ "#  Should every player fail to guess the murder circumstances, the murderer has won :O                                                   #\n"
    			+ "##########################################################################################################################################\n"
    			);
    	System.out.println("When everyone is ready to play, press ENTER:");
    	getInput();
    	int np = 2;
    	try {
    		np = Integer.valueOf(players);
    	} catch (NumberFormatException e) {
    		System.out.println("Not a real number! Defaulting to two players.");
    	}
    	try {
			startGame(np);
		} catch (IOException n) {
			
		}
    }
    
    /**
     * A method that starts the currentPlayers turn
     * Rolls dice
     * gives instructions
     * gives move order to checkLine, if valid passes on to move method
     * */
    public void turn(){
    	
    	this.movesLeft = 0;
    	String line = "";
    	
    	// greet player
    	System.out.println("Hello, " + currentPlayer.getPlayerName());
    	System.out.println("Please roll the dice when ready by typing 'roll'");
    	
    	// wait
    	getInput();
    	
    	// roll
    	System.out.println("Rolling...");
    	movesLeft = roll();
    	System.out.println("Moves available: " + movesLeft);
    	
    	// present instructions / get move order
    	System.out.println("North = Up - East = Left - West = Right - South = Down");
    	System.out.println("To move, type the number of squares to move, space, the first letter of a given direction");
    	System.out.println("Example: 5 N - 5 squares North, 3 E, 3 squares East - not case sensitive");
    	line = getInput();
    	
    	checkLine(line);
    }
    
    /**
     * method to check line length, as the line can only be either 3 or 4 chars in length
     * */
    public void checkLine(String line) {
    	if (line.length() < 3 || line.length() > 4) {
    		System.out.println("Error recognizing distance or direction, try again.");
    		checkLine(getInput());
    	}
    	move(line);
;    }
    
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
     * Moves the player a selected distance in a selected direction
     * Keeps on asking for move inputs until movesLeft is zero
     * if player is inside an estate, invokes guess cycle
     * if player is out of moves and no guess cycle has started, selects new currentPlayer
     * and invokes turn method
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
    	
    	if (moves > this.movesLeft) {
    		System.out.println("Error: distance exceeds your total moves left, please type correct distance and direction again:");
    		move(getInput());
    	}
    	
    	if (direction.equals("N") || direction.equals("n")) {
    		moveNorth(moves);
    	} else if (direction.equals("E") || direction.equals("e")) {
    		moveEast(moves);
    	} else if (direction.equals("S") || direction.equals("s")) {
    		moveSouth(moves);
    	} else {
    		moveWest(moves);
    	}
    	
    	// {"lucilla", "bert", "maline", "percy"};
    	if (this.movesLeft == 0) {
    		for (Player p : players) {
    			if (p.getCharName().equals(currentPlayer.getCharName())) {
    				
    			}
    		}
    	} else {
    		System.out.println("You still have " + this.movesLeft + " moves left, please enter next distance and direction:");
    		String line = getInput();
    		checkLine(line);
    	}
    	
    }
    
    public void moveNorth(int moves) {
    	List<Location> moveSq = new ArrayList<>();
    	// x and y, if going north, we decrement x, south, increment x, east increment y, west, decrement y
		int x = currentPlayer.location.getPos().getX()-1;
		int y = currentPlayer.location.getPos().getY();
		
		// original location of current player, used at end to tell the board there is not a player there
		// anymore, if the player was able to move in the chosen direction at all
		Location ogLoc = currentPlayer.location;
		
		// counts the valid moves taken by the player
		int moveCounter = 0;
		
		// add the square the player has chosen to move to, and all the squares in between to a list
		for (int i = 0; i < moves; i++) {
			moveSq.add(board.squares[x-i][y]);
		}
		
		// go through that list and check if each square is either 'Free' or is an estate
		// if square is free or an estate, move there and increment move counter
		// if square is an estate, make moveCounter and moves equal (no more moves are needed)
		// start the guess cycle
		for (int i = 0; i < moves; i++) {
			if (!moveSq.get(i).isWall) {
				if (moveSq.get(i).toString().equals("Free") || (moveSq.get(i) instanceof Estate)) {
					moveSq.get(i).setPlayerAtLoc(currentPlayer);
	                currentPlayer.setLocation(moveSq.get(i));
	                moveCounter++;
	                this.movesLeft--;
				} else { break; }
				
				if (moveSq.get(i) instanceof Estate) {
					this.movesLeft = 0;
					makeGuess();
				}
			}
		}
		
		// go through the locations moved to and tell those locations there is no longer a player there
		// up until the current player location
		for (int i = 0; i < moveCounter-1; i++) {
			moveSq.get(i).setHasPlayer(false);
		}
		
		// if the player has moved, erase the original location of its player
		if (moveCounter > 0) {
			ogLoc.setHasPlayer(false);
		}
		
		board.drawBoard();
    }
    
    public void moveSouth(int moves) {
    	List<Location> moveSq = new ArrayList<>();
    	// x and y, if going north, we decrement x, south, increment x, east increment y, west, decrement y
		int x = currentPlayer.location.getPos().getX()+1;
		int y = currentPlayer.location.getPos().getY();
		
		// original location of current player, used at end to tell the board there is not a player there
		// anymore, if the player was able to move in the chosen direction at all
		Location ogLoc = currentPlayer.location;
		
		// counts the valid moves taken by the player
		int moveCounter = 0;
		
		// add the square the player has chosen to move to, and all the squares in between to a list
		for (int i = 0; i < moves; i++) {
			moveSq.add(board.squares[x+i][y]);
		}
		
		// go through that list and check if each square is either 'Free' or is an estate
		// if square is free or an estate, move there and increment move counter
		// if square is an estate, make moveCounter and moves equal (no more moves are needed)
		// start the guess cycle
		for (int i = 0; i < moves; i++) {
			if (!moveSq.get(i).isWall) {
				if (moveSq.get(i).toString().equals("Free") || (moveSq.get(i) instanceof Estate)) {
					moveSq.get(i).setPlayerAtLoc(currentPlayer);
	                currentPlayer.setLocation(moveSq.get(i));
	                moveCounter++;
	                this.movesLeft--;
				} else { break; }
				
				if (moveSq.get(i) instanceof Estate) {
					this.movesLeft = 0;
					makeGuess();
				}
			}
		}
		
		// go through the locations moved to and tell those locations there is no longer a player there
		// up until the current player location
		for (int i = 0; i < moveCounter-1; i++) {
			moveSq.get(i).setHasPlayer(false);
		}
		
		// if the player has moved, erase the original location of its player
		if (moveCounter > 0) {
			ogLoc.setHasPlayer(false);
		}
		
		moves = moves - moveCounter;
		
		board.drawBoard();
    }
    
    public void moveEast(int moves) {
    	List<Location> moveSq = new ArrayList<>();
    	// x and y, if going north, we decrement x, south, increment x, east increment y, west, decrement y
		int x = currentPlayer.location.getPos().getX();
		int y = currentPlayer.location.getPos().getY()+1;
		
		// original location of current player, used at end to tell the board there is not a player there
		// anymore, if the player was able to move in the chosen direction at all
		Location ogLoc = currentPlayer.location;
		
		// counts the valid moves taken by the player
		int moveCounter = 0;
		
		// add the square the player has chosen to move to, and all the squares in between to a list
		for (int i = 0; i < moves; i++) {
			moveSq.add(board.squares[x][y+i]);
		}
		
		// go through that list and check if each square is either 'Free' or is an estate
		// if square is free or an estate, move there and increment move counter
		// if square is an estate, make moveCounter and moves equal (no more moves are needed)
		// start the guess cycle
		for (int i = 0; i < moves; i++) {
			if (!moveSq.get(i).isWall) {
				if (moveSq.get(i).toString().equals("Free") || (moveSq.get(i) instanceof Estate)) {
					moveSq.get(i).setPlayerAtLoc(currentPlayer);
	                currentPlayer.setLocation(moveSq.get(i));
	                moveCounter++;
	                this.movesLeft--;
				} else { break; }
				
				if (moveSq.get(i) instanceof Estate) {
					this.movesLeft = 0;
					makeGuess();
				}
			}
		}
		
		// go through the locations moved to and tell those locations there is no longer a player there
		// up until the current player location
		for (int i = 0; i < moveCounter-1; i++) {
			moveSq.get(i).setHasPlayer(false);
		}
		
		// if the player has moved, erase the original location of its player
		if (moveCounter > 0) {
			ogLoc.setHasPlayer(false);
		}
		
		moves = moves - moveCounter;
		
		board.drawBoard();
    }
    
    public void moveWest(int moves) {
    	List<Location> moveSq = new ArrayList<>();
    	// x and y, if going north, we decrement x, south, increment x, east increment y, west, decrement y
		int x = currentPlayer.location.getPos().getX();
		int y = currentPlayer.location.getPos().getY()-1;
		
		// original location of current player, used at end to tell the board there is not a player there
		// anymore, if the player was able to move in the chosen direction at all
		Location ogLoc = currentPlayer.location;
		
		// counts the valid moves taken by the player
		int moveCounter = 0;
		
		// add the square the player has chosen to move to, and all the squares in between to a list
		for (int i = 0; i < moves; i++) {
			moveSq.add(board.squares[x][y-i]);
		}
		
		// go through that list and check if each square is either 'Free' or is an estate
		// if square is free or an estate, move there and increment move counter
		// if square is an estate, make moveCounter and moves equal (no more moves are needed)
		// start the guess cycle
		for (int i = 0; i < moves; i++) {
			if (!moveSq.get(i).isWall) {
				if (moveSq.get(i).toString().equals("Free") || (moveSq.get(i) instanceof Estate)) {
					moveSq.get(i).setPlayerAtLoc(currentPlayer);
	                currentPlayer.setLocation(moveSq.get(i));
	                moveCounter++;
	                this.movesLeft--;
				} else { break; }
				
				if (moveSq.get(i) instanceof Estate) {
					this.movesLeft = 0;
					makeGuess();
				}
			}
		}
		
		// go through the locations moved to and tell those locations there is no longer a player there
		// up until the current player location
		for (int i = 0; i < moveCounter-1; i++) {
			moveSq.get(i).setHasPlayer(false);
		}
		
		// if the player has moved, erase the original location of its player
		if (moveCounter > 0) {
			ogLoc.setHasPlayer(false);
		}
		
		moves = moves - moveCounter;
		
		board.drawBoard();
    }

    /**
     * 
     */
    public void makeGuess(){
    	
    	System.out.println("In estate!");
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
        newGame.startGame(4);
    }

}
