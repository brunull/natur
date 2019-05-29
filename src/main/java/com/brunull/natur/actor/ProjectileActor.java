package com.brunull.natur.actor;

import com.brunull.natur.Game;
import com.brunull.natur.graphics.Sprite;
import com.brunull.natur.state.PlayingState;

public class ProjectileActor extends Actor {

	private Game game;
	
	public ProjectileActor(Game game, Sprite sprite) {
		super(sprite);
		
		this.game = game;
	}

	@Override
	public void update() {
		this.setX(getX() + 7);
		
		if (x > game.getWidth()) {
			PlayingState ps = (PlayingState)game.getGameStateManager().getCurrentState();
			ps.destroyActor(this);
		}
	}
}