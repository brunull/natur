package com.brunull.natur.graphics;

import java.awt.*;

public class Sprite {

    protected Image image;
    
    protected int x;
    protected int y;

    public Sprite(Image image) {
        this.image = image;
    }

    public void move(int dx, int dy) {
        setX(x + dx);
        setY(y + dy);
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}