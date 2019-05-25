package com.brunull.natur.actor;

import java.awt.event.KeyEvent;

import com.brunull.natur.graphics.Sprite;
import com.brunull.natur.input.Keyboard;

public class PlayerActor extends Actor {

	private int speed;
	
    public PlayerActor(Sprite sprite) {
		super(sprite);
		speed = 3;
	}
	
    @Override
    public void update() {
    	if (Keyboard.isKeyDown(KeyEvent.VK_W)) {
    		move(0, -1 * speed);
    	}
    	
    	if (Keyboard.isKeyDown(KeyEvent.VK_S)) {
    		move(0, 1 * speed);
    	}
    	
    	if (Keyboard.isKeyDown(KeyEvent.VK_D)) {
    		move(1 * speed, 0);
    	}
    	
    	if (Keyboard.isKeyDown(KeyEvent.VK_A)) {
    		move(-1 * speed, 0);
    	}
    }
}