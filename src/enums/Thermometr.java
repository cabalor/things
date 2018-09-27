package src.enums;

public final class Thermometr {






    private Thermometr(){}

    private static Thermometr ther = null;
    private Temp temp;
    private double deg;

    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    public double getDeg() {
        return deg;
    }

    public void setDeg(double deg) {
        this.deg = deg;
    }

    public static Thermometr getThermometr(){
        if (ther == null){
            ther = new Thermometr();
        }
        return ther;
    }


    @Override
    public String toString() {
        return "Thermometr{" +
                "temp=" + temp +
                ", deg=" + deg +
                '}';
    }
}
