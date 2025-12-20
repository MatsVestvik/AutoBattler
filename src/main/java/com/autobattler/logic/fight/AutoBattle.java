package com.autobattler.logic.fight;

import java.security.Key;

import com.autobattler.character.Team;
import com.autobattler.character.Character;

import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.animation.Timeline;

public class AutoBattle {
    
    public void battle(Team player, Team enemy){
        while (player.hasAliveMembers() && enemy.hasAliveMembers()) {
            if (player.getFirstAliveMember().getAttackPower() >= enemy.getFirstAliveMember().getAttackPower()) {
                attack(player.getFirstAliveMember(), enemy.getFirstAliveMember());
                if (enemy.getFirstAliveMember().isAlive()) {
                    attack(enemy.getFirstAliveMember(), player.getFirstAliveMember());
                }
            } else {
                attack(enemy.getFirstAliveMember(), player.getFirstAliveMember());
                if (player.getFirstAliveMember().isAlive()) {
                    attack(player.getFirstAliveMember(), enemy.getFirstAliveMember());
                }
            }
        }
    }

    public static void attack(Character attacker, Character defender) {
        int damage = attacker.getAttackPower();
        if (damage < 0) {
            damage = 0;
        }
        defender.setHealth(damage);
    }
}
