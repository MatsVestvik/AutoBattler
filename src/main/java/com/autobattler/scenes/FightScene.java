package com.autobattler.scenes;

import com.autobattler.character.Team;
import com.autobattler.logic.fight.Attack;
import com.autobattler.logic.fight.AutoBattle;
import com.autobattler.character.Character;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class FightScene {

    public static void runFightScene(Stage primaryStage, Team playerTeam, Team enemyTeam) {
        AutoBattle autoBattle = new AutoBattle();
        autoBattle.battle(playerTeam, enemyTeam);

        YouLoseScene youLoseScene = new YouLoseScene();
        Scene loseScene = youLoseScene.createScene(primaryStage);

        if(!playerTeam.hasAliveMembers()) {
            primaryStage.setScene(loseScene);
        } else if (!enemyTeam.hasAliveMembers()) {
            primaryStage.setScene(loseScene);
        }

        HBox root = new HBox(50);
        root.getChildren().add(playerTeam.getTeamView());
        root.getChildren().add(enemyTeam.getTeamView());
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }
    
}
