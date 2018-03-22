package start;

public class Attribiutes {

	// zolek8#2590 battle id Bartek

	private int Strength;
	private int Dexterity;
	private int Vitality;
	private int Inteligence;
	private int Luck;

	public int getStrength() {
		return Strength;
	}

	public void setStrength(int strength) {
		Strength = strength;
	}

	public int getDexterity() {
		return Dexterity;
	}

	public void setDexterity(int dexterity) {
		Dexterity = dexterity;
	}

	public int getVitality() {
		return Vitality;
	}

	public void setVitality(int vitality) {
		Vitality = vitality;
	}

	public int getInteligence() {
		return Inteligence;
	}

	public void setInteligence(int inteligence) {
		Inteligence = inteligence;
	}

	public int getLuck() {
		return Luck;
	}

	public void setLuck(int luck) {
		Luck = luck;
	}

	public Attribiutes(int strength, int dexterity, int vitality, int inteligence, int luck) {
		this.Strength = strength;
		this.Dexterity = dexterity;
		this.Vitality = vitality;
		this.Inteligence = inteligence;
		this.Luck = luck;
	}

	@Override
	public String toString() {
		return "Twoje atrubuty " + " S " + this.Strength + " D " + this.Dexterity + " I " + this.Inteligence + " V "
				+ this.Vitality + " L " + this.Luck;
	}

}
