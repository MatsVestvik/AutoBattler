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
import javafx.stage.Stage;

public class FightScene {

    public static void runFightScene(Stage primaryStage, Team playerTeam, Team enemyTeam) {
        YouLoseScene youLoseScene = new YouLoseScene();
        Scene loseScene = youLoseScene.createScene(primaryStage);

        HBox root = new HBox(50);
        root.getChildren().add(playerTeam.getTeamView());
        root.getChildren().add(enemyTeam.getTeamView());
        Scene battleScene = new Scene(root);
        
        Platform.runLater(() -> {
            primaryStage.setScene(battleScene);
        });
        
        // Run battle in background
        new Thread(() -> {
            AutoBattle autoBattle = new AutoBattle();
            autoBattle.battle(playerTeam, enemyTeam);
            
            // Check result and switch to lose scene if needed
            Platform.runLater(() -> {
                if(!playerTeam.hasAliveMembers() || !enemyTeam.hasAliveMembers()) {
                    primaryStage.setScene(loseScene);
                }
            });
        }).start();
    }
    
}
