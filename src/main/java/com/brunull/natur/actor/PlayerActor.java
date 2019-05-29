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
	private int turretHeat;
	
    public PlayerActor(Game game, Sprite sprite) {
		super(sprite);
		this.game = game;
		speed = 5;
		turretHeat = 0;
	}
	
    @Override
    public void update() {
    	if (Keyboard.isKeyDown(KeyEvent.VK_W) || Keyboard.isKeyDown(KeyEvent.VK_UP)) {
    		move(0, -1 * speed);
    	}
    	
    	if (Keyboard.isKeyDown(KeyEvent.VK_S) || Keyboard.isKeyDown(KeyEvent.VK_DOWN)) {
    		move(0, 1 * speed);
    	}
    	
    	if (y < 0) {
    		setY(0);
    	}
    	
    	if (y + sprite.getImage().getHeight(null) > game.getHeight()) {
    		setY(game.getHeight() - sprite.getImage().getHeight(null));
    	}
    	
    	/* if (Keyboard.isKeyDown(KeyEvent.VK_A) || Keyboard.isKeyDown(KeyEvent.VK_LEFT)) {
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
    	} */
    	
    	if (Keyboard.isKeyDown(KeyEvent.VK_SPACE)) {
    		
    		if (turretHeat != 0) {
    			turretHeat--;
    			return;
    		}
    		
    		PlayingState ps = (PlayingState)game.getGameStateManager().getCurrentState();
    		try {
    			Actor projectileActor = new ProjectileActor(AssetManager.loadSprite("/beam1.png"));
    			projectileActor.setX(getX() + (this.getBounds().width / 2));
    			projectileActor.setY(getY() + (this.getBounds().height / 2));
    			
				ps.spawnActor(projectileActor);
			} catch (IOException e) {
				e.printStackTrace();
			}
    		
    		turretHeat = 5;
    	}
    }
}