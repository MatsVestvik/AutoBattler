package com.autobattler.scenes;

import javafx.stage.Stage;

import com.autobattler.character.Team;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import com.autobattler.util.makeImage;
import com.autobattler.character.Character;

public class StartScene {
    public static void runStartScene(Stage primaryStage) {
        
        

        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> {
            ShopScene.runShopScene(new Team(true), primaryStage);
        });

        Scene startScene = new Scene(new StackPane(startButton), 800, 600);
        primaryStage.setScene(startScene);
    }
}
