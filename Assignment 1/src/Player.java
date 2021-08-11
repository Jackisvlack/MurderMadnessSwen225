import java.util.ArrayList;
import java.util.HashSet;

public class Player {
	
    String playerName;
    String charName;
    HashSet<Card> cards; 
    Location location;
    boolean controlled;
	private boolean hasGuessed;
    private boolean hasRolled;
    private boolean hasMoved;
	
    public Player(String name, String charName, HashSet<Card> cards) {
        this.playerName = name;
        this.charName = charName;
        this.cards = cards;
    }
    
    // returns the actual character name
    public String getCharName() {
    	return this.charName;
    }
<<<<<<< HEAD:Assignment 1/src/Player.java
=======

    public boolean getHasMoved(){
        return hasMoved;
    }
    public void setHasMoved(boolean moved){
        this.hasMoved = moved;
    }

    public boolean getHasRolled(){
        return hasRolled;
    }

    public void setHasRolled(boolean rolled){
        this.hasRolled = rolled;
    }

    /**
     * Sets the name of a player
     * @param name
     */
    public void setPlayerName(String name){
        this.playerName = name;
    }


    /**
     *  
     * @return name of the player
     */
    public String getPlayerName(){
        return this.playerName;
    }
>>>>>>> 593be659821c5900642e6c5ad0e1b7760dd15683:Assignment 1/src/GUI/Player.java
    
    // set location of this player
    public void setLocation(Location location){
        this.location = location;
    }
    
    // set if this character is controlled by a player
    public void setControlled(boolean controlled){
        this.controlled = controlled;
    }
    
    // see if this player is controlled by player or bot
    public boolean getControlled(){
        return this.controlled;
    }
    
    // set if this player has guessed
    public void setGuessed(boolean b) {
    	this.hasGuessed = b;
    }
    
    public boolean hasGuessed() {
    	return this.hasGuessed;
    }
}
