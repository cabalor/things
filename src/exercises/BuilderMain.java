package exercises;

public class BuilderMain {

	public static void main(String[] args) {
		
		Car auto = new Car.Builder().doors(4).color("red").owner("rychu").brand("audi").build();
		
		System.out.println(auto.toString());
		

	}

}
