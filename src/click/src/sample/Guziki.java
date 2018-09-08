package sample;


public class Guziki {

    public static ClickButton btn(){
        ClickButton btn = new ClickButton(1);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setDisable(true);
        return btn;
    }

}
