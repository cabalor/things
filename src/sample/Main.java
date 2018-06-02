package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.Group;
import javafx.scene.control.*;

public class Main extends Application {

    private Scene scene;
    private Group root;

    @Override
    public void start(Stage primaryStage) {
        root = new Group();

        Button btn = new Button("hello world");

        btn.setOnAction( e -> {
                    if (btn.getText().equals("hello world")) {
                        btn.setText("hi");
                    } else {
                        btn.setText("hello world");
                    }
                }
        );

        root.getChildren().add(btn);

        scene = new Scene(root, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Hello World App");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

