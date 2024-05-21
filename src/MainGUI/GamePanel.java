package MainGUI;

import entity.Karaktere;
import tile.TileHandler;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    Thread gameThread;
    Karaktere characters = new Karaktere(this);
    TileHandler tileH;
    private final int screenWidth = 48 * 28;
    private final int screenHeight = 48 * 14;

    public int getScreenCol() {
        return 28;
    }

    public int getScreenRow() {
        return 14;
    }

    public int getTileSize() {
        return 36;
    }

    public int getScreenWith() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }


    public GamePanel() {
        // toDo: Auslagern in Window- Klasse
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        tileH = new TileHandler(this);

        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(characters.kH1);
        this.addKeyListener(characters.kH2);
        this.setFocusable(true);

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

        Graphics2D g1 = (Graphics2D) g;

        tileH.drawBackGroundTiles(g1);

        // toDo: spriteNum und Direction übergeben und nur zeichnen in der draw Methode
        characters.player1.drawPlayer1(g1);
        characters.player2.drawPlayer2(g1);

        tileH.drawForGroundTiles(g1);

        g1.dispose();


    }
}





