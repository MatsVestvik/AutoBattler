package com.autobattler.logic.fight;

import java.security.Key;
import java.sql.Time;

import com.autobattler.character.Character;
import com.autobattler.character.Team;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Attack {
    public static void redText(Character attacker, Character defender) {
        Timeline timeline = new Timeline();
        defender.takeDamage(attacker.getAttackPower());
        KeyFrame attack = new KeyFrame(Duration.millis(100) , 
            new KeyValue(defender.getHealthText().fillProperty(), Color.RED),
            new KeyValue(attacker.getAttackText().fillProperty(), Color.BLUE)
        );
        KeyFrame takeDamage = new KeyFrame (Duration.millis(1000),
            new KeyValue(defender.getHealthText().fillProperty(), Color.GREEN),
            new KeyValue(attacker.getAttackText().fillProperty(), Color.BLACK)
        );
        timeline.getKeyFrames().addAll(attack, takeDamage);
        timeline.play();
    }
}