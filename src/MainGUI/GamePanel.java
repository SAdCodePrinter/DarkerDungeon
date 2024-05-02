package MainGUI;

import entity.Player;
import entity.Player2;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    private int tileSize = 48;
    private int screenWidth = tileSize * 28;
    private int screenHeight = tileSize * 16;


    KeyHandler keyH = new KeyHandler();
    KeyHandler2 keyH2 = new KeyHandler2();
    Thread gameThread;
    Player player = new Player(this, keyH);
    Player2 player2 = new Player2(this, keyH2);


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addKeyListener(keyH2);
        this.setFocusable(true);

    }

    public int getTileSize() {
        return tileSize;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        //"SleepMethod"
        double drawInterval = 1000000000 / 60.; //FPS: Der Screen kann 60 mal die Sekunde Gedrawt werden
        double nextDrawTime = System.nanoTime() + drawInterval;

        //Game Loop
        while(gameThread != null){
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
    private void update(){
        player.update();
        player2.update();
        if(CollisionHandler.isColliding(player, player2)) {
            System.out.println("KOLLISION");
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;//DAS PLAYER
        Graphics2D g3 = (Graphics2D) g;
        player.draw(g2);
        player2.draw(g3);


        g2.dispose();
        g3.dispose();
    }
}





