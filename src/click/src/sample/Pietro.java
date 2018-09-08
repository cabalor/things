package sample;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

import java.util.ArrayList;

import static sample.MainController.game;
import static sample.MainController.row;

public class Pietro {

    public static ArrayList<ClickButton> buttony = new ArrayList<>();
    public static ArrayList<ClickButton> buttony2 = new ArrayList<>();
    public static ArrayList<ClickButton> buttony3 = new ArrayList<>();

        public static HBox pietro(){
            HBox box = new HBox(10);
            for(int i =0; i<6; i++){
                ClickButton btn = Guziki.btn();
                btn.setText(String.valueOf(game.getMnoznik() * (100*(i+1))));
                btn.setFont(new Font("Arial Black", 13));
                btn.setShape(new Circle(10));
                btn.setStyle(color(row));
                box.getChildren().add(btn);
                buttony.add(btn);
            }
            game.setMnoznik(game.getMnoznik()+0.5);
            box.setAlignment(Pos.CENTER);
            return box;
        }

        public static void checkButony(){
            for (ClickButton but : buttony) {
                if(Double.valueOf(but.getText()).intValue() > game.getScore()){
                    but.setDisable(true);
                }
            }
        }

        private static String color(int i){
            return i%2==0?"-fx-background-color: red":"-fx-background-color: purple";
        }
}
