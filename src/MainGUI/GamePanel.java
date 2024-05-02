package MainGUI;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    private int tileSize = 48;
    private int screenWidth = tileSize * 28;
    private int screenHeight = tileSize * 16;


    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
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
        //GameLoop:
        while (gameThread != null){

            //1. Update:
            update();
            //2. Draw:
            repaint();
        }
    }
    public void update(){
        //player.update();

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);

        g2.fillRect(100, 100, tileSize, tileSize);

        g2.dispose();
    }
}





