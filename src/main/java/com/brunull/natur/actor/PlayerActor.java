package com.brunull.natur.actor;

import java.awt.event.KeyEvent;

import com.brunull.natur.Game;
import com.brunull.natur.graphics.Sprite;
import com.brunull.natur.input.Keyboard;

public class PlayerActor extends Actor {

	private Game game;
	
	private int speed;
	
    public PlayerActor(Game game, Sprite sprite) {
		super(sprite);
		this.game = game;
		speed = 5;
	}
	
    @Override
    public void update() {    	
    	if (Keyboard.isKeyDown(KeyEvent.VK_D)) {
    		move(1 * speed, 0);
    	}
    	
    	if (Keyboard.isKeyDown(KeyEvent.VK_A)) {
    		move(-1 * speed, 0);
    	}
    	
    	if (x < 0) {
    		setX(0);
    	}
    	
    	if (x + sprite.getImage().getWidth(null) > game.getWidth()) {
    		setX(game.getWidth() - sprite.getImage().getWidth(null));
    	}
    }
}