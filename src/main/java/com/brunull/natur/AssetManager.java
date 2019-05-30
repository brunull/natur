package com.brunull.natur;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.brunull.natur.graphics.Sprite;

public class AssetManager {
	
	private static HashMap<String, Sprite> spriteCache = new HashMap<String, Sprite>();
	
    public static BufferedImage loadImage(String name) throws IOException {
        BufferedImage image = ImageIO.read(AssetManager.class.getResource(name));
        return image;
    }
    
    public static Sprite loadSprite(String name) throws IOException {
    	
    	if (spriteCache.containsKey(name)) {
    		return spriteCache.get(name);
    	}
    	
    	BufferedImage image = loadImage(name);
    	
    	Sprite sprite = new Sprite(image);
    	spriteCache.put(name, sprite);
    	
    	return sprite;
    }
    
    public static Font loadFont(String name) throws IOException, FontFormatException {
    	return loadFont(name, 16.0f);
    }
    
    public static Font loadFont(String name, float size) throws IOException, FontFormatException {
    	InputStream is = AssetManager.class.getResourceAsStream(name);
    	Font original = Font.createFont(Font.TRUETYPE_FONT, is);
    	return original.deriveFont(Font.PLAIN, size);
    }
}