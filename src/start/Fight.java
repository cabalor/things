package start;

import java.util.List;

public class Fight extends Creatures {

	

	protected static void hit(Creatures at, Creatures def) {
		System.out.println(at + " "+def);
		System.out.println("dmg "+ at.getWeapon().maxDmg);
		def.setHp(def.getHp() - at.getWeapon().maxDmg);
		//def.hp = def.hp - (at.weapon.minDmg + ((int) Math.random() + ((at.weapon.maxDmg + at.weapon.minDmg)+1)));
		System.out.println("currently hp = " + def.getHp());
		
	}
	
	protected static void multiFight(Player player, List<Opponents> enemies) {
		
	}
	
	
}
