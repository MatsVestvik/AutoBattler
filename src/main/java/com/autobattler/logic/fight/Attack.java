package com.autobattler.logic.fight;

import java.security.Key;
import java.sql.Time;

import com.autobattler.character.Character;
import com.autobattler.character.Team;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Attack {
    public static void performAttack(Character attacker, Character defender) {
        Timeline timeline = new Timeline();
        KeyFrame attack = new KeyFrame(Duration.millis(1000) , e -> {
            
        });
        KeyFrame takeDamage = new KeyFrame (Duration.millis(1500), e -> {
            
        });
        timeline.getKeyFrames().addAll(attack, takeDamage);
        timeline.play();
    }
}