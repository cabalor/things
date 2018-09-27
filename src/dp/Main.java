package dp;

public class Main {

	public static void main(String[] args) {
		
		Car car = new VWSportCar(new CarBrand());
		
		System.out.println("auto "+car.opis());
		
		System.out.println("koszt "+car.cena());
	}

}
