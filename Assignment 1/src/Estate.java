public class Estate extends Location{

    public Estate(Position pos, String name) {
        super(pos, name);
        super.isWall = false;
    }

    public void setWall(){
        super.isWall = true;
    }
}
