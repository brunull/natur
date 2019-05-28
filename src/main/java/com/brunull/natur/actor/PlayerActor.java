package com.brunull.natur.actor;

import java.awt.event.KeyEvent;
import java.io.IOException;

import com.brunull.natur.AssetManager;
import com.brunull.natur.Game;
import com.brunull.natur.graphics.Sprite;
import com.brunull.natur.input.Keyboard;
import com.brunull.natur.state.PlayingState;

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
    	if (Keyboard.isKeyDown(KeyEvent.VK_A) || Keyboard.isKeyDown(KeyEvent.VK_LEFT)) {
    		move(-1 * speed, 0);
    	}
    	
    	if (Keyboard.isKeyDown(KeyEvent.VK_D) || Keyboard.isKeyDown(KeyEvent.VK_RIGHT)) {
    		move(1 * speed, 0);
    	}
    	
    	if (x < 0) {
    		setX(0);
    	}
    	
    	if (x + sprite.getImage().getWidth(null) > game.getWidth()) {
    		setX(game.getWidth() - sprite.getImage().getWidth(null));
    	}
    	
    	if (Keyboard.isKeyDown(KeyEvent.VK_SPACE)) {
    		PlayingState ps = (PlayingState)game.getGameStateManager().getCurrentState();
    		try {
    			Actor projectileActor = new ProjectileActor(AssetManager.loadSprite("/beam1.png"));
    			projectileActor.setX(getX() + (this.getBounds().width / 2));
    			projectileActor.setY(getY() + (this.getBounds().height / 2));
    			
				ps.spawnActor(projectileActor);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }
}