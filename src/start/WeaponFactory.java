package start;

public class WeaponFactory {

	
	
	
	public static Weapon makeWeapon(String weaponType) {
			
			if(Start.OLD_SWORD.equalsIgnoreCase(weaponType)) {
				return new OldSword();
			}
			if(Start.FIST.equalsIgnoreCase(weaponType)) {
				return new Unarmed("piesc", 1, 1);
			}
			return null;
	}
	
	
	
	
}
