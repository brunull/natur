package com.brunull.natur.state;

import java.awt.*;

public abstract class GameState {

    protected GameStateManager gameStateManager;

    public GameState(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
    }

    public abstract void enter();
    public abstract void exit();
    public abstract void update();
    public abstract void render(Graphics2D graphics);
}