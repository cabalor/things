package start;

import java.util.Scanner;

public class Start {

	public static void main(String[] args) {
		/*Weapon miecz = new Swords("miecz", 1,3);
		
		Creatures bandyta = new HummanEnemies("Bandyta", 10, miecz);
		
		Player player = new Player("Name", 10, miecz);
		System.out.println(player);
		
		Fight.hit(player, bandyta);*/
		
		Scanner scn = new Scanner(System.in);
		
		gameLoop(scn);
	}

	public static void gameLoop(Scanner scn) {
		
		while(true) {
			System.out.println("new game \n1 - stworz bohatera");
			switch(scn.nextLine()){
			case "1": createNewPlayer(scn);
			break;
			default: System.out.println("wcisnij jeszcze raz");
			break;
		}
		}
		
	}
	
	public static void createNewPlayer(Scanner scn){
		System.out.println("podaj imie bohatera");
		Creatures player = new Player();
		scn.nextLine();
		player.setName(scn.nextLine());
		player.setHp(10);
		System.out.println("wybierz bron: 1: for old sword, 2: for fist");
		player.setWeapon(weaponChooser(scn));
		System.out.println("stworzono gracza: "+player.toString());
	}
	
	public static Weapon weaponChooser(Scanner scn){
		return WeaponFactory.makeWeapon(scn.nextLine());
	}
	
}
