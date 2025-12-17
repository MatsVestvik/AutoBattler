package com.autobattler;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

import com.autobattler.util.makeImage;
import com.autobattler.character.Character;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Image Avatar = makeImage.loadImage("/img/sprites/character/Basic_Character.gif");
        Character character = new Character(100, 20, "Warrior", Avatar);

        Team team = new Team();
        team.setMember(0, character);

        StackPane root = new StackPane();
        root.getChildren().add(character.getImageView());
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("AutoBattler");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
