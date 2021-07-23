import java.util.ArrayList;
import java.util.HashSet;

public class Player {
    String name;
    HashSet<Card> cards; 
    private Location playerLocation;
    public Player(String playerName, String charName, HashSet<Card> cards, Location loc) {
        this.name = name;
        this.cards = cards;
        this.playerLocation = loc;
    }
    
    public String getName() {
    	return name;
    }
}
