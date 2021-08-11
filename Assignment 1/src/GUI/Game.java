package GUI;
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
	ArrayList<Player> controlledPlayers = new ArrayList<>();
    Guess murderCircumstance;
    ArrayList<Card> weaponCards = new ArrayList<>();
    ArrayList<Card> characterCards = new ArrayList<>();
    ArrayList<Card> estateCards = new ArrayList<>(); 
    Player currentPlayer;
    boolean solved = false;
	private String weapon;
	private String player;
	private int numberOfPlayers;
	private List<Card> cards;
	private int moves;
	
	public enum Characters {
		lucilla,
		bert,
		malina,
		percy
	}

	
    public Game(int numPlayers, List<String> pNames) {
		board = new Board();
        this.numberOfPlayers = numPlayers;
		playerCreation(pNames);
		createCards();
		chooseMurderCircumstance();
		dealCards();
		selectStartPlayer();
		board.placeCharactersStart(players);
    }


	public void playerCreation(List<String> playerNames){
		players.add(0, new Player("NPC", String.valueOf(Characters.lucilla), new HashSet<Card>())); 
		players.add(1, new Player("NPC", String.valueOf(Characters.bert), new HashSet<Card>()));
		players.add(2, new Player("NPC", String.valueOf(Characters.malina), new HashSet<Card>()));
		players.add(3, new Player("NPC", String.valueOf(Characters.percy), new HashSet<Card>()));

		Collections.shuffle(playerNames);

		//Uses controlled players list so game only cycles through controlled players
		for (int i = 0 ; i < numberOfPlayers; i++) {
			players.get(i).setControlled(true);
			players.get(i).setPlayerName(playerNames.remove(0));
			controlledPlayers.add(players.get(i));
		}
	}

	public void createCards(){
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
	}

	public void chooseMurderCircumstance(){
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
		weaponCards.remove(weaponCards.indexOf(murderWeapon));
		characterCards.remove(characterCards.indexOf(murderCharacter));
		estateCards.remove(estateCards.indexOf(murderEstate));

		cards = new ArrayList<Card>();
		cards.addAll(weaponCards);
		cards.addAll(estateCards);
		cards.addAll(characterCards);
		Collections.shuffle(cards);
	}
		 
	/**
	 * Deals the remaining cards to Players
	 */
	public void dealCards(){
		int c = 0;
		for (int i = 0; i < cards.size()-1; i++) {
			if (c == controlledPlayers.size()-1) { c = 0; }
			controlledPlayers.get(c).cards.add(cards.get(i));
			c++;
		}
	}

	/**
	 * Randomly selects the player to start
	 */
	public void selectStartPlayer(){
		int random = (int) (Math.random() * numberOfPlayers);
        currentPlayer = controlledPlayers.get(random);
	}


	public Board getBoard(){
		return board;
	}
    
    public void wait(int sec) {
    	try {
			TimeUnit.SECONDS.sleep(sec);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    /**
     * A method that starts the currentPlayers turn
     * Rolls dice
     * gives instructions
     * gives move order to checkLine, if valid passes on to move method
     * */
    public void nextPlayer(int curIndex){
				if (curIndex == numberOfPlayers - 1){
					curIndex = 0;
					currentPlayer = controlledPlayers.get(curIndex);
				} else {
					curIndex++;
					currentPlayer = controlledPlayers.get(curIndex);
				}
    }
    
    
    /**
     * Helper method to help players know which way they can go once in an estate
     * */
	public void checkEstateExits(Location location){
		System.out.println("You can leave the estate via the following exits: \n");
		if (location.getEast() != null){
			System.out.println("East\n");
		}
		if (location.getWest() != null){
			System.out.println("West\n");
		}
		if (location.getNorth() != null){
			System.out.println("North\n");
		}
	    if (location.getSouth() != null){
			System.out.println("South\n");
		}
	}


    /**
     * Moves the player north, if on an estate location starts the makeGuess cycle
     * */
    public void moveNorth() {
		if (currentPlayer.getHasRolled() == true){
			
		
    	if (moves == 0){
			int curIndex = controlledPlayers.indexOf(currentPlayer);
			nextPlayer(curIndex);
			return;
		}
    		Location playerLoc = currentPlayer.location;
    		
		if (currentPlayer.location.getPos().getX()-1 != -1){
    		if (!currentPlayer.location.getNorth().isWall && !currentPlayer.location.getNorth().hasPlayer()) {
    			playerLoc.getNorth().setPlayerAtLoc(currentPlayer);
    			currentPlayer.setLocation(playerLoc.getNorth());
    			playerLoc.setHasPlayer(false);
    			moves--;
				moveNorth();
    			
    			if (currentPlayer.location instanceof Estate && !currentPlayer.hasGuessed()) {
    				makeGuess(currentPlayer.location);
					int curIndex = players.indexOf(currentPlayer);
					nextPlayer(curIndex);
    			}
    		} 
		}
	}
    }
    
    /**
     * Moves the player south, if on an estate location starts the makeGuess cycle
     * */
    public void moveSouth() {
		if (currentPlayer.getHasRolled() == true){
    	if (moves == 0){
			int curIndex = players.indexOf(currentPlayer);
			nextPlayer(curIndex);
			return;
		}
    		Location playerLoc = currentPlayer.location;
    		
		if (currentPlayer.location.getPos().getX()+1 != 24){
    		if (!currentPlayer.location.getSouth().isWall && !currentPlayer.location.getSouth().hasPlayer()) {
    			playerLoc.getSouth().setPlayerAtLoc(currentPlayer);
    			currentPlayer.setLocation(playerLoc.getSouth());
    			playerLoc.setHasPlayer(false);
    			moves--;
				moveSouth();
    			
    			if (currentPlayer.location instanceof Estate && !currentPlayer.hasGuessed()) {
    				//board.drawBoard();
    				makeGuess(currentPlayer.location);
    				int curIndex = players.indexOf(currentPlayer);
					nextPlayer(curIndex);
    			}
    		} 
		}
	}
    }
    
    /**
     * Moves the player east, if on an estate location starts the makeGuess cycle
     * */
    public void moveEast() {
		if (currentPlayer.getHasRolled() == true){
    	if (moves == 0){
			int curIndex = players.indexOf(currentPlayer);
			nextPlayer(curIndex);
			return;
		}
    		Location playerLoc = currentPlayer.location;
    		
		if (currentPlayer.location.getPos().getY()+1 != 24){
    		if (!currentPlayer.location.getEast().isWall && !currentPlayer.location.getEast().hasPlayer()) {
    			playerLoc.getEast().setPlayerAtLoc(currentPlayer);
    			currentPlayer.setLocation(playerLoc.getEast());
    			playerLoc.setHasPlayer(false);
    			moves--;
				moveEast();
    			
    			if (currentPlayer.location instanceof Estate && !currentPlayer.hasGuessed()) {
    				//board.drawBoard();
    				makeGuess(currentPlayer.location);
    				int curIndex = players.indexOf(currentPlayer);
					nextPlayer(curIndex);
    			}
    		} 
		}
	}
    }
    
    /**
     * Moves the player west, if on an estate location starts the makeGuess cycle
     * */
    public void moveWest() {
		if (currentPlayer.getHasRolled() == true){
    	if (moves == 0){
			int curIndex = players.indexOf(currentPlayer);
			nextPlayer(curIndex);
			return;
		}
    		Location playerLoc = currentPlayer.location;
    		
		if (currentPlayer.location.getPos().getY()-1 != -1){
    		if (!currentPlayer.location.getWest().isWall && !currentPlayer.location.getWest().hasPlayer()) {
    			playerLoc.getWest().setPlayerAtLoc(currentPlayer);
    			currentPlayer.setLocation(playerLoc.getWest());
    			playerLoc.setHasPlayer(false);
    			moves --;
				moveWest();
    			
    			if (currentPlayer.location instanceof Estate && !currentPlayer.hasGuessed()) {
    				//board.drawBoard();
    				makeGuess(currentPlayer.location);
    				int curIndex = players.indexOf(currentPlayer);
					nextPlayer(curIndex);
    			}
    		} 
		}
	}
    }

    /**
     * The main guess cycle method
     * Manages the round of guessing that takes place when a player
     * that has not guessed enters an estate
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
					
					for (Card c : options) {
						counter = 0;
						//if (c.getName().equals(cardPicked)) {
							//finalCards.add(cardPicked);
							counter++;
							break;
						//}
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
    
    /**
     * prints out eligible refutation cards, is a method so that it can be called multiple times until it has 
     * good guessing input
     * */
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
    	
    	//weapon = getInput();
    	
    	System.out.println("Characters: (type in name)");
    	for (Card c : characterCards) {
    		System.out.println(i + ". " + c.getName());
    		i++;
    	}
    	i = 1;
    	System.out.println("");
    	
    	//player = getInput();
    	
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
    public void roll(){
        int diceOne = (int) (Math.random()*6 + 1);
        int diceTwo = (int) (Math.random()*6 + 1);
		currentPlayer.setHasRolled(true);
        this.moves = diceOne + diceTwo;
    }

	public int getMoves(){
		return this.moves;
	}

	public String getCurrentPlayerName(){
		return currentPlayer.getPlayerName();
	}
}