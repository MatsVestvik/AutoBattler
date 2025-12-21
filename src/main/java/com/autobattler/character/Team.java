package com.autobattler.character;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private List<Character> members;
    private HBox teamView;
    private boolean player;
    private static final int TEAM_SIZE = 5;
    private double size;

    public Team(boolean player) {
        this.player = player;
        this.size = Screen.getPrimary().getBounds().getWidth() / 10;
        members = new ArrayList<>();
        teamView = new HBox();
        teamView.setSpacing(0);
        teamView.setPrefHeight(size);
        teamView.setPrefWidth(5 * size);
        
        initializePlaceholders();
    }

    private void initializePlaceholders() {
        for (int i = 0; i < TEAM_SIZE; i++) {
            VBox placeholder = createPlaceholder();
            members.add(null);
            teamView.getChildren().add(placeholder);
        }
    }

    private VBox createPlaceholder() {
        VBox placeholder = new VBox();
        placeholder.setPrefSize(size, size);
        return placeholder;
    }

    public List<Character> getMembers() {
        if (player){
            return new ArrayList<>(reverseMembers(members)); // Return defensive copy
        }
        else{
            return new ArrayList<>(members); // Return defensive copy
        }
    }
    
    public List<Character> getActiveMembers() {
        List<Character> activeMembers = new ArrayList<>();
        for (Character character : members) {
            if (character != null) {
                activeMembers.add(character);
            }
        }
        return activeMembers;
    }

    public HBox getTeamView() {
        return teamView;
    }

    public boolean isPlayer() {
        return player;
    }

    public boolean setMember(int position, Character character) {
        if (position < 0 || position >= TEAM_SIZE) {
            return false;
        }
        
        // Check if position is already occupied
        if (members.get(position) != null) {
            return false;
        }
        
        if (character != null) {
            if (!player) {
                character.getImageView().setScaleX(-1);
            }
            members.set(position, character);
            teamView.getChildren().set(position, character.getCharacterView());
            return true;
        }
        return false;
    }
    
    public boolean removeMember(int position) {
        if (position < 0 || position >= TEAM_SIZE || members.get(position) == null) {
            return false;
        }
        
        members.set(position, null);
        teamView.getChildren().set(position, createPlaceholder());
        return true;
    }
    
    public boolean removeMember(Character character) {
        int position = members.indexOf(character);
        if (position != -1) {
            return removeMember(position);
        }
        return false;
    }

    public void moveAllMembersForward() {
        // Move from the end to the beginning to avoid overwriting
        for (int i = TEAM_SIZE - 2; i >= 0; i--) {
            Character current = members.get(i);
            if (current != null) {
                int nextPosition = i + 1;
                
                // Find the next available position
                while (nextPosition < TEAM_SIZE && members.get(nextPosition) != null) {
                    nextPosition++;
                }
                
                if (nextPosition < TEAM_SIZE) {
                    // Move character to new position
                    members.set(nextPosition, current);
                    members.set(i, null);
                    teamView.getChildren().set(nextPosition, current.getCharacterView());
                    teamView.getChildren().set(i, createPlaceholder());
                }
            }
        }
    }
    
    public void compactTeam() {
        List<Character> tempMembers = new ArrayList<>();
        List<javafx.scene.Node> tempViews = new ArrayList<>();
        
        // Collect all non-null members
        for (int i = 0; i < TEAM_SIZE; i++) {
            Character member = members.get(i);
            if (member != null) {
                tempMembers.add(member);
                tempViews.add(member.getCharacterView());
            }
        }
        
        // Clear and rebuild
        members.clear();
        teamView.getChildren().clear();
        if (!player){
            for (int i = 0; i < tempMembers.size(); i++) {
                members.add(tempMembers.get(i));
                teamView.getChildren().add(tempViews.get(i));
            }
        }
        // Add active members first
        // Fill remaining slots with placeholders
        for (int i = tempMembers.size(); i < TEAM_SIZE; i++) {
            members.add(null);
            teamView.getChildren().add(createPlaceholder());
        }

        if (player){
            for (int i = 0; i < tempMembers.size(); i++) {
                members.add(tempMembers.get(i));
                teamView.getChildren().add(tempViews.get(i));
            }
        }
    }
    
    public int getEmptySlotCount() {
        int count = 0;
        for (Character member : members) {
            if (member == null) {
                count++;
            }
        }
        return count;
    }
    
    public boolean hasEmptySlots() {
        return getEmptySlotCount() > 0;
    }
    
    public int getFirstEmptySlot() {
        for (int i = 0; i < TEAM_SIZE; i++) {
            if (members.get(i) == null) {
                return i;
            }
        }
        return -1;
    }
    
    public boolean addMember(Character character) {
        int emptySlot = getFirstEmptySlot();
        if (emptySlot != -1) {
            return setMember(emptySlot, character);
        }
        return false;
    }
    
    public void clearTeam() {
        for (int i = 0; i < TEAM_SIZE; i++) {
            members.set(i, null);
            teamView.getChildren().set(i, createPlaceholder());
        }
    }
    
    public boolean isPositionOccupied(int position) {
        return position >= 0 && position < TEAM_SIZE && members.get(position) != null;
    }
    
    public Character getMemberAt(int position) {
        if (position >= 0 && position < TEAM_SIZE) {
            return members.get(position);
        }
        return null;
    }

    public boolean hasAliveMembers() {
        for (Character member : members) {
            if (member != null && member.isAlive()) {
                return true;
            }
        }
        return false;
    }
    

    public List<Character> reverseMembers(List <Character> members) {
        List<Character> reversed = new ArrayList<>();
        for (int i = TEAM_SIZE - 1; i >= 0; i--) {
            reversed.add(members.get(i));
        }
        return reversed;
    }

    public Character getFirstAliveMember() {
        compactTeam();
        if(!player){
            for (int i = TEAM_SIZE - 1; i >=0 ; i--) {
                Character member = members.get(i);
                if (member != null && member.isAlive()) {
                    return member;
                }
            }
            return null;
        }
        else{
            members = reverseMembers(members);
            for (int i = 0; i < TEAM_SIZE; i++) {
                Character member = members.get(i);
                if (member != null && member.isAlive()) {
                    return member;
                }
            }
            return null;
        }
    }

    public void removeDeadMembers() {
        for (int i = 0; i < TEAM_SIZE; i++) {
            Character member = members.get(i);
            if (member != null && !member.isAlive()) {
                removeMember(i);
                teamView.getChildren().set(i, createPlaceholder());
                this.compactTeam();
            }
        }
    }
}