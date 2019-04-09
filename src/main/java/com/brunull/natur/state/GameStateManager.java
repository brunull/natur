package com.brunull.natur.state;

import com.brunull.natur.Game;

import java.util.Stack;

public class GameStateManager {

    private Stack<GameState> gameStates;
    private Game game;

    public GameStateManager(Game game) {
        gameStates = new Stack<>();
    }

    public void pushState(GameState state) {
        gameStates.push(state);
        state.enter();
    }

    public void popState() {
        getCurrentState().exit();
        gameStates.pop();
    }

    public GameState getCurrentState() {
        return gameStates.peek();
    }

    public Game getGame() {
        return game;
    }
}