package MainGUI;

import entity.Karaktere;
import tile.TileHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel{
    Karaktere characters;
    public TileHandler tileH;
    private final int screenWidth = 48 * 28;
    private final int screenHeight = 48 * 14;
    private final int delay = 1000 / 60; // Timer delay für 60 FPS

    public int getScreenCol() {
        return 37;
    }

    public int getScreenRow() {
        return 18;
    }

    public int getTileSize() {
        return 36;
    }

    public int getPHitboxWidth() {
        return 36;
    }

    public int getPHitboxHeight() {
        return 36;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        characters = new Karaktere(this);
        tileH = new TileHandler(this);

        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(characters.kH1);
        this.addKeyListener(characters.kH2);
        this.setFocusable(true);

// Timer für die Aktualisierung und das Neuzeichnen des Panels
        Timer timer = new Timer(delay, e -> {
            update();
            repaint();
        });
        timer.start();
    }

    private void update() {
        // den anderen Player übergeben, um eine Kollision abzufragen
        characters.player1.move(characters.player2);
        characters.player2.move(characters.player1);
        characters.troll1.move(characters.player1, characters.player2);

    }

    // toDO: das drawn auslagern
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g1 = (Graphics2D) g;

        tileH.drawBackGroundTiles(g1, true);

        characters.player1.drawPlayer(g1);
        characters.player1.drawHitbox(g1);

        characters.player2.drawPlayer(g1);
        characters.player2.drawHitbox(g1);

        characters.troll1.drawTroll(g1);
        characters.troll1.drawHitbox(g1);

        tileH.drawBackGroundTiles(g1, false);

        g1.dispose();


    }
}





