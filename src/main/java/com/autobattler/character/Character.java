package com.autobattler.character;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Character {
    private int health;
    private int attackPower;
    private String name;
    private Image image;
    private ImageView imageView;

    public Character(int health, int attackPower, String name, Image image) {
        this.health = health;
        this.attackPower = attackPower;
        this.name = name;
        this.image = image;
        this.imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.preserveRatioProperty().set(true);
    }

    public int getHealth() {return health;}
    public int getAttackPower() {return attackPower;}
    public String getName() {return name;}
    public Image getImage() {return image;}
    public ImageView getImageView() {return imageView;}
}
