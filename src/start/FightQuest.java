package start;

import java.util.Scanner;

public class FightQuest extends Adventures{

	
	
	public FightQuest(){
		
	}
	
	
	public void fightAdventure(Scanner scn) {
		System.out.println("spotykasz przeciwnika");
		Fight.Walka((Creatures)GameContext.getMap(GameContext.PLAYER), Opponents.getOpponent(Opponents.zlo));
		System.out.println("Czy chcesz isc na jeszcze jedna wyprawe? \n y or n?");
		if(ChooseEnum.Y.toString().equalsIgnoreCase(scn.nextLine())) {
			AdvFactory.makeQuest(Utils.generator(4),scn);
		} /*else {
			Start.gameMenu(scn);
		}
		*/
	}



	
	
	
}
