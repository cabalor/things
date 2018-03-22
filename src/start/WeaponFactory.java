package start;

public class WeaponFactory {

	

	
	
	public static Weapon makeWeapon(String weaponType) {
			
			if(Swords.OLD_SWORD.equalsIgnoreCase(weaponType)) {
				return new OldSword();
			}
			if(Unarmed.FIST.equalsIgnoreCase(weaponType)) {
				return new Unarmed("piesc", 1, 1);
			}
			return null;
	}
	
	
	
	
}
