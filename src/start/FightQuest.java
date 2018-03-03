package start;

import java.util.Scanner;

public class FightQuest extends Adventures{

	
	
	public FightQuest(){
		
	}
	
	
	public void fightAdventure(Scanner scn) {
		System.out.println("spotykasz przeciwnika");
		
		
		Fight.Fight((Creatures)GameContext.getMap(GameContext.PLAYER), Opponents.getOpponent(Opponents.zlo));
	}



	
	
	
}
