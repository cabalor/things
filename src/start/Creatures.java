package start;

public abstract class Creatures {

	protected int hp;
	protected String name;
	protected Weapon weapon;
	protected int level;
	protected int experience;
	protected Attribiutes attribiutes;
	
	public Creatures() {}
	
	public Creatures(String name, int hp, Weapon wep, int lvl, int exp, Attribiutes at) {
		this.setName(name);
		this.setHp(hp);
		setWeapon(wep);
		this.level = lvl;
		this.experience = exp;
		this.attribiutes = at;
	}
	
	public Creatures(String name, int hp, Weapon wep, int lvl) {
		this.setName(name);
		this.setHp(hp);
		setWeapon(wep);
		level = lvl;
	}
	
	
	protected  void showHp() {
		System.out.println("Currnetly " +getHp());
	}

	@Override
	public String toString() {
		return "Creatures [hp=" + getHp() + ", name=" + getName() + ", weapon=" + getWeapon() + " lvl "+ getLevel()+ "]";
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}
		
	
}
