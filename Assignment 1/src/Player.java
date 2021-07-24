import java.util.ArrayList;
import java.util.HashSet;

public class Player {
    String playerName;
    String charName;
    HashSet<Card> cards; 
    Location location;
    public Player(String name, String charName, HashSet<Card> cards) {
        this.playerName = name;
        this.charName = charName;
        this.cards = cards;
    }
    
    public String getCharName() {
    	return this.charName;
    }

    public String getPlayerName() {
    	return this.charName;
    }

    public void setLocation(Location location){
        this.location = location;
    }
}
