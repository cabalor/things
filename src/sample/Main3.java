/*
package sample;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main3 extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        Group root = new Group();

        Scene scene = new Scene( root, 800, 600);

        MenuBar menuBar = new MenuBar();

        Menu menu = new Menu("File");

        MenuItem exit = new MenuItem("Exit");

        exit.setOnAction( e -> System.exit(0)
        );

        menu.getItems().add(exit);
        TextArea ta = new TextArea("dupa dupa");


        //ToggleGroup tg = new ToggleGroup();

        Menu menu2 = new Menu("Font");

        MenuItem font1 = new MenuItem("font1");

        font1.setOnAction( e -> ta.setFont(new Font("Courier New", 24)));
        MenuItem font2 = new MenuItem("font2");
        font2.setOnAction( e -> ta.setFont(new Font("sans-serif", 28)));

        MenuItem font3 = new MenuItem("font3");
        font3.setOnAction( e -> ta.setFont(new Font("Times New Roman", 20)));

        //RadioMenuItem r1 = new RadioMenuItem("r1");
        //r1.setToggleGroup(tg);

        //RadioMenuItem r2 = new RadioMenuItem("r2");
        //r2.setToggleGroup(tg);

        menu2.getItems().add(font1);
        menu2.getItems().add(font2);
        menu2.getItems().add(font3);

        menuBar.getMenus().add(menu);
        menuBar.getMenus().add(menu2);

        menuBar.prefWidthProperty().bind(
                primaryStage.widthProperty()
        );


        ta.prefWidthProperty().bind(
                primaryStage.widthProperty()
        );

        ta.prefHeightProperty().bind(
                primaryStage.heightProperty()
        );

        GridPane gp = new GridPane();
        gp.setHgap(5);
        gp.setVgap(5);

        GridPane.setHalignment(menuBar, HPos.RIGHT);
        gp.add(menuBar, 0, 0);
        GridPane.setHalignment(ta, HPos.RIGHT);
        gp.add(ta, 0, 1);

        root.getChildren().add(gp);
        //root.getChildren().add(ta);


        //Label fNameLabel = new Label("Imie:");
        */
/*TextField fNameField = new TextField();

        Label lNameLabel = new Label("Nazwisko:");
        TextField lNameField = new TextField();

        GridPane gp = new GridPane();
        gp.setHgap(5);
        gp.setVgap(5);
        gp.setPadding(new Insets(5));

        GridPane.setHalignment(fNameLabel, HPos.RIGHT);
        gp.add(fNameLabel, 0, 0);

        GridPane.setHalignment(fNameField, HPos.LEFT);
        gp.add(fNameField, 1, 0);

        GridPane.setHalignment(lNameLabel, HPos.RIGHT);
        gp.add(lNameLabel, 0, 1);

        GridPane.setHalignment(lNameField, HPos.LEFT);
        gp.add(lNameField, 1,1);

        lNameField.setLayoutX(100);
        lNameField.setLayoutY(50);*//*


        //root.getChildren().add(gp);

        primaryStage.setTitle("First FX app");
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
*/
