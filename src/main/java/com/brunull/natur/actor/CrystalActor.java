package com.brunull.natur.actor;

import java.io.IOException;

import com.brunull.natur.AssetManager;
import com.brunull.natur.Game;
import com.brunull.natur.graphics.Sprite;
import com.brunull.natur.state.PlayingState;

public class CrystalActor extends Actor {

	private Game game;
	
	public CrystalActor(Game game) throws IOException {
		this(game, AssetManager.loadSprite("/crystal.png"));
	}
	
	public CrystalActor(Game game, Sprite sprite) {
		super(sprite);
		this.game = game;
	}

	@Override
	public void update() {
		move(-3, 0);
		
		PlayingState ps = (PlayingState)game.getGameStateManager().getCurrentState();
		
		if (x < 0) {
			ps.destroyActor(this);
			ps.addScore(-3);
		}
		
		checkCollisions(ps);	
	}

	private void checkCollisions(PlayingState ps) {
		for (Actor a : ps.getActors()) {
			if (this == a) {
				continue;
			}
			
			if (a.getBounds().intersects(getBounds())) {				
				if (a instanceof PlayerActor) {
					PlayerActor p = (PlayerActor)a;
					
					if (p.getHealth() < 100) {
						p.damage(-10);
					}
					
					ps.destroyActor(this);
				}
			}
		}
	}
}