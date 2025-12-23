package com.autobattler.scenes;

import com.autobattler.character.Team;
import com.autobattler.character.specificCharacters.CaveMan;
import com.autobattler.character.specificCharacters.Orc;
import com.autobattler.character.Character;
import com.autobattler.scenes.FightScene;
import com.autobattler.character.specificCharacters.Baker;

import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.autobattler.util.makeImage;
import javafx.scene.control.Button;

public class ShopScene {
    public static void runShopScene(Team playerTeam, Stage primaryStage) {
        Button fightButton = new Button("Start Fight");
        Button buyCaveman = new Button("Buy Caveman - 50 Gold");
        Button buyOrc = new Button("Buy Orc - 50 Gold");
        Button buyBaker = new Button("Buy Baker - 50 Gold");
        VBox shopLayout = new VBox();
        StackPane teamWrapper = new StackPane();
        HBox backgroundContainer = new HBox();
        for (int i = 0; i < 10; i++) {
            Image backgroundImage = makeImage.loadImage("/img/sprites/background/UnitBackground.gif");
            ImageView bgView = new ImageView(backgroundImage);
            bgView.setFitWidth(Screen.getPrimary().getBounds().getWidth() / 10);
            bgView.setPreserveRatio(true);
            backgroundContainer.getChildren().add(bgView);
        }
        teamWrapper.getChildren().add(backgroundContainer);
        teamWrapper.getChildren().add(playerTeam.getTeamView());
        shopLayout.getChildren().add(teamWrapper);
        shopLayout.getChildren().add(buyCaveman);
        shopLayout.getChildren().add(buyOrc);
        shopLayout.getChildren().add(buyBaker);
        shopLayout.getChildren().add(fightButton);
        Scene shopScene = new Scene(shopLayout, Screen.getPrimary().getBounds().getWidth(), Screen.getPrimary().getBounds().getHeight());
        primaryStage.setScene(shopScene);
        primaryStage.show();

        buyBaker.setOnAction(e -> {
            Baker baker = new Baker();
            playerTeam.addMember(baker.getCharacter());
            updateButtons(playerTeam);
        });
        buyOrc.setOnAction(e -> {
            Orc orc = new Orc();
            playerTeam.addMember(orc.getCharacter());
            updateButtons(playerTeam);
        });
        buyCaveman.setOnAction(e -> {
            CaveMan caveman = new CaveMan();
            playerTeam.addMember(caveman.getCharacter());
            updateButtons(playerTeam);
        });
        fightButton.setOnAction(e -> {
            FightScene.runFightScene(playerTeam, primaryStage);
        });
        for (int i = 0; i < playerTeam.getSize(); i++) {
            Character character = playerTeam.getMember(i);
            if (character != null) {
                int index = i;
                character.getButton().setOnAction(e -> {
                    System.out.println("Removing character at index: " + index);
                    playerTeam.removeMember(character);
                    playerTeam.updateTeamView();
                });
            }
        }
    }

    private static void updateButtons(Team playerTeam) {
        for (int i = 0; i < playerTeam.getSize(); i++) {
            Character character = playerTeam.getMember(i);
            if (character != null) {
                int index = i;
                character.getButton().setOnAction(e -> {
                    System.out.println("Removing character at index: " + index);
                    playerTeam.removeMember(character);
                    playerTeam.updateTeamView();
                });
            }
        }
    }
}
