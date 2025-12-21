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
        Image cavemanAvatar = makeImage.loadImage("/img/sprites/character/Basic_Character.gif");
        Image orcAvatar = makeImage.loadImage("/img/sprites/character/Orc.gif");

        Character caveman1 = new Character(100, 20, "Warrior", cavemanAvatar);
        Character caveman2 = new Character(100, 20, "Warrior", cavemanAvatar);
        Character caveman3 = new Character(100, 20, "Warrior", cavemanAvatar);
        Character caveman4 = new Character(100, 20, "Warrior", cavemanAvatar);
        Character caveman5 = new Character(100, 20, "Warrior", cavemanAvatar);


        Team team = new Team(true);
        team.setMember(0, caveman1);
        team.setMember(4, caveman2);
        team.setMember(2, caveman3);
        team.setMember(3, caveman4);
        team.setMember(1, caveman5);

        Character orc1 = new Character(120, 25, "Orc", orcAvatar);
        Character orc2 = new Character(120, 25, "Orc", orcAvatar);
        Character orc3 = new Character(120, 25, "Orc", orcAvatar);
        Character orc4 = new Character(120, 25, "Orc", orcAvatar);


        Team enemyTeam = new Team(false);
        
        enemyTeam.setMember(1, orc1);
        enemyTeam.setMember(3, orc2);
        enemyTeam.setMember(0, orc3);
        enemyTeam.setMember(4, orc4);
        

        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> {
            FightScene.runFightScene(primaryStage, team, enemyTeam);
        });

        Scene startScene = new Scene(new StackPane(startButton), 800, 600);
        primaryStage.setScene(startScene);
    }
}
