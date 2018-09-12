package dp;

public class VWTransportCar  extends VW{

	public VWTransportCar(Car newCar) {
		super(newCar);
		System.out.println("kupujesz trasportera");
	}

	
	public String opis() {

		return defCar.opis()+" vw transport car";
	}

	public double cena() {

		return defCar.cena()+5000;
	}
	
	
}
