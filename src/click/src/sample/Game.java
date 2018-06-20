package sample;

import java.util.List;

import static sample.MainController.addBtn;
import static sample.MainController.game;
import static sample.MainController.klikacz;
import static sample.Pietro.checkButony;

public class Game {

    private int currentIincome;

    private int score;

    private double mnoznik;

    private double pietroMulti;

    public Game() {
        this.score = 0;
        this.mnoznik = 1;
        this.currentIincome = 10;
        this.pietroMulti = 1;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getMnoznik() {
        return mnoznik;
    }
    public void setMnoznik(double mnoznik) {
        this.mnoznik = mnoznik;
    }

    public int getCurrentIincome() {
        return currentIincome;
    }

    public void setCurrentIincome(int currentIincome) {
        this.currentIincome = currentIincome;
    }

    public double getPietroMulti() {
        return pietroMulti;
    }

    public void setPietroMulti(double pietroMulti) {
        this.pietroMulti = pietroMulti;
    }

    public void kup(ClickButton btn){
        if(Double.valueOf(btn.getText()) <= getScore()){
            btn.setDisable(false);
            btn.setOnAction(e -> {
                setCurrentIincome(getCurrentIincome() + Double.valueOf(btn.getText()).intValue());
                game.setScore(game.getScore() - Double.valueOf(btn.getText()).intValue());
                MainController.kasa.setText("masz " + getScore() + " kasy");
                btn.setMulti(btn.getMulti() + 0.5);
                btn.setText(String.valueOf(Double.valueOf(btn.getText()).intValue() * btn.getMulti()));
                klikacz.setText("Dodaj kase " + game.getCurrentIincome());
                if(game.getScore()<addBtn.getUpgrade()){
                    addBtn.setDisable(true);
                }
                checkButony();
            });
        }
    }

}
