package com.autobattler.scenes;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class YouWinScene {
     public Scene createScene(Stage primaryStage) {
        Scene scene = new Scene(new javafx.scene.layout.StackPane(), 800, 600);
        primaryStage.setTitle("You Win!");
        return scene;
    }
}
