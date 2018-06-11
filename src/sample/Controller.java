package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Font;


public class Controller {

    @FXML
    private Label myLabel;
    @FXML
    private TextArea textArea;
    @FXML
    private RadioMenuItem font1;
    @FXML
    private RadioMenuItem font2;
    @FXML
    private RadioMenuItem font3;
    @FXML
    private MenuBar menubar;

    public void onClickButton() {
        System.out.println("Kliknięte!");
        myLabel.setText(String.valueOf(Integer.valueOf(myLabel.getText())+1));

    }

    public void zmiana(){
        if(font1.isSelected()){
            textArea.setFont(new Font("Courier New", 24));
        }
    }

    public void onChange1(){
        textArea.setFont(new Font("Courier New", 24));
    }

    public void onChange2(){
        textArea.setFont(new Font("Courier New", 30));
    }

    public void onChange3(){
        textArea.setFont(new Font("Courier New", 11));
    }

    public void exit(){
        Platform.exit();
    }

    @FXML
    public void initialize() {
        System.out.println(myLabel);
        ToggleGroup tg = new ToggleGroup();
        font1.setToggleGroup(tg);
        font2.setToggleGroup(tg);
        font3.setToggleGroup(tg);
    }

}
