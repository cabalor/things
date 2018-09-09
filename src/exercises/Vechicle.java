package exercises;

public class Vechicle {

	private String name;
	private int doors;
	private double weight;
	
	
	public Swims flyingType;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDoors() {
		return doors;
	}
	public void setDoors(int doors) {
		this.doors = doors;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	
	public String tryToFly() {
		return flyingType.Swim();
	}
	
	public void setFlying(Swims newFlyType) {
		
		flyingType = newFlyType;
	}
	
	
	
	
}
