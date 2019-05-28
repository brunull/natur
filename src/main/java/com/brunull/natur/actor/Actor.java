package com.brunull.natur.actor;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.brunull.natur.graphics.Sprite;

public abstract class Actor {

	protected Sprite sprite;
	
	protected Rectangle bounds;
	
    protected int x;
    protected int y;

    public Actor(Sprite sprite) {
    	this.sprite = sprite;
    }
    
    public abstract void update();
    
    public void draw(Graphics2D g) {
    	g.drawImage(sprite.getImage(), x, y, null);
    	
    	drawBoundsDebug(g);
    }
    
    public void drawBoundsDebug(Graphics2D g) {
    	g.setColor(Color.RED);
    	Rectangle bounds = getBounds();
    	g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
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
    
    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
    
    public Rectangle getBounds() {
    	return new Rectangle(x, y, sprite.getImage().getWidth(null), sprite.getImage().getHeight(null));
    }
}