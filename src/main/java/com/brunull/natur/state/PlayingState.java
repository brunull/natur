package com.brunull.natur.state;

import com.brunull.natur.AssetManager;
import com.brunull.natur.actor.Actor;
import com.brunull.natur.actor.CrystalActor;
import com.brunull.natur.actor.PawnEnemyActor;
import com.brunull.natur.actor.PlayerActor;
import com.brunull.natur.audio.AudioPlayer;
import com.brunull.natur.graphics.Sprite;
import com.brunull.natur.input.Keyboard;
import com.brunull.natur.math.Vector2;
import com.brunull.natur.ui.TextElement;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

public class PlayingState extends GameState {

	private int spawnTimer;
	private int crystalSpawnTimer;
	
	private int score;
	
	private ArrayList<Actor> actorKillList;
	
    private ArrayList<Actor> actors;
    private PlayerActor player;
    
	private Sprite clouds1;
	private Sprite clouds2;
	private Sprite junk;
	private Sprite city;
	
	private TextElement gameplayInfoText;
	
	private Font font;
	private Font font2;
	
	private int levelStartTextTimer;
	
	private boolean shouldDisplayGameplayInfo;

    public PlayingState(GameStateManager gameStateManager) {
        super(gameStateManager);
        
        actorKillList = new ArrayList<Actor>();
        
        try {
			font = AssetManager.loadFont("/VCR_OSD_MONO_1.001.ttf");
			font2 = font.deriveFont(22.0f);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		}
        
        gameplayInfoText = new TextElement("A CIDADE ESTÁ SUJA",
				font2,
				Color.YELLOW,
				new Vector2<Integer>(0, 0)
		);
        
        shouldDisplayGameplayInfo = true;
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
        
        score = 0;
        spawnTimer = 0;
        crystalSpawnTimer = 0;
        
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
		
		gameplayInfoText.setPosition((game.getWidth() / 2) - gameplayInfoText.getBounds((Graphics2D)game.getBackBuffer()).width / 2, (game.getHeight() / 2) - gameplayInfoText.getBounds((Graphics2D)game.getBackBuffer()).height / 2);
		
		levelStartTextTimer = 0;
		
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
		
		if (spawnTimer > 30) {
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
		
		if (crystalSpawnTimer > 400) {
			try {
				CrystalActor ca = new CrystalActor(game);
				
				ca.setX(game.getWidth());
				ca.setY((int)(Math.random() * 500));
				
				spawnActor(ca);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			crystalSpawnTimer = 0;
		}
		
		if (levelStartTextTimer >= 180) {
			gameplayInfoText.setText("DESTRUA O LIXO E SALVE A CIDADE!");
			gameplayInfoText.setPosition((game.getWidth() / 2) - gameplayInfoText.getBounds((Graphics2D)game.getBackBuffer()).width / 2, (game.getHeight() / 2) - gameplayInfoText.getBounds((Graphics2D)game.getBackBuffer()).height / 2);
		
	        if (levelStartTextTimer >= 360) {
	        	shouldDisplayGameplayInfo = false;
	        	levelStartTextTimer = 0;
	        }
		}
		
		levelStartTextTimer++;
		
		crystalSpawnTimer++;
		spawnTimer++;
		
		if (actorKillList.size() > 0) {
			for (Actor a : actorKillList) {
				actors.remove(a);
			}
			
			actorKillList.clear();
		}
		
		//System.out.println("Actor count: " + actors.size());
		
		if (player.getHealth() <= 0) {
		   	AudioPlayer.stop();
			enter();
		}
		
		if (isMilestoneAchieved()) {
			gameplayInfoText.setColor(Color.GREEN);
			gameplayInfoText.setText(score + " PONTOS OBTIDOS!");
			gameplayInfoText.setPosition((game.getWidth() / 2) - gameplayInfoText.getBounds((Graphics2D)game.getBackBuffer()).width / 2, (game.getHeight() / 2) - gameplayInfoText.getBounds((Graphics2D)game.getBackBuffer()).height / 2);
			shouldDisplayGameplayInfo = true;
		}
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
        
        g.setColor(Color.YELLOW);
        g.setFont(font);
        
        g.drawString("This is the playing state.", 5, 15);
        g.drawString("Player X: " + player.getX(), 5, 35);
        g.drawString("Player Y: " + player.getY(), 5, 55);
        
        g.drawString("Score: " + score, 5, 75);
        g.drawString("Health: " + player.getHealth(), 5, 95);
                
        if (shouldDisplayGameplayInfo) {
        	gameplayInfoText.drawShadowed(g);
        }
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
    
    private boolean isMilestoneAchieved() {
    	return (score % 100 == 0) && (score >= 100);
    }
    
    public ArrayList<Actor> getActors() {
    	return actors;
    }
    
    public PlayerActor getPlayerActor() {
    	return player;
    }
}