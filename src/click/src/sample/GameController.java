package click;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class GameController {

    /*private int dodanaKasa;

    private Game game;

    @FXML
    private Label kaska;


    public void exit() {
        Platform.exit();
    }

    public void backToMenu() {
        try {
            Parent gameRoot = FXMLLoader.load(getClass().getResource("main.fxml"));
            Scene gameScene = new Scene(gameRoot, 800, 600);
            MainClick.primaryStage.setScene(gameScene);
            MainClick.primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dodajKase(){
        game.setScore(1 + dodanaKasa);
        kaska.setText("Masz tyle " + game.getScore()+" kasy");
    }


    public void zakup(ActionEvent event) {
        Button guzikNacisniety;


        try {
            guzikNacisniety = (Button)event.getSource();
            dodanaKasa += Integer.valueOf(guzikNacisniety.getText());
        } catch (Exception ex) {
            System.out.println("cos nie tak z guzikiem");
        }



    }


    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Label getKaska() {
        return kaska;
    }

    public void setKaska(Label kaska) {
        this.kaska = kaska;
    }

    public int getDodanaKasa() {
        return dodanaKasa;
    }

    public void setDodanaKasa(int dodanaKasa) {
        this.dodanaKasa = dodanaKasa;
    }*/
}
