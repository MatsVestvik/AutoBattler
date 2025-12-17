package com.autobattler.util;

import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.scene.image.Image;

public class makeImage {
    
    public static Image loadImage(String path) {
        try {
            Image image = new Image(path);
            return image;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
