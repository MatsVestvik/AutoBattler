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

    public Scene createScene(Team playerTeam, Team enemyTeam, Stage primaryStage, Scene winScene, Scene loseScene) {
        AutoBattle autoBattle = new AutoBattle();
        autoBattle.battle(playerTeam, enemyTeam);

        if(!playerTeam.hasAliveMembers()) {
            primaryStage.setScene(loseScene);
        } else if (!enemyTeam.hasAliveMembers()) {
            primaryStage.setScene(winScene);
        }

        HBox root = new HBox(50);
        root.getChildren().add(playerTeam.getTeamView());
        root.getChildren().add(enemyTeam.getTeamView());
        Scene scene = new Scene(root);
        return scene;
    }
    
}
