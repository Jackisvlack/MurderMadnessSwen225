public class Location {
    private Location west;
    private Location north;
    private Location south;
    private Position pos;
    private Location east;
    public boolean isWall;
    String name;
    Weapon estatesWeapon;

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

    public void setWeapon(Weapon weapon){
        this.estatesWeapon = weapon;
    }

    public String getWeaponName(){
        return estatesWeapon.getName();
    }
}
