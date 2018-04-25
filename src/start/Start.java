package start;

import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;

public class Start {

	

	public static void main(String[] args) {

		Scanner scn = new Scanner(System.in);
		gameLoop(scn);
	}

	public static void gameLoop(Scanner scn) {
		GameContext context = new GameContext();
		while (true) {
			System.out.println("new game \n1 - stworz bohatera \n2 - rozpocznij gre");
			try {
				switch (scn.nextLine()) {
				case "1":
					createNewPlayer(scn);
					break;
				case "2":
					gameMenu(scn);
					break;
				default:
					System.out.println("wybierz jeszcze raz");
					break;
				}
			} catch (NullPointerException e) {
					System.out.println("wybierz raz jeszcze");
			}
		}
	}

	public static void createNewPlayer(Scanner scn) {
		
		//if(GameContext.getMap(GameContext.PLAYER)==null) {
		System.out.println("podaj imie bohatera");
		Player player = Player.getPlayer(scn);
		player.setName(scn.nextLine());
		player.setHp(10);
		player.setWeapon(weaponChooser(scn));
		GameContext.setMap(GameContext.PLAYER, player);
		System.out.println("stworzono gracza: " + player.toString());
		//} else {
		//	System.out.println("stworzyles juz bohatera");
		//}
		
	}

	public static Weapon weaponChooser(Scanner scn) {
		System.out.println("wybierz bron: 1: for old sword, 2: for fist");
		switch (scn.nextLine()) {
		case "1":
			return WeaponFactory.makeWeapon(Swords.OLD_SWORD);
		case "2":
			return WeaponFactory.makeWeapon(Unarmed.FIST);
		default:
			weaponChooser(scn);
		}
		return null;
	}

	public static void gameMenu(Scanner scn) {
		System.out.println(
				"Co chcesz zrobic? \n 1-przygoda \n 2- twoje staystyki");
		switch (scn.nextLine()) {
		case "1":
			searchForAdv(scn);
			break;
		case "2": Utils.showPlayer((Player)GameContext.getMap(GameContext.PLAYER));
			break;
		default:
			System.out.println("wybierz jeszcze raz");
			break;
		}
	}

	public static void searchForAdv(Scanner scn) {
		System.out.println("wyruszasz na przygode");
		AdvFactory.makeQuest(Utils.generator(4),scn);
		gameMenu(scn);

	}

	public static void fight(GameContext context) {

	}
	
}
