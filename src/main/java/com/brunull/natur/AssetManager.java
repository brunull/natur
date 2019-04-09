package com.brunull.natur;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class AssetManager {
    public BufferedImage loadSpriteSheet(String name) throws IOException {
        BufferedImage spriteSheet = ImageIO.read(AssetManager.class.getResource(name));
        return spriteSheet;
    }
}