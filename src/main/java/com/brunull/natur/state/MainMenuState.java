package com.brunull.natur.state;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.brunull.natur.input.Keyboard;

public class MainMenuState extends GameState {

	public MainMenuState(GameStateManager gameStateManager) {
		super(gameStateManager);
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
			
			System.out.println("AAA");
		}
	}

	@Override
	public void render(Graphics2D g) {
        g.setColor(Color.YELLOW);
        g.drawString("This is the main menu state.", 5, 15);
	}

}