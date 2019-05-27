package com.brunull.natur.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import com.brunull.natur.AssetManager;
import com.brunull.natur.Game;
import com.brunull.natur.audio.AudioPlayer;
import com.brunull.natur.graphics.Sprite;
import com.brunull.natur.input.Keyboard;
import com.brunull.natur.math.Vector2;
import com.brunull.natur.ui.TextElement;

public class MainMenuState extends GameState {

	private TextElement copyText;
	private Sprite logoSprite;
	
	private Sprite background;
	private Sprite background2;
	
	public MainMenuState(GameStateManager gameStateManager) {
		super(gameStateManager);
		
		game = gameStateManager.getGame();
		
		copyText = new TextElement("Natur v0.1",
				game.getBackBuffer().getFont(),
				Color.WHITE,
				new Vector2<Integer>(5, 15)
		);
	}

	@Override
	public void enter() {
		try {
			logoSprite = AssetManager.loadSprite("/logo.png");
			background = AssetManager.loadSprite("/mainmenubg.png");
			background2 = AssetManager.loadSprite("/mainmenubg2.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		background.setX(0);
		background.setY(0);
		
		background2.setX(400);
		background2.setY(0);
		
		int paddingY = game.getWindow().getInsets().top;
		
		//String r = copyText.getFont().toString();
		//copyText.setPosition((int)(game.getWidth() - (r.getX() + r.getWidth())), game.getHeight() - 15);
		copyText.setPosition(game.getWidth() - (copyText.getPosition().getX() + copyText.getBounds((Graphics2D)game.getBackBuffer()).width), (game.getHeight() - paddingY) - copyText.getBounds((Graphics2D)game.getBackBuffer()).height);
		
		//AudioPlayer.playSound("/maintheme.wav");
	}

	@Override
	public void exit() {
		
	}

	@Override
	public void update() {
		if (Keyboard.isKeyDown(KeyEvent.VK_ENTER)) {
			gameStateManager.pushState(new PlayingState(gameStateManager));
		}
		
		if (Keyboard.isKeyDown(KeyEvent.VK_ESCAPE)) {
			gameStateManager.popState();
		}
		
		background.move(-1, 0);
		background2.move(-1, 0);
		
		if (background.getX() < -400) {
			background.setX(game.getWidth());
		}
		
		if (background2.getX() < -400) {
			background2.setX(game.getWidth());
		}
	}

	@Override
	public void render(Graphics2D g) {   
		clear(new Color(0, 142, 201));
		
		g.drawImage(background.getImage(), background.getX(), background.getY(), null);
		g.drawImage(background2.getImage(), background2.getX(), background2.getY(), null);
		
        copyText.draw(g);
        copyText.drawBounds(g);
        
        g.drawImage(logoSprite.getImage(), (game.getWidth() / 2) - (logoSprite.getImage().getWidth(null) / 2), 50, null);
	}

}