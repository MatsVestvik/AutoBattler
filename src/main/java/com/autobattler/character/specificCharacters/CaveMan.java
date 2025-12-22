package com.autobattler.character.specificCharacters;

import javafx.scene.image.Image;

import com.autobattler.character.Character;
import com.autobattler.character.ability.Ability;
import com.autobattler.util.makeImage;
import com.autobattler.character.ability.Heal;
import com.autobattler.character.ability.Ability;

public class CaveMan {
    
    private Character caveman;

    public CaveMan(){
        Ability ability = new Ability(10, 0);
        Image cavemanAvatar = makeImage.loadImage("/img/sprites/character/Basic_Character.gif");
        this.caveman = new Character(50, 20, "Warrior", cavemanAvatar, ability);
    }

    public void ability(){
        caveman.setHealth(caveman.getHealth() + 10);
    }

    public Character getCharacter() {
        return caveman;
    }
}
