package com.brunull.natur.state;

import java.util.Stack;

public class GameStateManager {

    private Stack<GameState> gameStates;

    public GameStateManager() {
        gameStates = new Stack<>();
    }

    public void pushState(GameState state) {
        gameStates.push(state);
    }

    public void popState() {
        gameStates.pop();
    }

    public GameState peekState() {
        return gameStates.peek();
    }
}