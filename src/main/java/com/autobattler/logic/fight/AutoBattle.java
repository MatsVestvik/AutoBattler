package com.autobattler.logic.fight;

import java.security.Key;

import com.autobattler.character.Team;
import com.autobattler.character.Character;

import javafx.animation.KeyFrame;
import javafx.util.Duration;

public class AutoBattle {
    
    public static void startBattle(Team player, Team enemy) {
        Character firstPosPlayer = player.getMembers().get(4);
        Character firstPosEnemy = enemy.getMembers().get(0); 

        boolean playerStronger = (firstPosPlayer.getAttackPower() - firstPosEnemy.getAttackPower()) >= 0;

        if (playerStronger) {
            Attack.performAttack(firstPosPlayer, firstPosEnemy);
            if (firstPosEnemy.isAlive()) {
                Attack.performAttack(firstPosEnemy, firstPosPlayer);
            }
        } else {
            Attack.performAttack(firstPosEnemy, firstPosPlayer);
            if (firstPosPlayer.isAlive()) {
                Attack.performAttack(firstPosPlayer, firstPosEnemy);
            }
        }
    }
}
