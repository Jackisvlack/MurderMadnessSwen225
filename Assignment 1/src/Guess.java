
class Guess {
    String estate;
    String weapon;
    String character;

    
    public Guess (String estate, String weapon, String character){
        this.estate = estate;
        this.weapon = weapon;
        this.character = character;
    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Guess other = (Guess) obj;
		if (character == null) {
			if (other.character != null)
				return false;
		} else if (!character.equals(other.character))
			return false;
		if (estate == null) {
			if (other.estate != null)
				return false;
		} else if (!estate.equals(other.estate))
			return false;
		if (weapon == null) {
			if (other.weapon != null)
				return false;
		} else if (!weapon.equals(other.weapon))
			return false;
		return true;
	}
    
}