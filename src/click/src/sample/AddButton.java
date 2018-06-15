package sample;

import javafx.scene.control.Button;

public class AddButton extends Button {

    private int upgrade;

    public AddButton(int up){
        this.upgrade = up;
    }

    public int getUpgrade() {
        return upgrade;
    }

    public void setUpgrade(int upgrade) {
        this.upgrade = upgrade;
    }
}
