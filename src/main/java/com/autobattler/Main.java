package com.autobattler;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import com.autobattler.util.makeImage;
import com.autobattler.character.Character;
import com.autobattler.character.Team;
import com.autobattler.logic.fight.Attack;
import com.autobattler.logic.fight.AutoBattle;
import com.autobattler.scenes.FightScene;
import com.autobattler.scenes.StartScene;
import com.autobattler.scenes.YouLoseScene;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        
        

        YouLoseScene youLoseScene = new YouLoseScene();
        Scene loseSceneInstance = youLoseScene.createScene(primaryStage);

        StartScene.runStartScene(primaryStage, loseSceneInstance, loseSceneInstance);

        primaryStage.setTitle("AutoBattler");
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
