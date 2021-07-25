public class Location {
    private Location west;
    private Location north;
    private Location south;
    private Position pos;
    private Location east;
    public boolean isWall;
    String name;
    Weapon estatesWeapon;
	private boolean hasPlayer = false;
    private Player player;

    public void setEast(Location east){
        this.east = east;
    }

    public void setWest(Location west) {
        this.west = west;
    }

    public void setNorth(Location north) {
        this.north = north;
    }

    public void setSouth(Location south) {
        this.south = south;
    }


    public Location getEast() {
        return east;
    }

    public Location getWest() {
        return west;
    }

    public Location getNorth() {
        return north;
    }

    public Location getSouth() {
        return south;
    }

    public Location(Position pos) {
        this.pos = pos;
    }
    public Location(Position pos, String name) {
        this.pos = pos;
        this.name = name;
    }
    public void setWall(){
        this.isWall = true;
    }
    
    public void setHasPlayer(boolean b) {
    	this.player = null;
    	this.hasPlayer = b;
    }
    
    public boolean hasPlayer(int x, int y) {
    	return this.hasPlayer;
    }

    public void setWeapon(Weapon weapon){
        this.estatesWeapon = weapon;
    }

    public String getWeaponName(){
        return estatesWeapon.getName();
    }

    public Player playerAtLoc(){
        return this.player;
    }

    /**
     * Takes player and sets them to this locaton, sets hasPlayer to true
     * @param player
     */
    public void setPlayerAtLoc(Player player){
        hasPlayer = true;
        this.player = player;
    }

    /**
     * Helper method to assist board in drawing
     * @return
     */
    public String getPlayerIcon(){
        String icon = "";
        if (player.getCharName().equals("lucilla")){
            icon = "L";
        }
        if (player.getCharName().equals("bert")){
            icon = "B";
        }
        if (player.getCharName().equals("percy")){
            icon = "P";
        }
        if (player.getCharName().equals("malina")){
            icon = "M";
        }
        return icon;
    }
    
    /**
     * Identifies whether location has a player on it, is a wall, or is free
     * */
    public String toString() {
    	if (this.hasPlayer) { return "Player"; }
    	else if (isWall) {
    		return "Wall";
    	} else {
    		return "Free";
    	}
    }
    
    public Position getPos() {
    	return this.pos;
    }
}
