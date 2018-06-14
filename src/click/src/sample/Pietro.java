package click;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import java.util.ArrayList;

import static click.MainController.game;

public class Pietro {

    public static ArrayList<ClickButton> buttony = new ArrayList<>();

        public static HBox pietro(){
            HBox box = new HBox(10);
            for(int i =0; i<6; i++){
                ClickButton btn = Guziki.btn();
                btn.setText(String.valueOf(game.getMnoznik() * (100*(i+1))));
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

}
