package com.brunull.natur.graphics;

import java.awt.*;

public class Renderer {
    public void drawSprite(Graphics2D g, Sprite sprite) {
        g.drawImage(sprite.getImage(), 0, 0, null);
    }
}