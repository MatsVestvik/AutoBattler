package com.autobattler.logic.fight;

import java.security.Key;

import com.autobattler.character.Character;
import com.autobattler.character.Team;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Attack {
    public static void performAttack(Team attackerTeam, Team defenderTeam) {
        Timeline timeline = new Timeline();
        Character attacker = attackerTeam.getMembers().get(4);
        Character defender = defenderTeam.getMembers().get(0);
        KeyFrame kf = new KeyFrame(
            Duration.millis(1000), e -> {
                if (attackerTeam.isPlayer()) {
                    attacker.getCharacterView().setTranslateX(-20);
                } else {
                    attacker.getCharacterView().setTranslateX(20);
                }
            }
        );
        KeyFrame kf2 = new KeyFrame(
            Duration.millis(1500), e -> {
                if (attackerTeam.isPlayer()) {
                    attacker.getCharacterView().setTranslateX(0);
                } else {
                    attacker.getCharacterView().setTranslateX(0);
                }
                applyDamage(attacker, defender);
            }
        );
        timeline.getKeyFrames().addAll(kf, kf2);
        timeline.play();
    }

    private static void applyDamage(Character attacker, Character defender) {
        int damage = attacker.getAttackPower();
        int newHealth = defender.getHealth() - damage;
        defender.setHealth(newHealth);
    }
}
