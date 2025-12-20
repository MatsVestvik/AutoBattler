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
        KeyFrame playerAttack = new KeyFrame(Duration.millis(1000), e -> {
            enemy.removeDeadMembers();
            player.removeDeadMembers();
            Character eChar = enemy.getMembers().get(0);
            Character pChar = player.reverseMembers(player.getMembers()).get(0);
            if (eChar != null && eChar.isAlive()) {
                eChar.setHealth(eChar.getHealth() - pChar.getAttackPower());
            }
                    
           
        });
        
        KeyFrame enemyAttack = new KeyFrame(Duration.millis(2000), e -> {
            enemy.removeDeadMembers();
            player.removeDeadMembers();
            Character pChar = player.reverseMembers(player.getMembers()).get(0);
            Character eChar = enemy.getMembers().get(0);
            if (pChar != null && pChar.isAlive()) {
                pChar.setHealth(pChar.getHealth() - eChar.getAttackPower());
            }
                    
        });
        battle.getKeyFrames().add(playerAttack);
        battle.getKeyFrames().add(enemyAttack);
        battle.setCycleCount(Timeline.INDEFINITE);
        battle.play();
    }
}
