package com.autobattler.scenes;

import com.autobattler.character.Team;
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
        shopLayout.getChildren().add(fightButton);
        Scene shopScene = new Scene(shopLayout, Screen.getPrimary().getBounds().getWidth(), Screen.getPrimary().getBounds().getHeight());
        primaryStage.setScene(shopScene);
        primaryStage.show();

        fightButton.setOnAction(e -> {
            FightScene.runFightScene(primaryStage);
        });
    }
}
