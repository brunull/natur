package com.brunull.natur.actor;

import java.awt.event.KeyEvent;

import com.brunull.natur.graphics.Sprite;
import com.brunull.natur.input.Keyboard;

public class PlayerActor extends Actor {

    private Sprite sprite;

    public PlayerActor() {

    }

    public PlayerActor(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void update() {
    	if (Keyboard.isKeyDown(KeyEvent.VK_UP)) {
    		move(1, 0);
    	}
    	
    	if (Keyboard.isKeyDown(KeyEvent.VK_DOWN)) {
    		move(-1, 0);
    	}
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}