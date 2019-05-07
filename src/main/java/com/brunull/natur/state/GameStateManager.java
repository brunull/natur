package com.brunull.natur.state;

import com.brunull.natur.Game;

import java.util.EmptyStackException;
import java.util.Stack;

public class GameStateManager {

    private Stack<GameState> gameStates;
    private Game game;

    public GameStateManager(Game game) {
        gameStates = new Stack<>();
        this.game = game;
    }

    public void pushState(GameState state) {
    	if (getCurrentState() != null) {
    		getCurrentState().exit();
    	}
    	
        gameStates.push(state);
        state.enter();
    }

    public void popState() {
        gameStates.pop();
    }

    public GameState getCurrentState() {
    	try {
    		return gameStates.peek();
    	} catch (EmptyStackException e) {
    		return null;
    	}
    }

    public Game getGame() {
        return game;
    }
}