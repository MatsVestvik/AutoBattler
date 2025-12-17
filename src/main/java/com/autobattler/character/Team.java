package com.autobattler.character;

import java.util.ArrayList;
import java.util.List;

import com.autobattler.character.Character;

public class Team {
    private List<Character> members;

    public Team() {
        members = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            members.add(null);
        }
    }

    public List<Character> getMembers() {return members;}

    public void setMember(int position, Character character) {
        if (position >= 0 && position < members.size()) {
            members.set(position, character);
        }
    }

    
}
