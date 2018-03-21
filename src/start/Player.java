package start;

import java.util.Scanner;

public class Player extends Creatures{

	
	private volatile static Player player;

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	
	public Player(String name, int hp, Weapon wep)  {
		super(name, hp, wep, 1,0, new Attribiutes(10, 10, 10, 10, 10));
	}
	
	public Player() {}
	
	@Override
	public String toString() {
		return "Nazywasz si� " + super.getName() + " masz " + super.getHp()+ " punkt�w zycia "+" posiadasz bron "+ super.getWeapon().weaponName 
				+ " masz "+ super.getLevel();
	}
	
	
	public static Player getPlayer(Scanner scn) {
		if(player == null) {
			synchronized (Player.class) {
				if(player == null) {
					player = new Player();
				}
			}
			return player;
		} else {
			System.out.println("stworzyles juz gracza");
			scn.next();
		}
		return null;
	}
	
	
}
