package com.autobattler.character;

import java.security.Key;
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

public class Character {
    private int health;
    private int attackPower;
    private String name;
    private Image image;
    private ImageView imageView;
    private Text healthText;
    private Text attackText;

    private VBox verticalBox;
    private HBox horizontalBox;
    private StackPane characterView;
    private double size;
    private Ability ability;

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
        this.verticalBox = new VBox(5);
        this.horizontalBox = new HBox(5);
        horizontalBox.getChildren().addAll(attackText, healthText);
        verticalBox.getChildren().addAll(imageView, horizontalBox);
        this.characterView = new StackPane();
        characterView.getChildren().addAll(verticalBox);
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

    public void triggerAbility() {
        ability.triggerAbility(this);
    }

    public void setHealth(int health) {
        if (health<= 0){
            this.health = 0;
        } else {
            this.health = health;}
        this.healthText.setText(String.valueOf(this.health));
    }

    public void takeDamage(int damage) {
        Timeline timeline = new Timeline();
        KeyFrame takeDamage = new KeyFrame (Duration.millis(500), e -> {
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
