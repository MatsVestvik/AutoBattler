package com.autobattler.character.ability;

import com.autobattler.character.Character;

public class Ability {

    private int healAmount;
    private int damageAmount;

    public Ability(int healAmount, int damageAmount){
        this.healAmount = healAmount;
        this.damageAmount = damageAmount;
    }

    public void triggerAbility(Character character){
        character.setHealth(character.getHealth() + healAmount);
        character.setHealth(character.getHealth() - damageAmount);
    }
}
