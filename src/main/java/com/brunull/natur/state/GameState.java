package com.brunull.natur.state;

import java.awt.*;

public interface GameState {
    void initialize();
    void update();
    void render(Graphics2D graphics);
}