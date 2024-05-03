package MainGUI;

import entity.Karaktere;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    private int tileSize = 48;
    private int screenWidth = tileSize * 28;
    private int screenHeight = tileSize * 16;


    //    KeyHandler keyH = new KeyHandler();
//    KeyHandler2 keyH2 = new KeyHandler2();
    Thread gameThread;
//    Player player = new Player(this, keyH);
//    Player2 player2 = new Player2(this, keyH2);

    Karaktere players = new Karaktere(this);


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(players.kH1);
        this.addKeyListener(players.kH2);
        this.setFocusable(true);

    }

    public int getTileSize() {
        return tileSize;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        //"SleepMethod"
        double drawInterval = 1000000000 / 60.; //FPS: Der Screen kann 60 mal die Sekunde Gedrawt werden
        double nextDrawTime = System.nanoTime() + drawInterval;

        //Game Loop
        while (gameThread != null) {
            update();
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime); //Pausiert den Loop in Millis

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void update() {
        System.out.println("KOLLISION");
        players.player1.collisionAndUpdate(players.player2);

        players.player2.collisionAndUpdate(players.player1);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g1 = (Graphics2D) g; // DAS PLAYER
        Graphics2D g2 = (Graphics2D) g;


        players.player1.draw(g1, Color.white);
        players.player2.draw(g2, Color.red);


        g1.dispose();
        g2.dispose();
    }
}





