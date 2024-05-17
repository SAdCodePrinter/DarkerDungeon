package MainGUI;

import entity.Karaktere;
import tile.TileHandler;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CompletableFuture;

public class GamePanel extends JPanel implements Runnable {
    Thread gameThread;
    Karaktere characters = new Karaktere(this);
    TileHandler tileH = new TileHandler(this);
    private int screenWidth = 48 * 28;
    private int screenHeight = 48 * 14;

    public GamePanel() {
        // toDo: Auslagern in Window- Klasse
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));

//          Falls wir Fullscreen einbinden wollen und keine feste Window- Size
//        protected void setWindowSize() {
//            width = frame.getBounds().width;
//            height = frame.getBounds().height;
//            screen.setSize(width, height);
//        }

        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(characters.kH1);
        this.addKeyListener(characters.kH2);
        this.setFocusable(true);

    }

    public int getTileSize() {
        return 82;
    }

    public int getScreenWith() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        //"SleepMethod"
        double drawInterval = 1000000000 / 60.; // FPS: Der Screen kann 60-mal die Sekunde gezeichnet werden
        double nextDrawTime = System.nanoTime() + drawInterval;

        //Game Loop
        while (gameThread != null) {
            this.update();
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

        Graphics2D g1 = (Graphics2D) g; // DAS PLAYER1
        Graphics2D g2 = (Graphics2D) g; // DAS PLAYER2
        Graphics2D g3 = (Graphics2D) g;
        Graphics2D g4 = (Graphics2D) g;

        // toDo: Muss man den Hintergrund nicht nur einmalig zeichnen?
        //  - oder durch zweiten Thread?
        //  - oder nur ein Bild der Map
        //  - Wie wurde das im Tutorial gemacht?

        //CompletableFuture.runAsync(() -> {
        tileH.drawBackGroundTiles(g3);
        //});

        //characters.player1.drawPlayer1(g1);
        //characters.player2.drawPlayer2(g2);

//        tileH.drawForGroundTiles(g4);

        g1.dispose();
        g2.dispose();
        g3.dispose();


    }
}





