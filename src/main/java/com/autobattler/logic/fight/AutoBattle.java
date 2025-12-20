package com.autobattler.logic.fight;

import java.security.Key;
import java.sql.Time;
import java.util.List;

import com.autobattler.character.Team;
import com.autobattler.character.Character;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class AutoBattle {
    
    public void battle(Team player, Team enemy){

        Timeline battle = new Timeline();
        player.compactTeam();
        enemy.compactTeam();
        KeyFrame playerAttack = new KeyFrame(Duration.millis(3000), e -> {
            attack(player, enemy);
        });
        
        KeyFrame enemyAttack = new KeyFrame(Duration.millis(6000), e -> {
            attack(enemy, player);
        });
        battle.getKeyFrames().add(playerAttack);
        battle.getKeyFrames().add(enemyAttack);
        battle.setCycleCount(Timeline.INDEFINITE);
        battle.play();
    }

    private static void attack(Team attackerTeam, Team defenderTeam) {
        attackerTeam.removeDeadMembers();
        defenderTeam.removeDeadMembers();
        Character eChar = defenderTeam.getMembers().get(0);
        Character pChar = attackerTeam.getMembers().get(0);
        ImageView attackImage = pChar.getImageView();
        if (eChar != null && eChar.isAlive()) {
            Timeline attackMoveTimeline = new Timeline();
            KeyFrame attackMove = new KeyFrame(Duration.millis(0),
                e -> Attack.redText(pChar, eChar)
            );
            KeyFrame returnMove = new KeyFrame(Duration.millis(2000),
                new KeyValue(attackImage.xProperty(), attackImage.getX())
            );
            attackMoveTimeline.getKeyFrames().addAll(attackMove, returnMove);
            attackMoveTimeline.play();
        }

    }
}
