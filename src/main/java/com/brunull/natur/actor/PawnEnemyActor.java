package com.brunull.natur.actor;

import java.io.IOException;

import com.brunull.natur.AssetManager;
import com.brunull.natur.graphics.Sprite;

public class PawnEnemyActor extends Actor {

	public PawnEnemyActor() throws IOException {
		this(AssetManager.loadSprite("/can.png"));
	}
	
	public PawnEnemyActor(Sprite sprite) {
		super(sprite);
	}

	@Override
	public void update() {
		move(-6, 0);
	}
	
}