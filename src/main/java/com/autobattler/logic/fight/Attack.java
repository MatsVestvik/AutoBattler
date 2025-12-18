package com.autobattler.logic.fight;

import com.autobattler.character.Character;

public class Attack {
    public static void performAttack(Character attacker, Character defender) {
        int damage = attacker.getAttackPower();
        int newHealth = defender.getHealth() - damage;
        defender.setHealth(newHealth);
    }
}
