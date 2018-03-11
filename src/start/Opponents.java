package start;

import java.util.HashMap;
import java.util.Map;

public class Opponents {

	
	public static final String zlo = "zlo";
	
	
	 Map<String, Creatures> enemies = new HashMap<>();
	
	public static Creatures getOpponent(String str) {
		Map<String, Creatures> enemies = new HashMap<>();
		enemies.put(Opponents.zlo, new HummanEnemies("zlodziej", 10, WeaponFactory.makeWeapon(Start.OLD_SWORD), 1));
		
		
		
		return enemies.get(str);
		
	}
	
	
	
}
