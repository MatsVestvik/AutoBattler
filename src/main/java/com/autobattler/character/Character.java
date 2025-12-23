package com.autobattler.character;

import java.security.Key;
import java.sql.Time;
import java.util.Stack;

import com.autobattler.character.ability.Ability;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.KeyValue;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.control.Button;

public class Character {
    private int health;
    private int attackPower;
    private String name;
    private Image image;
    private ImageView imageView;
    private Text healthText;
    private Text attackText;
    private Text deltaHealthText;
    private Text deltaAttackText;

    private VBox verticalBox;
    private HBox statBox;
    private HBox deltaStatsBox;
    private StackPane characterView;
    private double size;
    private Ability ability;
    private Button button;
    private StackPane characterBox;

    public Character(int health, int attackPower, String name, Image image, Ability ability) {
        this.ability = ability;
        this.health = health;
        this.attackPower = attackPower;
        this.name = name;
        this.image = image;
        this.imageView = new ImageView(image);
        this.size = Screen.getPrimary().getBounds().getWidth() / 10;
        imageView.setFitWidth(size);
        imageView.preserveRatioProperty().set(true);
        this.healthText = new Text(String.valueOf(health));
        this.attackText = new Text(String.valueOf(attackPower));
        healthText.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-fill: green;");
        attackText.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-fill: black;");

        this.deltaAttackText = new Text("   ");
        this.deltaHealthText = new Text("   ");
        deltaHealthText.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");
        deltaAttackText.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");
        this.button = new Button();
        button.setPrefSize(size, size);
        button.opacityProperty().set(0.5);
        this.verticalBox = new VBox(5);
        this.statBox = new HBox(5);
        this.deltaStatsBox = new HBox(5);
        this.characterBox = new StackPane();
        characterBox.setPrefSize(size, size);
        characterBox.getChildren().add(imageView);
        characterBox.getChildren().add(button);
        deltaStatsBox.getChildren().addAll(deltaAttackText, deltaHealthText);
        statBox.getChildren().addAll(attackText, healthText);
        verticalBox.getChildren().addAll(characterBox, statBox, deltaStatsBox);
        this.characterView = new StackPane();
        characterView.getChildren().add(verticalBox);
        
    }

    public int getHealth() {return health;}
    public int getAttackPower() {return attackPower;}
    public String getName() {return name;}
    public Image getImage() {return image;}
    public ImageView getImageView() {return imageView;}
    public Text getHealthText() {return healthText;}
    public Text getAttackText() {return attackText;}
    public StackPane getCharacterView() {return characterView;}
    public Ability getAbility() {return ability;}
    public Text getDeltaHealthText() {return deltaHealthText;}
    public Text getDeltaAttackText() {return deltaAttackText;}
    public Button getButton() {return button;}
    public StackPane getCharacterBox() {return characterBox;}

    public void triggerAbility() {
        ability.triggerAbility(this);
    }

    public void setHealth(int health) {
        if (health<= 0){
            changeText(healthText, deltaHealthText, this.health-health);
            this.health = 0;
        } else {
            changeText(healthText, deltaHealthText, this.health - health);
            this.health = health;}
        this.healthText.setText(String.valueOf(this.health));
    }

    public void setAttackPower(int attackPower) {
        changeText(attackText, deltaAttackText, this.attackPower - attackPower);
        this.attackPower = attackPower;
        this.attackText.setText(String.valueOf(this.attackPower));
    }

    public void changeText(Text text, Text deltaText, int value) {
        final int absValue = Math.abs(value);
        Paint OriginalColor = text.getFill();
        if (value<0) {
            
            Timeline timeline = new Timeline();
            KeyFrame increase = new KeyFrame(Duration.millis(0),
                e -> {
                    deltaText.setText("+" + String.valueOf(absValue));
                    deltaText.setFill(Color.GREEN);
                    text.setFill(Color.GREEN);
                }
            );            
            KeyFrame disappear = new KeyFrame(Duration.millis(1000),
                e -> {
                    text.setFill(OriginalColor);
                    deltaText.setText("   ");
                }
            );
            timeline.getKeyFrames().addAll(increase, disappear);
            timeline.play();
        }
        else if (value>0) {
            Timeline timeline = new Timeline();
            KeyFrame decrease = new KeyFrame(Duration.millis(0),
                e -> {
                    deltaText.setText("-" + String.valueOf(absValue));
                    deltaText.setFill(Color.RED);
                    text.setFill(Color.RED);
                }
            );
            KeyFrame disappear = new KeyFrame(Duration.millis(1000),
                e -> {
                    deltaText.setText("  ");
                    text.setFill(OriginalColor);
                }
            );
            timeline.getKeyFrames().addAll(decrease, disappear);
            timeline.play();
        }
        else {
            text.setText("   ");
            
        }
    }

    public void takeDamage(int damage) {
        Timeline timeline = new Timeline();
        KeyFrame takeDamage = new KeyFrame (Duration.millis(1000), e -> {
            new KeyValue(healthText.fillProperty(), Color.RED); 
        });
        timeline.getKeyFrames().add(takeDamage);
        timeline.play();
        setHealth(this.health - damage);
    }

    public boolean isAlive() {
        return this.health > 0;
    }
}
