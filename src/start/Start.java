package start;


public class Start {

	public static void main(String[] args) {
		Weapon miecz = new Swords("miecz", 1,3);
		
		Creatures bandyta = new Humans("Bandyta", 10, miecz);
		
		Player player = new Player("Name", 10, miecz);
		System.out.println(player);
		
		Fight.hit(player, bandyta);
		
		
	}

}
