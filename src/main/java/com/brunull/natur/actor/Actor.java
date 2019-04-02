package com.brunull.natur.actor;

public abstract class Actor {

    protected int x;
    protected int y;

    public abstract void update();

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
}