package start;

import java.util.List;

public class Fight extends Creatures {

	public Fight() {
		
	}
	
	
	public static void Fight(Creatures at, Creatures def) {
		while(at.getHp() > 0 && def.getHp() >0) {
			hit(at, def);
	}
	}
	
	
	
	

	private static void hit(Creatures at, Creatures def) {
		System.out.println("dmg "+ at.getWeapon().maxDmg);
		def.setHp(def.getHp() - (at.weapon.minDmg +(int)(Math.random() *((at.weapon.maxDmg - at.weapon.minDmg)+1))));
		if(def.getHp() <=0) {
			System.out.println("przeiwnik pokonany");
		} else {
		System.out.println("currently hp defa = " + def.getHp());
		}

		
	}
	
	protected static void multiFight(Player player, List<Opponents> enemies) {
		
	}
	
	
}
