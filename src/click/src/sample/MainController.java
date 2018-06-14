package click;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import static click.Pietro.checkButony;


public class MainController {

    private int dodanaKasa;

    public static Game game = new Game();

    private int row = 2;

    int maxRows = 15;

    public static AddButton addBtn;

    public static Button klikacz;

    public static Label kasa;

    public static Alert alert = new Alert(Alert.AlertType.INFORMATION);


    @FXML
    private Label kaska;


    public void startButton() {

        Group root = new Group();

        Scene gameScene = new Scene(root, 800, 600);

        MenuBar menuBar = new MenuBar();

        Menu menu = new Menu("Opcje");

        MenuItem exit = new MenuItem("Wyjscie");
        MenuItem back = new MenuItem("wroc do menu");

        back.setOnAction(e -> {
            try {
                Parent gameRoot = FXMLLoader.load(getClass().getResource("main.fxml"));
                Scene scene = new Scene(gameRoot, 800, 600);
                MainClick.primaryStage.setScene(scene);
                MainClick.primaryStage.show();
                game.setScore(0);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        exit.setOnAction(e -> Platform.exit()
        );

        menu.getItems().add(exit);
        menu.getItems().add(back);

        menuBar.getMenus().add(menu);

        GridPane gp = new GridPane();
        gp.setVgap(5);

        menuBar.prefWidthProperty().bind(
                MainClick.primaryStage.widthProperty()
        );
        gp.add(menuBar, 0, 0);

        gp.add(miasta(gp), 0,1);
        gp.add(guziki(gp), 0, 2);
        root.getChildren().add(gp);

        MainClick.primaryStage.setScene(gameScene);
        MainClick.primaryStage.show();


    }

    public int checkRows() {
        System.out.println("przed dodaniem " + getRow());
        if (getRow() >= maxRows) {
            alert.setTitle("alert");
            alert.setContentText("maxymalna liczba pieter osiagnieta");
            alert.showAndWait();
        } else {
            setRow(getRow());
            return getRow();
        }
        return getRow();
    }

    public HBox guziki(GridPane gp) {
        addBtn = new AddButton(50);
        addBtn.setText("Dodaj pietro za " + addBtn.getUpgrade());
        addBtn.setDisable(true);
        klikacz = new Button();
        klikacz.setText("Dodaj kase " + game.getCurrentIincome());
        kasa = new Label();
        kasa.setText("masz " + game.getScore() + " kasy");

        HBox buttonbox = new HBox(5);

        if (getRow() > 16) {
            addBtn.isDisable();
        }


        addBtn.setOnAction(e -> {
            gp.add(Pietro.pietro(), 0, checkRows());
            game.setScore(game.getScore() - addBtn.getUpgrade());
            kasa.setText("masz " + game.getScore() + " kasy");
            addBtn.setUpgrade(addBtn.getUpgrade() + 500);
            addBtn.setText("Dodaj pietro za " + addBtn.getUpgrade());
            if(game.getScore()<addBtn.getUpgrade()){
                addBtn.setDisable(true);
            }
            checkButony();
        });


        klikacz.setOnAction(e -> {
            game.setScore(game.getScore() + game.getCurrentIincome());
            kasa.setText("masz " + game.getScore() + " kasy");
            for (ClickButton but : Pietro.buttony) {
                game.kup(but);
            }
            if (addBtn.getUpgrade() <= game.getScore()) {
                addBtn.setDisable(false);
            }
        });

        buttonbox.getChildren().addAll(addBtn, klikacz, kasa);
        buttonbox.setAlignment(Pos.CENTER);
        return buttonbox;
    }

    public HBox miasta(GridPane gp){
        Button warszawa = new Button();
        warszawa.setText("Warszawa");
        warszawa.setMaxWidth(Double.MAX_VALUE);
        Button londyn = new Button();
        londyn.setText("Londyn");
        londyn.setMaxWidth(Double.MAX_VALUE);
        Button tokio = new Button();
        tokio.setText("Tokio");
        tokio.setMaxWidth(Double.MAX_VALUE);

        HBox buttonbox = new HBox(5);

        buttonbox.getChildren().addAll(warszawa, londyn, tokio);
        buttonbox.setAlignment(Pos.CENTER);
        return buttonbox;
    }


    @FXML
    public void initialize() {
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
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row + 1;
    }


}



