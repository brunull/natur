package com.brunull.natur.actor;

import java.awt.*;

public class PlayerActor extends GlowingActor {

    private int y;

    public PlayerActor(Rectangle shape, Color color) {

        super(shape, color);

        y = 0;
    }

    @Override
    public void update() {

        int dy = (int)(Math.sin(Math.toRadians(y)) * 1.1);
        y++;

        System.out.println(dy);

        if ( y >= 360) {
            y = 0;
        }

        shape.grow(dy, 0);
    }
}