package com.autobattler.logic.fight;

import java.security.Key;

import com.autobattler.character.Team;
import com.autobattler.character.Character;

import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.animation.Timeline;

public class AutoBattle {
    
    public void battle(Team player, Team enemy){
        Timeline battle = new Timeline();
        player.compactTeam();
        enemy.compactTeam();
        KeyFrame round = new KeyFrame(Duration.millis(2000), e -> {
            for (Character pChar : player.getMembers()) {
                if (pChar != null && pChar.isAlive()) {
                    for (Character eChar : enemy.getMembers()) {
                        if (eChar != null && eChar.isAlive()) {
                            Attack.performAttack(pChar, eChar);
                            break;
                        }
                        else {
                            enemy.removeDeadMembers();
                            player.removeDeadMembers();
                        }
                    }
                }
            }
            for (Character eChar : enemy.getMembers()) {
                if (eChar != null && eChar.isAlive()) {
                    for (Character pChar : player.reverseMembers(player.getMembers())) {
                        if (pChar != null && pChar.isAlive()) {
                            Attack.performAttack(eChar, pChar);
                            break;
                        }
                        else {
                            player.removeDeadMembers();
                            enemy.removeDeadMembers();
                        }
                    }
                }
            }
        });
        battle.getKeyFrames().add(round);
        battle.setCycleCount(Timeline.INDEFINITE);
        battle.play();
    }
}
