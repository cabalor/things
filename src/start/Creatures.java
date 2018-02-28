package start;

public abstract class Creatures {

	private int hp;
	private String name;
	protected Weapon weapon;
	
	public Creatures() {}
	
	public Creatures(String name, int hp, Weapon wep) {
		this.setName(name);
		this.setHp(hp);
		setWeapon(wep);
	}
	
	
	protected  void showHp() {
		System.out.println("Currnetly " +getHp());
	}

	@Override
	public String toString() {
		return "Creatures [hp=" + getHp() + ", name=" + getName() + ", weapon=" + getWeapon() + "]";
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
		
	
}
