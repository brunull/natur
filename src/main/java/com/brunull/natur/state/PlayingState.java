package com.brunull.natur.state;

import com.brunull.natur.AssetManager;
import com.brunull.natur.actor.Actor;
import com.brunull.natur.actor.PlayerActor;
import com.brunull.natur.audio.AudioPlayer;
import com.brunull.natur.graphics.Sprite;
import com.brunull.natur.input.Keyboard;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

public class PlayingState extends GameState {

	private int score;
	
    private ArrayList<Actor> actors;
    private PlayerActor player;
    
	private Sprite background;

    public PlayingState(GameStateManager gameStateManager) {
        super(gameStateManager);
        
        score = 0;
    }

    @Override
    public void enter() {
        actors = new ArrayList<>();
        try {
			player = new PlayerActor(game, AssetManager.loadSprite("/player.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        player.setX((game.getWidth() / 2) - player.getSprite().getImage().getWidth(null) / 2);
        player.setY(game.getHeight() - (player.getSprite().getImage().getHeight(null) + 45));
        
		try {
			background = AssetManager.loadSprite("/01bg1.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        AudioPlayer.playSound("/01.wav");
    }

    @Override
    public void exit() {
    	gameStateManager.popState();
    }

    @Override
    public void update() {
        for (Actor actor : actors) {
            actor.update();
        }

        player.update();
        
        if (Keyboard.isKeyDown(KeyEvent.VK_ESCAPE)) {
        	exit();
        }
    }

    @Override
    public void render(Graphics2D g) {
    	
    	g.drawImage(background.getImage(), background.getX(), background.getY(), null);
    	
    	for (Actor actor : actors) {
    		actor.draw(g);
    	}
    	
        player.draw(g);
        
        g.setColor(Color.WHITE);
        g.drawString("This is the playing state.", 5, 15);
        g.drawString("Player X: " + player.getX(), 5, 35);
        g.drawString("Player Y: " + player.getY(), 5, 55);
    }
    
    public void spawnActor(Actor actor) {
    	this.actors.add(actor);
    }
}