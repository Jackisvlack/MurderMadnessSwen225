public class Estate extends Location{
    Weapon estatesWeapon;

    public Estate(Position pos, String name) {
        super(pos, name);
        super.isWall = false;
    }

    public void setWall(){
        super.isWall = true;
    }

    public void setWeapon(Weapon weapon){
        this.estatesWeapon = weapon;
    }

    public String getWeaponName(){
        return estatesWeapon.getName();
    }
}
