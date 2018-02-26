package start;

import java.util.Scanner;

public class Start {

	public static final String OLD_SWORD= "OLD_SWORD";
	public static final String FIST = "FIST";
	
	public static void main(String[] args) {
		
		Scanner scn = new Scanner(System.in);
		gameLoop(scn);
	}

	
	
	public static void gameLoop(Scanner scn) {
		GameContext context = new GameContext();
		while(true) {
			System.out.println("new game \n1 - stworz bohatera \n2 - rozpocznij gre");
			switch(scn.nextLine()){
			case "1": createNewPlayer(scn, context);
			break;
			case "2": game(scn, context);
			break;
			default: System.out.println("wybierz jeszcze raz");
			break;
		}
		}
		
	}
	
	public static void createNewPlayer(Scanner scn, GameContext context){
		System.out.println("podaj imie bohatera");
		Creatures player = new Player();
		player.setName(scn.nextLine());
		player.setHp(10);
		player.setWeapon(weaponChooser(scn));
		context.setMap(GameContext.PLAYER, player);
		System.out.println("stworzono gracza: "+player.toString());
	}
	
	public static Weapon weaponChooser(Scanner scn){
		System.out.println("wybierz bron: 1: for old sword, 2: for fist");
		switch(scn.nextLine()) {
		case "1": return WeaponFactory.makeWeapon(OLD_SWORD);
		case "2": return WeaponFactory.makeWeapon(FIST);
		default: weaponChooser(scn);
		}
		return null;
	}
	
	public static void game(Scanner scn, GameContext context) {
		System.out.println("jestes graczem 1 " + context.getMap(GameContext.PLAYER).toString());
	}
	
}
