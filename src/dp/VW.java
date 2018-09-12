package dp;

public abstract class VW implements Car {

	protected Car defCar;

	public VW(Car newCar) {

		defCar = newCar;
	}

	public String opis() {

		return defCar.opis();
	}

	public double cena() {

		return defCar.cena();
	}

}
