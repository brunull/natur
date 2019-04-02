package com.brunull.natur.actor;

public class PlayerActor extends Actor {

    private InputComponent inputComponent;

    public PlayerActor() {
        inputComponent = new InputComponent(this);
    }

    @Override
    public void update() {
        inputComponent.update();
    }
}