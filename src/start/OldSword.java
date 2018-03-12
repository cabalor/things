package start;

public class OldSword extends Swords{

	public OldSword()  {
		super(OldSword.class.getName(), 1, 3);
	}

	@Override
	public String toString() {
		return "Stary miecz zadaje obrażnia od "+ super.minDmg +" do "+super.maxDmg;
	}
	
	
}
