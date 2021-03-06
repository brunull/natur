package com.brunull.natur.actor;

import java.io.IOException;

import com.brunull.natur.AssetManager;
import com.brunull.natur.Game;
import com.brunull.natur.audio.AudioPlayer;
import com.brunull.natur.graphics.Sprite;
import com.brunull.natur.state.PlayingState;

public class PawnEnemyActor extends Actor {

	private Game game;
	
	public PawnEnemyActor(Game game) throws IOException {
		this(game, AssetManager.loadSprite("/can.png"));
	}
	
	public PawnEnemyActor(Game game, Sprite sprite) {
		super(sprite);
		this.game = game;
	}

	@Override
	public void update() {
		move(-6, 0);
		
		PlayingState ps = (PlayingState)game.getGameStateManager().getCurrentState();
		
		if (x < 0) {
			ps.destroyActor(this);
			ps.addScore(-3);
			ps.getPlayerActor().damage(5);
			
			AudioPlayer.playSound("/hit2.wav", false);
		}
		
		checkCollisions(ps);	
	}
	
	private void checkCollisions(PlayingState ps) {
		for (Actor a : ps.getActors()) {
			if (this == a) {
				continue;
			}
			
			if (a.getBounds().intersects(getBounds())) {
				if (a instanceof ProjectileActor) {
					
					AudioPlayer.playSound("/hit1.wav", false);
					
					ps.destroyActor(a);
					ps.destroyActor(this);
					ps.addScore(2);
				}
				
				if (a instanceof PlayerActor) {
					PlayerActor p = (PlayerActor)a;
					p.damage(15);
					
					AudioPlayer.playSound("/hit2.wav", false);
					
					ps.destroyActor(this);
				}
			}
		}
	}
	
}