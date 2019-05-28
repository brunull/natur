package com.brunull.natur.actor;

import com.brunull.natur.graphics.Sprite;

public class ProjectileActor extends Actor {

	public ProjectileActor(Sprite sprite) {
		super(sprite);
	}

	@Override
	public void update() {
		this.setY(getY() - 5);
	}
}