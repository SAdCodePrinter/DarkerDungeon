package MainGUI;

import object.OBJ_Heart;
import object.ObjectHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gamePanel;
    Font arial_40;
    BufferedImage heart_full, heart_half, heart_blanc;
    Graphics2D g1;

    public UI(GamePanel gamePanel) {

        this.gamePanel = gamePanel;

        new Font("Arial", Font.PLAIN, 40);

        //HUD
        ObjectHandler heart = new OBJ_Heart(gamePanel);
        heart_full = heart.image1;
        heart_half = heart.image2;
        heart_blanc = heart.image3;
    }

    public void draw(Graphics2D g1) {
        this.g1 = g1;

        g1.setFont(arial_40);
        g1.setColor(Color.white);

        if (gamePanel.getGameState() == gamePanel.getPlayState()) {
            drawPlayerLife();
        }

        if (gamePanel.getGameState() == gamePanel.getPauseState()) {
            String text = "SYYYMON";
            int length = (int) g1.getFontMetrics().getStringBounds(text, g1).getWidth();
            int x = gamePanel.getScreenWidth() / 2 - length / 2;
            int y = gamePanel.getScreenHeight() / 2;

            g1.drawString(text, x, y);

        }

        g1.setFont(arial_40);
        g1.setColor(Color.BLUE);
        g1.drawString("SYYYMON", 50, 50);
    }

    private void drawPlayerLife() {
        int x = gamePanel.getTileSize();
        int y = gamePanel.getTileSize() * 16;
        int spaceBetweenHearts = 20; // Abstand zwischen den Herzen

        // MaxLife
        for (int i = 0; i < gamePanel.characters.player1.maxLife / 2; i++) {
            g1.drawImage(heart_blanc, x, y, null);
            x += gamePanel.getTileSize() + spaceBetweenHearts;
        }

        x = gamePanel.getTileSize();
        y = gamePanel.getTileSize() * 16;

        // CurrentLife
        for (int i = 0; i < gamePanel.characters.player1.life; ) {
            g1.drawImage(heart_half, x, y, null);
            i++;
            if (i < gamePanel.characters.player1.life) {
                g1.drawImage(heart_full, x, y, null);
                i++;
            }
            x += gamePanel.getTileSize() + spaceBetweenHearts;
        }
    }
}


