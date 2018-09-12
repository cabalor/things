package dp;

public class VWSportCar extends VW {

	public VWSportCar(Car newCar) {
		super(newCar);

		System.out.println("kupujesz");

		System.out.println("sport car VW");

	}

	public String opis() {

		return defCar.opis()+" vw sport car";
	}

	public double cena() {

		return defCar.cena()+20000;
	}

}
