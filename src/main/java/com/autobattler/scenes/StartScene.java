package com.autobattler.scenes;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class StartScene {
    public static void runStartScene(Stage primaryStage, Scene fightScene, Scene winScene, Scene loseScene) {
        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> {
            primaryStage.setScene(fightScene);
        });

        Scene startScene = new Scene(new StackPane(startButton), 800, 600);
        primaryStage.setScene(startScene);
    }
}
