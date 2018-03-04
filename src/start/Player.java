package start;

public class Player extends Creatures{

	
	

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public Player(String name, int hp, Weapon wep)  {
		super(name, hp, wep, 1,0);
	}
	
	public Player() {}
	@Override
	public String toString() {
		return "Nazywasz si� " + super.getName() + " masz " + super.getHp()+ " punkt�w zycia "+" posiadasz bron "+ super.getWeapon().weaponName 
				+ " masz "+ super.getLevel();
	}


	
}
