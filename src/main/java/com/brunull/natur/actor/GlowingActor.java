package com.brunull.natur.actor;

import java.awt.*;

public abstract class GlowingActor extends Actor {

    protected Rectangle shape;
    protected Color glowColor;

    public GlowingActor(Rectangle shape, Color glowColor) {
        this.shape = shape;
        this.glowColor = glowColor;
    }

    @Override
    public abstract void update();

    public int getWidth() {
        return shape.width;
    }

    public int getHeight() {
        return shape.height;
    }

    public Rectangle getShape() {
        return shape;
    }

    public void setShape(Rectangle shape) {
        this.shape = shape;
    }

    public Color getGlowColor() {
        return glowColor;
    }

    public void setGlowColor(Color glowColor) {
        this.glowColor = glowColor;
    }
}