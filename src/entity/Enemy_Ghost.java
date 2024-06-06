package entity;

import MainGUI.CollisionHandler;
import MainGUI.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Enemy_Ghost extends Entity {

    private final CollisionHandler collisionHandler;

    public Enemy_Ghost(GamePanel gamePanel, String imagePath) {
        super(gamePanel);
        collisionHandler = new CollisionHandler(gamePanel);
        getGhostImage(imagePath);
    }

    private void getGhostImage(String path) {
        try {
            for (int i = 0; i < 8; i++) {
                idle[i] = ImageIO.read(Objects.requireNonNull(Player.class.getResourceAsStream(path + "idle (" + (i + 1) + ").png")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void setDefault(int xKoord, int yKoord, int defineSpeed) {
        setX(xKoord);
        setY(yKoord);
        setSpeed(defineSpeed);
        setDirection("idle");
    }

    public void drawHitbox(Graphics2D g) {
        g.setColor(Color.red);
        g.drawRect(x, y, 36, 36);
    }

    public void move(Player player1, Player player2) {
        Player target = nearestPlayer(this, player1, player2);
        // Die Richtung in die der Ghost gehen soll festlegen
        direction = "idle";
    }

    private Player nearestPlayer(Enemy_Ghost ghost, Player player1, Player player2) {
        double distanceToPlayer1 = Math.sqrt(Math.pow(ghost.getX() - player1.getX(), 2) + Math.pow(ghost.getY() - player1.getY(), 2));
        double distanceToPlayer2 = Math.sqrt(Math.pow(ghost.getX() - player2.getX(), 2) + Math.pow(ghost.getY() - player2.getY(), 2));

        return distanceToPlayer1 <= distanceToPlayer2 ? player1 : player2;
    }

    public void drawGhost(Graphics2D g) {
        BufferedImage imageGhost =
                switch (direction) {
                    case "idle" -> idle[spriteNum];
                    default -> null;
                };

        if (imageGhost != null) {
            g.drawImage(imageGhost, getX() - 32, getY() - 36, gamePanel.getTileSize() * 3, gamePanel.getTileSize() * 3, null);
        }
    }
}
