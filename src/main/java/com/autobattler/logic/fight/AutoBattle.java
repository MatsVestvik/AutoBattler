package com.autobattler.logic.fight;

import java.security.Key;

import com.autobattler.character.Team;
import com.autobattler.character.Character;

import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.animation.Timeline;

public class AutoBattle {
    
    public static void fightOneVOne(Team player, Team enemy) {
        Timeline timeline = new Timeline();

        Character firstPosPlayer = player.getMembers().get(4);
        Character firstPosEnemy = enemy.getMembers().get(0); 

        boolean playerStronger = (firstPosPlayer.getAttackPower() - firstPosEnemy.getAttackPower()) >= 0;
       
    }
}
