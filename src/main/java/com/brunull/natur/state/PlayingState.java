package com.brunull.natur.state;

import com.brunull.natur.actor.Actor;
import com.brunull.natur.actor.PlayerActor;

import java.awt.*;
import java.util.ArrayList;

public class PlayingState extends GameState {

    private ArrayList<Actor> actors;
    private PlayerActor player;

    public PlayingState(GameStateManager gameStateManager) {
        super(gameStateManager);
    }

    @Override
    public void enter() {
        actors = new ArrayList<>();
        player = new PlayerActor();
    }

    @Override
    public void exit() {

    }

    @Override
    public void update() {
        for (Actor actor : actors) {
            actor.update();
        }

        player.update();
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.YELLOW);
        g.drawString("This is the playing state.", 5, 15);
        g.drawString("Player X: " + player.getX(), 5, 35);
        g.drawString("Player Y: " + player.getY(), 5, 55);
    }
}