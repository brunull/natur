package com.brunull.natur.state;

import com.brunull.natur.actor.Actor;
import com.brunull.natur.actor.PlayerActor;

import java.awt.*;
import java.util.ArrayList;

public class PlayingState implements GameState {

    private PlayerActor player;
    private ArrayList<Actor> actors;

    @Override
    public void initialize() {
        player = new PlayerActor(new Rectangle(50, 50), Color.MAGENTA);
        actors = new ArrayList<>();
        actors.add(player);
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
        g.drawString("This is the playing State", 5, 15);
    }
}