package com.autobattler.character;

public class Copy {
    public static Team copyTeam(Team original) {
        Team copy = new Team(original.isPlayer());
        for (int i = 0; i < original.getMembers().size(); i++) {
            Character member = original.getMembers().get(i);
            if (member != null) {
                Character memberCopy = new Character(
                    member.getHealth(),
                    member.getAttackPower(),
                    member.getName(),
                    member.getImage(),
                    member.getAbility()
                );
                copy.setMember(i, memberCopy);
            }
        }
        return copy;
    }
}
