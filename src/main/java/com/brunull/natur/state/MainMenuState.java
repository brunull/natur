package com.brunull.natur.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.brunull.natur.input.Keyboard;
import com.brunull.natur.math.Vector2;
import com.brunull.natur.ui.TextElement;

public class MainMenuState extends GameState {

	private TextElement simpleText;
	
	public MainMenuState(GameStateManager gameStateManager) {
		super(gameStateManager);
		
		simpleText = new TextElement("This is the main menu state.",
				Font.getFont("Arial"),
				Color.YELLOW,
				new Vector2<Integer>(5, 15)
		);
	}

	@Override
	public void enter() {
		
	}

	@Override
	public void exit() {
		
	}

	@Override
	public void update() {
		if (Keyboard.isKeyDown(KeyEvent.VK_ENTER)) {
			gameStateManager.pushState(new PlayingState(gameStateManager));
		}
	}

	@Override
	public void render(Graphics2D g) {        
        simpleText.draw(g);
	}

}