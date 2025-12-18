package com.autobattler.scenes;

import com.autobattler.character.Team;
import com.autobattler.logic.fight.AutoBattle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

public class FightScene {

    public static void spaceEvent(Team player, Team enemy) {

        Timeline timeline = new Timeline();
        KeyFrame kf = new KeyFrame(javafx.util.Duration.millis(3000), e -> {
            player.compactTeam();
            enemy.compactTeam();
        });   
        KeyFrame kf2 = new KeyFrame(javafx.util.Duration.millis(6000), e -> {
            AutoBattle.startBattle(player, enemy);
        });
        timeline.getKeyFrames().addAll(kf, kf2);
        timeline.play();
    }
    
}
