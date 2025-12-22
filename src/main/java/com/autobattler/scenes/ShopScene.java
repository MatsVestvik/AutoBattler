package com.autobattler.scenes;

import com.autobattler.character.Team;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.Scene;

public class ShopScene {
    public static void runShopScene(Team playerTeam, Stage primaryStage) {
        VBox shopLayout = new VBox();
        shopLayout.setSpacing(10);
        shopLayout.getChildren().add(playerTeam.getTeamView());
        
        Scene shopScene = new Scene(shopLayout, 800, 600);
        primaryStage.setScene(shopScene);
        primaryStage.show();
    }
}
