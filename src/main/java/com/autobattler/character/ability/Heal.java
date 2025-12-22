package com.autobattler.character.ability;

import com.autobattler.character.Character;
public class Heal {
    
    public static void heal(Character character, int amount) {
        character.setHealth(character.getHealth() + amount);
    }
}
