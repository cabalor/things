package enums;

public class Main {

	
	public static void main(String[] args) {
		
		
		double prof = Comps.COMODORE.profability();
		
		for(Comps c : Comps.values()) {
			System.out.printf("komputer %s i jego opłacalnosc %g%n", c, c.profability());
		}
		
	}
	
	
	
}
