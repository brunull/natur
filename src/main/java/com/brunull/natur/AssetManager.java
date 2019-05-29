package com.brunull.natur;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.brunull.natur.graphics.Sprite;

public class AssetManager {
    public static BufferedImage loadImage(String name) throws IOException {
        BufferedImage image = ImageIO.read(AssetManager.class.getResource(name));
        return image;
    }
    
    public static Sprite loadSprite(String name) throws IOException {
    	BufferedImage image = loadImage(name);
    	return new Sprite(image);
    }
    
    public static Font loadFont(String name) throws IOException, FontFormatException {
    	InputStream is = AssetManager.class.getResourceAsStream(name);
    	Font original = Font.createFont(Font.TRUETYPE_FONT, is);
    	return original.deriveFont(Font.PLAIN, 16f);
    }
}