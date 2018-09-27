package enums;

public enum Comps {

	COMODORE(1, 2),
	AMIGA(2,5),
	I286(3, 7),
	I386(4, 10),
	I486(5, 12),
	I483DX4(7,16),
	P1(10,22);
	
	
	private final double speed;
	
	private final double price;
	
	private final double speedofWork;
	
	private static final double TaskLenght = 10;
	
	Comps(double speed, double price){
		this.speed = speed;
		this.price = price;
		speedofWork = TaskLenght/speed;
	}
	
	public double speed() {
		return speed;
	}
	
	public double price() {
		return price;
	}
	
	public double profability() {
		return price/speed;
	}
	
}
