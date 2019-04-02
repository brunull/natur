package com.brunull.natur.state;

import com.brunull.natur.actor.Actor;

import java.awt.*;
import java.util.ArrayList;

public class PlayingState extends GameState {

    private ArrayList<Actor> actors;

    public PlayingState(GameStateManager gameStateManager) {
        super(gameStateManager);
    }

    @Override
    public void enter() {
        actors = new ArrayList<>();
    }

    @Override
    public void exit() {

    }

    @Override
    public void update() {
        for (Actor actor : actors) {
            actor.update();
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.YELLOW);
        g.drawString("This is the playing state.", 5, 15);
    }
}