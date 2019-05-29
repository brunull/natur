package com.brunull.natur.state;

import com.brunull.natur.AssetManager;
import com.brunull.natur.actor.Actor;
import com.brunull.natur.actor.PawnEnemyActor;
import com.brunull.natur.actor.PlayerActor;
import com.brunull.natur.audio.AudioPlayer;
import com.brunull.natur.graphics.Sprite;
import com.brunull.natur.input.Keyboard;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

public class PlayingState extends GameState {

	private int spawnTimer;
	
	private int score;
	
	private ArrayList<Actor> actorKillList;
	
    private ArrayList<Actor> actors;
    private PlayerActor player;
    
	private Sprite clouds1;
	private Sprite clouds2;
	private Sprite junk;
	private Sprite city;

    public PlayingState(GameStateManager gameStateManager) {
        super(gameStateManager);
        
        actorKillList = new ArrayList<Actor>();
        
        score = 0;
        spawnTimer = 0;
    }

    @Override
    public void enter() {
        actors = new ArrayList<>();
        try {
			player = new PlayerActor(game, AssetManager.loadSprite("/player.png"));
			actors.add(player);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        /* player.setX((game.getWidth() / 2) - player.getSprite().getImage().getWidth(null) / 2);
        player.setY(game.getHeight() - (player.getSprite().getImage().getHeight(null) + 45)); */
        
        player.setX(player.getBounds().width + 25);
        player.setY((game.getHeight() / 2) - player.getSprite().getImage().getHeight(null) / 2);
        
		try {
			city = AssetManager.loadSprite("/cityset1.png");
			clouds1 = AssetManager.loadSprite("/cloudset1.png");
			clouds2 = AssetManager.loadSprite("/cloudset2.png");
			junk = AssetManager.loadSprite("/junkset1.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		clouds1.setX(25);
		clouds1.setY(15);
		
		clouds2.setX(65);
		clouds2.setY(35);
        
		city.setX(0);
		city.setY(game.getHeight() - city.getImage().getHeight(null));
		
		junk.setX(0);
		junk.setY(game.getHeight() - junk.getImage().getHeight(null));
		
        AudioPlayer.playSound("/01.wav");
    }

    @Override
    public void exit() {
    	gameStateManager.popState();
    }

    @Override
    public void update() {
        for (Actor actor : actors) {
        	
        	if (actor == player) {
        		continue;
        	}
        	
            actor.update();
        }

        player.update();
        
        if (Keyboard.isKeyDown(KeyEvent.VK_ESCAPE)) {
        	exit();
        }
        
		clouds1.move(-2, 0);
		
		clouds2.move(-1, 0);
		city.move(-2, 0);
        
		junk.move(-3, 0);
		
		if (clouds1.getX() + clouds1.getImage().getWidth(null) < 0) {
			clouds1.setX(game.getWidth());
		}
		
		if (clouds2.getX() + clouds2.getImage().getWidth(null) < 0) {
			clouds2.setX(game.getWidth());
		}
		
		if (city.getX() + city.getImage().getWidth(null) < 0) {
			city.setX((city.getX() + city.getImage().getWidth(null)) * 2);
		}
		
		if (junk.getX() + junk.getImage().getWidth(null) < 0) {
			junk.setX((junk.getX() + junk.getImage().getWidth(null)) * 2);
		}
		
		if (spawnTimer > 25) {
			try {
				PawnEnemyActor pa = new PawnEnemyActor(game);
				
				pa.setX(game.getWidth());
				pa.setY((int)(Math.random() * 500));
				
				spawnActor(pa);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			spawnTimer = 0;
		}
		
		spawnTimer++;
		
		if (actorKillList.size() > 0) {
			for (Actor a : actorKillList) {
				actors.remove(a);
			}
			
			actorKillList.clear();
		}
		
		System.out.println("Actor count: " + actors.size());
    }

    @Override
    public void render(Graphics2D g) {
    	
    	clear(new Color(0, 142, 201));
    
    	g.drawImage(clouds2.getImage(), clouds2.getX(), clouds2.getY(), null);
    	
    	g.drawImage(city.getImage(), city.getX(), city.getY(), null);
    	g.drawImage(city.getImage(), city.getX() + city.getImage().getWidth(null), city.getY(), null);
    	
    	for (Actor actor : actors) {
    		
        	if (actor == player) {
        		continue;
        	}
        	
    		actor.draw(g);
    	}
    	
    	player.draw(g);
    	
    	g.drawImage(junk.getImage(), junk.getX(), junk.getY(), null);
    	g.drawImage(junk.getImage(), junk.getX() + junk.getImage().getWidth(null), junk.getY(), null);
    	
    	g.drawImage(clouds1.getImage(), clouds1.getX(), clouds1.getY(), null);
    	
        // player.draw(g);
        
        g.setColor(Color.WHITE);
        g.drawString("This is the playing state.", 5, 15);
        g.drawString("Player X: " + player.getX(), 5, 35);
        g.drawString("Player Y: " + player.getY(), 5, 55);
        
        g.drawString("Score: " + score, 5, 75);
        g.drawString("Health: " + player.getHealth(), 5, 95);
    }
    
    public void spawnActor(Actor actor) {
    	this.actors.add(actor);
    }
    
    public void destroyActor(Actor actor) {
    	this.actorKillList.add(actor);
    }
    
    public void addScore(int score) {
    	this.score += score;
    }
    
    public ArrayList<Actor> getActors() {
    	return actors;
    }
    
    public PlayerActor getPlayerActor() {
    	return player;
    }
}