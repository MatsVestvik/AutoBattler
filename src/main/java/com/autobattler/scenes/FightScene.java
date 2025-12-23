package com.autobattler.scenes;

import com.autobattler.character.Team;
import com.autobattler.character.specificCharacters.CaveMan;
import com.autobattler.logic.fight.Attack;
import com.autobattler.logic.fight.AutoBattle;
import com.autobattler.character.Character;
import com.autobattler.character.Copy;
import com.autobattler.character.specificCharacters.Orc;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import com.autobattler.util.makeImage;

public class FightScene {

    public static void runFightScene(Stage primaryStage) {
        
        Team playerTeam = new Team(true);
        for (int i = 0; i < 5; i++) {
            CaveMan caveman = new CaveMan();
            Character cavemanChar = caveman.getCharacter();
            playerTeam.setMember(i, cavemanChar);
        }

        Team savePlayerTeam = Copy.copyTeam(playerTeam);

        Team enemyTeam = new Team(false);
        for (int i = 0; i < 2; i++) {
            Orc orc = new Orc();
            Character orcChar = orc.getCharacter();
            enemyTeam.setMember(i, orcChar);
        }



        YouLoseScene youLoseScene = new YouLoseScene();
        Scene loseScene = youLoseScene.createScene(primaryStage);
        
        StackPane battlePane = new StackPane();
        VBox verticalBox = new VBox();
        
        
        double size = Screen.getPrimary().getBounds().getWidth() / 10;
        HBox backgroundContainer = new HBox();
        for (int i = 0; i < 10; i++) {
            Image backgroundImage = makeImage.loadImage("/img/sprites/background/UnitBackground.gif");
            ImageView bgView = new ImageView(backgroundImage);
            bgView.setFitWidth(size);
            bgView.setPreserveRatio(true);
            backgroundContainer.getChildren().add(bgView);
        }

        HBox root = new HBox(0);
        root.getChildren().add(playerTeam.getTeamView());
        root.getChildren().add(enemyTeam.getTeamView());
        battlePane.getChildren().add(backgroundContainer);
        battlePane.getChildren().add(root);
        verticalBox.getChildren().add(battlePane);

        Scene battleScene = new Scene(verticalBox, 10 * (int)size, Screen.getPrimary().getBounds().getHeight() );
        battleScene.getStylesheets().add("src/main/resources/css/style.css");


        primaryStage.setScene(battleScene);
    
        AutoBattle autoBattle = new AutoBattle();
        autoBattle.battle(playerTeam, enemyTeam);

        Timeline[] checkBattleOutcomeRef = new Timeline[1];
        Timeline checkBattleOutcome = new Timeline(new KeyFrame(Duration.millis(500), e -> {
            if (!playerTeam.hasAliveMembers()) {
                Platform.runLater(() -> {
                    primaryStage.setScene(loseScene);
                });
                checkBattleOutcomeRef[0].stop();
            }
            else if (!enemyTeam.hasAliveMembers()) {
                Platform.runLater(() -> {
                    ShopScene.runShopScene(savePlayerTeam, primaryStage);
                });
                checkBattleOutcomeRef[0].stop();
            }
        }));
        checkBattleOutcomeRef[0] = checkBattleOutcome;
        checkBattleOutcome.setCycleCount(Timeline.INDEFINITE);
        checkBattleOutcome.play();
    }
    
}
