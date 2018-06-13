package sample;


import javafx.scene.control.Button;

public class ClickButton extends Button {

    private double multi;

    public ClickButton(double mult){
        this.multi = mult;
    }

    public double getMulti() {
        return multi;
    }

    public void setMulti(double multi) {
        this.multi = multi;
    }
}
