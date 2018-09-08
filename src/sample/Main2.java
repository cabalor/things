package sample;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main2 extends Application {


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

        Menu menu2 = new Menu("Font");

        MenuItem font1 = new MenuItem("font1");

        font1.setOnAction( e -> ta.setFont(new Font("Courier New", 24)));
        MenuItem font2 = new MenuItem("font2");
        font2.setOnAction( e -> ta.setFont(new Font("sans-serif", 28)));

        MenuItem font3 = new MenuItem("font3");
        font3.setOnAction( e -> ta.setFont(new Font("Times New Roman", 20)));

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

        primaryStage.setTitle("text app");
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
