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

    public void setPlayerAtLoc(Player player){
        hasPlayer = true;
        this.player = player;
    }

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
        if (player.getCharName().equals("lucilla")){
            icon = "M";
        }
        return icon;
    }
}
