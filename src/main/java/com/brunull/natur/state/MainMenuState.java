package com.brunull.natur.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;

import com.brunull.natur.AssetManager;
import com.brunull.natur.audio.AudioPlayer;
import com.brunull.natur.graphics.Sprite;
import com.brunull.natur.input.Keyboard;
import com.brunull.natur.math.Vector2;
import com.brunull.natur.ui.TextElement;

public class MainMenuState extends GameState {

	private TextElement infoText;
	private TextElement creditsText;
	
	private TextElement pressToPlayText;
	
	private Sprite logoSprite;
	
	private Sprite background;
	private Sprite background2;
	
	private Font font;
	private Font font2;
	
	private int blinkTicks;
	
	public MainMenuState(GameStateManager gameStateManager) {
		super(gameStateManager);
		
        try {
			font = AssetManager.loadFont("/VCR_OSD_MONO_1.001.ttf");
			font2 = font.deriveFont(22.0f);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		}
		
		game = gameStateManager.getGame();
		
		pressToPlayText = new TextElement("> Pressione ENTER para jogar <",
				font2,
				Color.YELLOW,
				new Vector2<Integer>(0, 0)
		);
		
		infoText = new TextElement("Natur ver. 1.0 - APS 3º Semestre CC",
				font,
				Color.WHITE,
				new Vector2<Integer>(0, 0)
		);
		
		creditsText = new TextElement("Desenvolvido por Bruno, João e Honorran",
				font,
				Color.WHITE,
				new Vector2<Integer>(0, 0)
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
		
		//int paddingY = game.getWindow().getInsets().top;
		
		//String r = copyText.getFont().toString();
		//copyText.setPosition((int)(game.getWidth() - (r.getX() + r.getWidth())), game.getHeight() - 15);
		infoText.setPosition(game.getWidth() - (infoText.getPosition().getX() + infoText.getBounds((Graphics2D)game.getBackBuffer()).width + 15), (game.getHeight()) - (infoText.getBounds((Graphics2D)game.getBackBuffer()).height  * 2) - 20);
		creditsText.setPosition(game.getWidth() - (creditsText.getPosition().getX() + creditsText.getBounds((Graphics2D)game.getBackBuffer()).width + 15), (game.getHeight()) - creditsText.getBounds((Graphics2D)game.getBackBuffer()).height - 15);
		
		pressToPlayText.setPosition((game.getWidth() / 2) - pressToPlayText.getBounds((Graphics2D)game.getBackBuffer()).width / 2, (game.getHeight() / 2) - pressToPlayText.getBounds((Graphics2D)game.getBackBuffer()).height / 2);

		blinkTicks = 30;
		
		AudioPlayer.playSound("/maintheme.wav", true);
	}

	@Override
	public void exit() {
		AudioPlayer.stop();
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
		
		infoText.drawShadowed(g);
		creditsText.drawShadowed(g);
        //infoText.drawBounds(g);
		
		if (blinkTicks >= 30) {
			pressToPlayText.drawShadowed(g);
			
			if (blinkTicks >= 60) {
				blinkTicks = 0;
			}
		}
		
		blinkTicks++;
        
        g.drawImage(logoSprite.getImage(), (game.getWidth() / 2) - (logoSprite.getImage().getWidth(null) / 2), 50, null);
	}
}