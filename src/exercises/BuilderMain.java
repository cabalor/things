package exercises;

public class BuilderMain {

	public static void main(String[] args) {
		
		Car auto = new Car.Builder().doors(4).color("red").owner("rychu").brand("audi").build();
		
		System.out.println(auto.toString());
		

		Vechicle bick = new Bicycle();
		
		Vechicle boat = new Boat();
		
		System.out.println("bicycle: "+ bick.tryToFly());
		

		System.out.println("boat: "+ boat.tryToFly());
		
		bick.setFlying(new CanSwim());
		

		System.out.println("bicycle: "+ bick.tryToFly());
	}

}
