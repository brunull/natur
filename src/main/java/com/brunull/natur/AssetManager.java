package com.brunull.natur;

import javax.imageio.ImageIO;

import com.brunull.natur.graphics.Sprite;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class AssetManager {
    public static BufferedImage loadImage(String name) throws IOException {
        BufferedImage image = ImageIO.read(AssetManager.class.getResource(name));
        return image;
    }
    
    public static Sprite loadSprite(String name) throws IOException {
    	BufferedImage image = loadImage(name);
    	return new Sprite(image);
    }
}