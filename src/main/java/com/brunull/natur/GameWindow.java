package com.brunull.natur;

import com.brunull.natur.input.Keyboard;
import com.brunull.natur.state.GameStateManager;
import com.brunull.natur.state.PlayingState;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameWindow extends JFrame implements Runnable {

    private GameStateManager gameStateManager;
    private Keyboard keyboard;

    private Canvas canvas;
    private BufferedImage backBuffer;

    private boolean isRunning;

    public GameWindow(String title, int width, int height) {
        super(title);

        setResizable(false);
        setSize(width, height);
    }

    @Override
    public void run() {
        initialize();

        double frameCap = 1.0 / 60.0;

        double firstTime = 0;
        double elapsedTime = 0;
        double lastTime = 0;

        double unprocessedTime = 0;
        double frameTime = 0;

        int fps = 0;
        int frames = 0;

        isRunning = true;
        while (isRunning) {
            firstTime = System.nanoTime() / 1000000000.0;
            elapsedTime = firstTime - lastTime;
            lastTime = firstTime;

            unprocessedTime += elapsedTime;
            frameTime += elapsedTime;

            boolean shouldRender = false;

            while (unprocessedTime >= frameCap) {
                unprocessedTime -= frameCap;
                shouldRender = true;

                if (frameTime >= 1.0) {

                    frameTime = 0;
                    fps = frames;
                    frames = 0;

                    System.out.println("FPS: " + fps);
                }
            }

            if (shouldRender) {
                frames++;

                update();
                render();
            }
            else {
                try {
                    Thread.sleep(1);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected void initialize() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        canvas = new Canvas();
        canvas.setSize(getSize());
        add(canvas);

        pack();

        backBuffer = new BufferedImage(
            canvas.getWidth(),
            canvas.getHeight(),
            BufferedImage.TYPE_INT_RGB
        );

        keyboard = new Keyboard();
        canvas.addKeyListener(keyboard);
        canvas.setFocusable(true);

        setVisible(true);

        gameStateManager = new GameStateManager();
        gameStateManager.pushState(new PlayingState());
    }

    protected void update() {
        gameStateManager.getCurrentState().update();
    }

    protected void render() {
        Graphics g = canvas.getGraphics();
        Graphics2D bbg = (Graphics2D)backBuffer.getGraphics();

        clear(Color.BLACK);

        gameStateManager.getCurrentState().render(bbg);

        g.drawImage(backBuffer, 0, 0, this);
    }

    private void clear(Color clearColor) {
        Graphics bbg = backBuffer.getGraphics();
        bbg.setColor(clearColor);
        bbg.fillRect(0,0, getWidth(), getHeight());
    }
}