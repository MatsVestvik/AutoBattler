package com.autobattler.scenes;

import javafx.stage.Stage;
import javafx.scene.Scene;  
public class YouLoseScene {

    public Scene createScene(Stage primaryStage) {
        Scene scene = new Scene(new javafx.scene.layout.StackPane(), 800, 600);
        primaryStage.setTitle("You Lose!");
        return scene;
    }
}
