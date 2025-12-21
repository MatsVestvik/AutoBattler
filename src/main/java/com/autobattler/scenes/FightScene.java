package com.autobattler.scenes;

import com.autobattler.character.Team;
import com.autobattler.logic.fight.Attack;
import com.autobattler.logic.fight.AutoBattle;
import com.autobattler.character.Character;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.image.ImageView;
import com.autobattler.util.makeImage;

public class FightScene {

    public static void runFightScene(Stage primaryStage) {
        Image cavemanAvatar = makeImage.loadImage("/img/sprites/character/Basic_Character.gif");
        Image orcAvatar = makeImage.loadImage("/img/sprites/character/Orc.gif");

        Character caveman1 = new Character(100, 20, "Warrior", cavemanAvatar);
        Character caveman2 = new Character(100, 20, "Warrior", cavemanAvatar);
        Character caveman3 = new Character(100, 20, "Warrior", cavemanAvatar);
        Character caveman4 = new Character(100, 20, "Warrior", cavemanAvatar);
        Character caveman5 = new Character(100, 20, "Warrior", cavemanAvatar);


        Team playerTeam = new Team(true);
        playerTeam.setMember(0, caveman1);
        playerTeam.setMember(4, caveman2);
        playerTeam.setMember(2, caveman3);
        playerTeam.setMember(3, caveman4);
        playerTeam.setMember(1, caveman5);

        Character orc1 = new Character(120, 25, "Orc", orcAvatar);
        Character orc2 = new Character(120, 25, "Orc", orcAvatar);
        Character orc3 = new Character(120, 25, "Orc", orcAvatar);
        Character orc4 = new Character(120, 25, "Orc", orcAvatar);


        Team enemyTeam = new Team(false);
        
        enemyTeam.setMember(1, orc1);
        enemyTeam.setMember(3, orc2);
        enemyTeam.setMember(0, orc3);
        enemyTeam.setMember(4, orc4);


        YouLoseScene youLoseScene = new YouLoseScene();
        Scene loseScene = youLoseScene.createScene(primaryStage);
        
        StackPane battlePane = new StackPane();
        
        double size = Screen.getPrimary().getBounds().getWidth() / 10;
        HBox backgroundContainer = new HBox();
        for (int i = 0; i < 10; i++) {
            Image backgroundImage = makeImage.loadImage("/img/sprites/background/UnitBackground.gif");
            ImageView bgView = new ImageView(backgroundImage);
            bgView.setFitWidth(size);
            bgView.setPreserveRatio(true);
            backgroundContainer.getChildren().add(bgView);
        }

        HBox root = new HBox(0);
        root.getChildren().add(playerTeam.getTeamView());
        root.getChildren().add(enemyTeam.getTeamView());
        battlePane.getChildren().add(backgroundContainer);
        battlePane.getChildren().add(root);
        Scene battleScene = new Scene(battlePane, 10 * (int)size, (int)size * 2);


        primaryStage.setScene(battleScene);
    
        AutoBattle autoBattle = new AutoBattle();
        autoBattle.battle(playerTeam, enemyTeam);

        Timeline checkBattleOutcome = new Timeline(new KeyFrame(Duration.millis(500), e -> {
            if (!playerTeam.hasAliveMembers()) {
                Platform.runLater(() -> {
                    primaryStage.setScene(loseScene);
                });
            }
            else if (!enemyTeam.hasAliveMembers()) {
                Platform.runLater(() -> {
                    YouWinScene youWinScene = new YouWinScene();
                    Scene winScene = youWinScene.createScene(primaryStage);
                    primaryStage.setScene(winScene);
                });
            }
        }));
        checkBattleOutcome.setCycleCount(Timeline.INDEFINITE);
        checkBattleOutcome.play();
    }
    
}
