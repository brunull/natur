package com.brunull.natur;

import com.brunull.natur.graphics.Renderer;
import com.brunull.natur.input.Keyboard;
import com.brunull.natur.state.GameStateManager;
import com.brunull.natur.state.MainMenuState;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Game extends JPanel implements Runnable {

	private JFrame window;
	
    private Renderer renderer;

    private AssetManager assetManager;

    private GameStateManager gameStateManager;
    private Keyboard keyboard;

    private BufferedImage backBuffer;

    private boolean isRunning;

    public Game() {
        window = new JFrame("Natur");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.setResizable(false);
        
        setMinimumSize(new Dimension(800, 600));
        setMaximumSize(new Dimension(800, 600));
        setPreferredSize(new Dimension(800, 600));
        setSize(800, 600);

        window.add(this);
        window.pack();
        window.setVisible(true);
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.run();
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

    private void initialize() {

        backBuffer = new BufferedImage(
            getWidth(),
            getHeight(),
            BufferedImage.TYPE_INT_RGB
        );

        keyboard = new Keyboard();
        window.addKeyListener(keyboard);
        setFocusable(true);

        renderer = new Renderer();

        assetManager = new AssetManager();

        gameStateManager = new GameStateManager(this);
        gameStateManager.pushState(new MainMenuState(gameStateManager));
    }

    private void update() {
        gameStateManager.getCurrentState().update();
    }

    private void render() {
        Graphics g = getGraphics();
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

    public Renderer getRenderer() {
        return renderer;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }
    
    public JFrame getWindow() {
    	return window;
    }
}