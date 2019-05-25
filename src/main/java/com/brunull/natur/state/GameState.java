package com.brunull.natur.state;

import java.awt.*;

import com.brunull.natur.Game;

public abstract class GameState {

    protected GameStateManager gameStateManager;
    protected Game game;

    public GameState(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
        this.game = gameStateManager.getGame();
    }

    public abstract void enter();
    public abstract void exit();
    public abstract void update();
    public abstract void render(Graphics2D g);
    
    protected void clear(Color clearColor) {
    	Graphics g = game.getBackBuffer();
        g.setColor(clearColor);
        g.fillRect(0,0, game.getWidth(), game.getHeight());
    }
}