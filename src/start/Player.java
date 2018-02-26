package start;

public class Player extends Creatures{

	
	

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public Player(String name, int hp, Weapon wep) {
		super(name, hp, wep);
	}
	
	public Player() {}
	@Override
	public String toString() {
		return "Nazywasz siê " + super.getName() + " masz " + super.getHp()+ " punktów zycia "+" posiadasz bron "+ super.getWeapon().weaponName;
	}


	
}
