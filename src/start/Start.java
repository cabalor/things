package start;

import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;

public class Start {

	public static final String OLD_SWORD = "OLD_SWORD";
	public static final String FIST = "FIST";

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
					game(scn);
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
		System.out.println("podaj imie bohatera");
		Creatures player = new Player();
		player.setName(scn.nextLine());
		player.setHp(10);
		player.setWeapon(weaponChooser(scn));
		GameContext.setMap(GameContext.PLAYER, player);
		System.out.println("stworzono gracza: " + player.toString());
	}

	public static Weapon weaponChooser(Scanner scn) {
		System.out.println("wybierz bron: 1: for old sword, 2: for fist");
		switch (scn.nextLine()) {
		case "1":
			return WeaponFactory.makeWeapon(OLD_SWORD);
		case "2":
			return WeaponFactory.makeWeapon(FIST);
		default:
			weaponChooser(scn);
		}
		return null;
	}

	public static void game(Scanner scn) {
		System.out.println("jestes graczem 1 " + GameContext.getMap(GameContext.PLAYER).toString()
				+ "\n co chcesz zrobic? \n 1-przygoda");
		switch (scn.nextLine()) {
		case "1":
			searchForAdv(scn);
			break;
		/*
		 * case "2": game(scn, context); break;
		 */
		default:
			System.out.println("wybierz jeszcze raz");
			break;
		}
	}

	public static void searchForAdv(Scanner scn) {
		System.out.println("wyruszasz na przygode");
		AdvFactory.makeQuest(Start.generator(4),scn);
		game(scn);

	}

	public static void fight(GameContext context) {

	}

	
	public static int generator(int max) {
		Random random = new SecureRandom();
		System.out.println("liczba losowa " + random.nextInt(max));
		return random.nextInt(max);
	}
	
	public static int generator(int min, int max) {
		Random random = new SecureRandom();
		System.out.println("min "+min+" amx "+max);
		return random.nextInt((max - min) + 1) + min;
	}
	

	
}
