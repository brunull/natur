package com.brunull.natur.actor;

import com.brunull.natur.graphics.Sprite;

public class PlayerActor extends Actor {

    private Sprite sprite;

    public PlayerActor() {

    }

    public PlayerActor(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void update() {

    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}