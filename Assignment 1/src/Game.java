import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
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
	private String weapon;
	private String player;
	
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
    public void startGame(int np){
        board = new Board();

            int numplayers  = np;
            
            
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
            
            players.add(0, new Player("player1", String.valueOf(Characters.lucilla), new HashSet<Card>())); 
            players.add(1, new Player("player2", String.valueOf(Characters.bert), new HashSet<Card>()));
            players.add(2, new Player("player3", String.valueOf(Characters.malina), new HashSet<Card>()));
            players.add(3, new Player("player4", String.valueOf(Characters.percy), new HashSet<Card>()));
            
            for (int i = 0; i < numplayers; i++) {
            	players.get(i).setControlled(true);
            }
            
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
            
			int random = (int) (Math.random() * numplayers);
            currentPlayer = players.get(random);
            turn(); 
            
      
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
		} catch (Exception n) {
			throw n;
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
    	
		/**
		 * When in estates, you can only move out of the exits of that estate
		 */
		if (currentPlayer.location instanceof Estate){
			checkEstateExits(currentPlayer.location);
			if (direction.equals("N") || direction.equals("n")) {
				Location orgLoc = currentPlayer.location;
				currentPlayer.location.getNorth().setPlayerAtLoc(currentPlayer);
				orgLoc.setHasPlayer(false);
				moves--;
				moveNorth(moves);
			} else if (direction.equals("E") || direction.equals("e")) {
				Location orgLoc = currentPlayer.location;
				currentPlayer.location.getEast().setPlayerAtLoc(currentPlayer);
				orgLoc.setHasPlayer(false);
				moves--;
				moveEast(moves);
			} else if (direction.equals("S") || direction.equals("s")) {
				Location orgLoc = currentPlayer.location;
				currentPlayer.location.getSouth().setPlayerAtLoc(currentPlayer);
				orgLoc.setHasPlayer(false);
				moves--;
				moveSouth(moves);
			} else {
				Location orgLoc = currentPlayer.location;
				currentPlayer.location.getWest().setPlayerAtLoc(currentPlayer);
				orgLoc.setHasPlayer(false);
				moves--;
				moveWest(moves);
			}
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
    		if (currentPlayer.getCharName().equals("percy")) {
    			currentPlayer = players.get(0);
    		} else {
    			currentPlayer = players.get(players.indexOf(currentPlayer)+1);
    		}
    	} else {
    		System.out.println("You still have " + this.movesLeft + " moves left, please enter next distance and direction:");
    		String line = getInput();
    		checkLine(line);
    	}
    	
    }

	public void checkEstateExits(Location location){
		System.out.println("You can leave via the following exits: \n");
		if (location.getEast() != null){
			System.out.println("East\n");
		} else if (location.getWest() != null){
			System.out.println("West\n");
		} else if (location.getNorth() != null){
			System.out.println("North\n");
		} else {
			System.out.println("South\n");
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
					if (!currentPlayer.hasGuessed()) {
						makeGuess(moveSq.get(i));
					} else {
						System.out.println("You are not elgibile to guess!\n"
								+ "Next person!");
					}
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
					if (!currentPlayer.hasGuessed()) {
						makeGuess(moveSq.get(i));
					} else {
						System.out.println("You are not elgibile to guess!\n"
								+ "Next person!");
					}
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
					if (!currentPlayer.hasGuessed()) {
						makeGuess(moveSq.get(i));
					} else {
						System.out.println("You are not elgibile to guess!\n"
								+ "Next person!");
					}
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
					if (!currentPlayer.hasGuessed()) {
						makeGuess(moveSq.get(i));
					} else {
						System.out.println("You are not elgibile to guess!\n"
								+ "Next person!");
					}
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
    public void makeGuess(Location loc){
    	String estate = loc.name;
    	List<Integer> idxList = new ArrayList<>();
    	int curIndex = players.indexOf(currentPlayer);
    	
    	// Welcome message to this estate
    	System.out.println("Welcome to the " + loc.name+ " "+ currentPlayer.charName +"!");
    	System.out.println("You see " + loc.getWeaponName() + " - mysterious...");
    	System.out.println("This may be a clue to the puzzle! What do you think happened?");
    	System.out.println("Choose two cards : one player card and one weapon card");
    	
    	// Prints the players options to choose from
    	// Also gets and checks the players input
      	printCardOptions();
    	
      	// Make an official guess object
    	Guess playersGuess = new Guess(estate, weapon, player);
    	
    	// check if this is the murder circumstances,
    	// if yes, the player wins,
    	// if not, the player continues on to guess cycle.
    	if (playersGuess.equals(murderCircumstance)) {
    		this.solved = true;
			System.out.println("Congratulations!! You have solved the murder!");
    		return;
    	} 
		System.out.println(currentPlayer.charName + " " + playersGuess.toString() + "!");
		System.out.println("Who disagrees?");
    	
		if (curIndex == 0) {
			idxList.add(1);
			idxList.add(2);
			idxList.add(3);
		} else if (curIndex == 1) {
			idxList.add(2);
			idxList.add(3);
			idxList.add(0);
		} else if (curIndex == 2) {
			idxList.add(3);
			idxList.add(0);
			idxList.add(1);
		} else {
			idxList.add(0);
			idxList.add(1);
			idxList.add(2);
		}
		
		List<String> finalCards = new ArrayList<>();
		/**
		 * For each of the players that aren't currentPlayer, 
		 * get the eligible cards
		 * if they have eligible cards, make them choose which card they wish to present
		 * if they are not an 'active' player do the same but always choose the first eligible card, if any
		 * */
		for (int i = 0; i < idxList.size(); i++) { 
			List<Card> options = new ArrayList<Card>();
			Player p = players.get(idxList.get(i));
			
			// Get all eligible refutation cards
			if (p.controlled == true) {
				for (Card c : p.cards) {
					if (c.getName().equals(estate)) {
						options.add(c);
					} else if (c.getName().equals(weapon)) {
						options.add(c);
					} else if (c.getName().equals(player)) {
						options.add(c);
					}
				}
				
				
				System.out.println("Please pass the screen on to: " + p.charName);
				wait(2);
				System.out.println("Hello, " + p.charName);
				if (options.isEmpty()) {
					System.out.println("Sorry, " + p.getCharName() + ". You have no eligible refutation cards.");
				} else {
					printEligibleCards(options);
					int counter = 0;
					String cardPicked = getInput();
					for (Card c : options) {
						counter = 0;
						if (c.getName().equals(cardPicked)) {
							finalCards.add(cardPicked);
							counter++;
							break;
						}
					}
					
					if (counter == 0) {
						System.out.println("Please try again as we did not recognize that card!");
						printEligibleCards(options);
					}
					
				}
				
			} else {
				for (Card c : p.cards) {
					if (c.getName().equals(estate)) {
						options.add(c);
					} else if (c.getName().equals(weapon)) {
						options.add(c);
					} else if (c.getName().equals(player)) {
						options.add(c);
					}
				}
				
				if (!options.isEmpty()) {
					finalCards.add(options.get(0).getName());
				}
			}
			
		}
		
		System.out.println("Please pass the screen on to " + currentPlayer.charName);
		System.out.println("Hello, " + currentPlayer.charName + " here are what your hopefully loyal associates had to say...");
		for (String s : finalCards) {
			System.out.println(s);
		}
		
		System.out.println("Your turn is now over! You have not guessed the right murder circumstances, therefore\n"
				+ "you are now excluded from guessing in the future!");
		currentPlayer.setGuessed(true);
    }
    
    public void printEligibleCards(List<Card> options) {
    	System.out.println("Please choose one of the card(s):");
		for (Card c : options) {
			System.out.println(c.getName());
		}
    }
    
    /**
     * Get the players guesses and check them
     * */
    public void printCardOptions() {
    	int i = 1;
    	System.out.println("Choose a weapon: (type in name)");
    	for (Card c : weaponCards) {
    		System.out.println(i + ". " + c.getName());
    		i++;
    	}
    	i = 1;
    	System.out.println("");
    	
    	weapon = getInput();
    	
    	System.out.println("Characters: (type in name)");
    	for (Card c : characterCards) {
    		System.out.println(i + ". " + c.getName());
    		i++;
    	}
    	i = 1;
    	System.out.println("");
    	
    	player = getInput();
    	
    	checkInputs(weapon, player);
    }
    
    /**
     * Checks the guess inputs validity
     * */
    public void checkInputs(String w, String p) {
    	int checked = 0;
    	for (Card c : weaponCards) {
    		if (c.getName().equalsIgnoreCase(w)) {
    			checked++;
    		}
    	}
    	for (Card c : characterCards) {
    		if (c.getName().equalsIgnoreCase(p)) {
    			checked++;
    		}
    	}
    	if (checked != 2) {
    		printCardOptions();
    	}
    }

    /**
     * Method to simulate two 6 sided dice
     * @return int: the sum of two dice
     */
    public int roll(){
        int diceOne = (int) (Math.random()*6 + 1);
        int diceTwo = (int) (Math.random()*6 + 1);
        return diceOne + diceTwo;
    }

    public static void main(String... args) throws IOException {
        Game newGame = new Game();
		newGame.startScreen();
    }

}
