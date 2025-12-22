package com.autobattler.character.specificCharacters;

import javafx.scene.image.Image;
import com.autobattler.character.Character;
import com.autobattler.util.makeImage;
import com.autobattler.character.ability.Ability;

public class Orc {
    private Character orc;  
    public Orc() {
        Ability ability = new Ability(0, 5);
        Image orcAvatar = makeImage.loadImage("/img/sprites/character/Orc.gif");
        this.orc = new Character(50, 25, "Orc", orcAvatar, ability);
    }

    public void ability() {
        orc.setHealth(orc.getAttackPower() + 10);
    }
    public Character getCharacter() {
        return orc;
    }
}
