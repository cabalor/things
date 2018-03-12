package start;

import java.security.SecureRandom;
import java.util.Random;

public class Utils {

	
	public static int generator(int max) {
		Random random = new SecureRandom();
		System.out.println("liczba losowa " + random.nextInt(max));
		return random.nextInt(max);
	}
	
	public static int generator(int min, int max) {
		Random random = new SecureRandom();
		System.out.println("min "+min+" max "+max);
		return random.nextInt((max - min) + 1) + min;
	}
	
	public static void showPlayer(Player player) {
		System.out.println("aktualnie masz " + player.getHp() + " punktów zycia.");
	}
	
	
}
