package com.brunull.natur.state;

import java.util.Stack;

public class GameStateManager {

    private Stack<GameState> gameStates;

    public GameStateManager() {
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
}