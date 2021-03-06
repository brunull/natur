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

	private boolean isFirstTry;
	
	private int spawnTimer;
	private int crystalSpawnTimer;
	
	private int score;
	private int bestScore;
	
	private ArrayList<Actor> actorKillList;
	
    private ArrayList<Actor> actors;
    private PlayerActor player;
    
	private Sprite clouds1;
	private Sprite clouds2;
	private Sprite junk;
	private Sprite city;
	
	private TextElement gameplayInfoText;
	
	private TextElement playerHealthText;
	private TextElement scoreText;
	private TextElement bestScoreText;
	
	private Font font;
	private Font font2;
	
	private int secondsElapsedSinceLastDisplay;
	
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
        
        gameplayInfoText = new TextElement("A CIDADE ESTA SUJA",
				font2,
				Color.YELLOW,
				new Vector2<Integer>(0, 0)
		);
        
        playerHealthText = new TextElement("SAUDE: 0%",
				font,
				Color.YELLOW,
				new Vector2<Integer>(5, 5)
		);
        
        scoreText = new TextElement("PONTOS: 0",
				font,
				Color.YELLOW,
				new Vector2<Integer>(5, playerHealthText.getBounds((Graphics2D)game.getBackBuffer()).height + 10)
		);
        
        bestScoreText = new TextElement("RECORDE: 0",
				font,
				Color.YELLOW,
				new Vector2<Integer>(5, (scoreText.getPosition().getY() + scoreText.getBounds((Graphics2D)game.getBackBuffer()).height) + 5)
		);
        
        bestScore = 0;
        
        shouldDisplayGameplayInfo = true;
        isFirstTry = true;
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
		
		secondsElapsedSinceLastDisplay = 0;
		
		if (isFirstTry) {
			AudioPlayer.playSound("/01.wav", true);
		}
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
		
		if (secondsElapsedSinceLastDisplay >= 180) {
			gameplayInfoText.setText("DESTRUA O LIXO PARA AJUDA-LA!");
			gameplayInfoText.setPosition((game.getWidth() / 2) - gameplayInfoText.getBounds((Graphics2D)game.getBackBuffer()).width / 2, (game.getHeight() / 2) - gameplayInfoText.getBounds((Graphics2D)game.getBackBuffer()).height / 2);
		
	        if (secondsElapsedSinceLastDisplay >= 360) {
	        	shouldDisplayGameplayInfo = false;
	        	secondsElapsedSinceLastDisplay = 0;
	        }
		}
		
		secondsElapsedSinceLastDisplay++;
		
		crystalSpawnTimer++;
		spawnTimer++;
		
		if (actorKillList.size() > 0) {
			for (Actor a : actorKillList) {
				actors.remove(a);
			}
			
			actorKillList.clear();
		}
		
		playerHealthText.setText("SAUDE: " + player.getHealth() + "%");
		scoreText.setText("PONTOS: " + score);
		
		if (player.getHealth() <= 0) {		   	
			
			if (isFirstTry) {
				isFirstTry = false;
			}
			
			bestScore = score > bestScore ? score : bestScore;
			bestScoreText.setText("RECORDE: " + bestScore);
		   	
			enter();
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
        
        g.setColor(Color.YELLOW);
        g.setFont(font);
        
        playerHealthText.drawShadowed(g);
        scoreText.drawShadowed(g);
        bestScoreText.drawShadowed(g);
        
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
    
    public ArrayList<Actor> getActors() {
    	return actors;
    }
    
    public PlayerActor getPlayerActor() {
    	return player;
    }
}