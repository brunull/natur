package com.brunull.natur.actor;

import com.brunull.natur.input.Keyboard;

import java.awt.event.KeyEvent;

public class InputComponent {

    private Actor parentActor;

    public InputComponent(Actor parentActor) {
        this.parentActor = parentActor;
    }

    public void update() {
        if (Keyboard.isKeyDown(KeyEvent.VK_W)) {
            parentActor.move(1, 1);
        }
        else if (Keyboard.isKeyDown(KeyEvent.VK_S)) {
            parentActor.move(-1, -1);
        }
    }
}