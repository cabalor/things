package start;

public class WeaponFactory {

	
	
	
	public static Weapon makeWeapon(String weaponType) {
			if("1".equalsIgnoreCase(weaponType)) {
				return new OldSword();
			}
			if("2".equalsIgnoreCase(weaponType)) {
				return new Unarmed("piesc", 1, 1);
			}
			return null;
	}
	
	
	
	
}
