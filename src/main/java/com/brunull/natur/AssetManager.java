package com.brunull.natur;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class AssetManager {
    public BufferedImage loadImage(String name) throws IOException {
        BufferedImage image = ImageIO.read(AssetManager.class.getResource(name));
        return image;
    }
}