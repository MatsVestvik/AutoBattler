package com.autobattler.character.specificCharacters;

import com.autobattler.character.ability.Ability;
import com.autobattler.character.Character;
import com.autobattler.util.makeImage;

import javafx.scene.image.Image;

public class Baker {
    private Character baker;

    public Baker(){
        Ability ability = new Ability(40, 0);
        Image bakerAvatar = makeImage.loadImage("/img/sprites/character/Baker.gif");
        this.baker = new Character(10, 5, "Warrior", bakerAvatar, ability);
    }

    public void ability(){
        baker.setHealth(baker.getHealth() + 40);
    }

    public Character getCharacter() {
        return baker;
    }
}
