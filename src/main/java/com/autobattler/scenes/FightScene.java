package com.autobattler.scenes;

import com.autobattler.character.Team;
import com.autobattler.logic.fight.Attack;
import com.autobattler.logic.fight.AutoBattle;
import com.autobattler.character.Character;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class FightScene {

    public static void spaceEvent(Team player, Team enemy) {

        Timeline timeline = new Timeline();
        KeyFrame kf0 = new KeyFrame(javafx.util.Duration.millis(3000), e -> {
            player.compactTeam();
            enemy.compactTeam();
        });   
        timeline.getKeyFrames().add(kf0);
        Character firstPosPlayer = player.getMembers().get(4);
        Character firstPosEnemy = enemy.getMembers().get(0); 


        boolean playerStronger = (firstPosPlayer.getAttackPower() - firstPosEnemy.getAttackPower()) >= 0;
        while (firstPosPlayer.isAlive() && firstPosEnemy.isAlive()) {
            if (playerStronger) {
                KeyFrame kf = new KeyFrame(Duration.millis(1000), e -> {
                    Attack.performAttack(player, enemy);
                });
                if (firstPosEnemy.isAlive()) {
                    KeyFrame kf2 = new KeyFrame(Duration.millis(2000), e -> {
                        Attack.performAttack(enemy, player);
                    });
                    timeline.getKeyFrames().addAll(kf, kf2);
                    timeline.play();
                }
                else {
                    enemy.removeMember(0);
                    enemy.compactTeam();
                    break;
                }
               
            } else {
                KeyFrame kf = new KeyFrame(Duration.millis(1000), e -> {
                    Attack.performAttack(enemy, player);
                });
                if (firstPosPlayer.isAlive()) {
                    KeyFrame kf2 = new KeyFrame(Duration.millis(2000), e -> {
                        Attack.performAttack(player, enemy);
                    });
                    timeline.getKeyFrames().addAll(kf, kf2);
                    timeline.play();
                }
                else {
                    player.removeMember(4);
                    player.compactTeam();
                    break;
                }
            }
        }
    }
    
}
