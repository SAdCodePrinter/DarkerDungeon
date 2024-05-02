package MainGUI;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    private int tileSize = 48;
    private int screenWidth = tileSize * 28;
    private int screenHeight = tileSize * 16;


    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);

    //Default Posotion
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 3;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
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
    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;//DAS PLAYER
        Graphics2D g3 = (Graphics2D) g;
        player.draw(g2);

        g2.setColor(Color.white);
        g3.setColor(Color.red);

        g3.fillRect(200, 200, tileSize, tileSize);

        g2.dispose();
        g3.dispose();
    }
}





