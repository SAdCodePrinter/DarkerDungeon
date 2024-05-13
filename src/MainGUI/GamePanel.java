package MainGUI;

import entity.Karaktere;
import tile.TileHandler;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    Thread gameThread;
    Karaktere characters = new Karaktere(this);
    TileHandler tileH = new TileHandler(this);


    public GamePanel() {
        int screenWidth = 48 * 28;
        int screenHeight = 48 * 14;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(characters.kH1);
        this.addKeyListener(characters.kH2);
        this.setFocusable(true);

    }

    public int getTileSize() {
        return 24;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        //"SleepMethod"
        double drawInterval = 1000000000 / 60.; //FPS: Der Screen kann 60-mal die Sekunde gezeichnet werden
        double nextDrawTime = System.nanoTime() + drawInterval;

        //Game Loop
        while (gameThread != null) {
            update();
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                remainingTime = remainingTime / 1000000;

                Thread.sleep((long) remainingTime); //Pausiert den Loop in Millis

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void update() {

        characters.player1.move(characters.player2);

        characters.player2.move(characters.player1);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g1 = (Graphics2D) g; // DAS PLAYER
        Graphics2D g2 = (Graphics2D) g;
        Graphics2D g3 = (Graphics2D) g;
        Graphics2D g4 = (Graphics2D) g;

        tileH.drawBackGroundTiles(g3);

        characters.player1.draw(g1, Color.white);
        characters.player2.draw(g2, Color.red);

        tileH.drawForGroundTiles(g4);

        g1.dispose();
        g2.dispose();
        g3.dispose();
    }
}





