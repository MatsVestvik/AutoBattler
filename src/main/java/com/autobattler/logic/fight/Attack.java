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
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;

public class Attack {
    public static void redText(Character attacker, Character defender, int direction) {
        double size = Screen.getPrimary().getBounds().getWidth() / 10;
        Timeline timeline = new Timeline();
        defender.takeDamage(attacker.getAttackPower());
        attacker.triggerAbility();
        KeyFrame attack = new KeyFrame(Duration.millis(100) , 
            new KeyValue(defender.getHealthText().fillProperty(), Color.RED),
            new KeyValue(attacker.getAttackText().fillProperty(), Color.BLUE),
            new KeyValue(attacker.getCharacterView().translateXProperty(), attacker.getCharacterView().getTranslateX() + direction * size/1.5)
        );
        KeyFrame takeDamage = new KeyFrame (Duration.millis(500),
            new KeyValue(defender.getHealthText().fillProperty(), Color.GREEN),
            new KeyValue(attacker.getAttackText().fillProperty(), Color.BLACK),
            new KeyValue(attacker.getCharacterView().translateXProperty(), attacker.getCharacterView().getTranslateX())
        );
        timeline.getKeyFrames().addAll(attack, takeDamage);
        timeline.play();
    }
}