package com.brunull.natur.graphics;

import java.awt.*;

public class Sprite {

    protected Image image;

    public Sprite(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}