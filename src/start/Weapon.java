package start;

public abstract class Weapon {

	protected String weaponName;
	protected int maxDmg;
	protected int minDmg;
	
	
	
	public Weapon() {}
	
	public Weapon(String name, int maxDmg, int minDmg) {
		weaponName = name;
		this.maxDmg = maxDmg;
		this.minDmg = minDmg;
	}

	@Override
	public String toString() {
		return "Weapon [weaponName=" + weaponName + ", maxDmg=" + maxDmg + ", minDmg=" + minDmg + "]";
	}

	
	
	
}
