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
	private Sprite background2;

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
			background2 = AssetManager.loadSprite("/01bg1.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		background.setX(0);
		background.setY(0);
		
		background2.setX(0);
		background2.setY(-background.getImage().getHeight(null));
		
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
        
        background.move(0, 1);
        background2.move(0, 1);
        
		if (background.getY() > game.getHeight()) {
			background.setY(background2.getY() + background2.getImage().getHeight(null));
		}
		
		if (background2.getY() > game.getHeight()) {
			background2.setY(-background2.getImage().getHeight(null));
		}
    }

    @Override
    public void render(Graphics2D g) {
    	
    	g.drawImage(background.getImage(), background.getX(), background.getY(), null);
    	g.drawImage(background2.getImage(), background2.getX(), background2.getY(), null);
    	g.drawImage(background2.getImage(), background2.getX(), background2.getY() - background2.getImage().getHeight(null), null);
    	
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