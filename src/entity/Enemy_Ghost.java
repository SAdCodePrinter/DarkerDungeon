package entity;

import MainGUI.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Enemy_Ghost extends Entity {

    private int spriteCounter = 0; // Counter to keep track of animation frames

    public Enemy_Ghost(GamePanel gamePanel, String imagePath) {
        super(gamePanel);
        getGhostImage(imagePath);
    }

    private void getGhostImage(String path) {
        try {
            for (int i = 0; i < 8; i++) {
                String tmp = path + "idle (" + (i + 1) + ").png";
                idle[i] = ImageIO.read(Objects.requireNonNull(Player.class.getResourceAsStream(tmp)));
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

        int targetX = target.getX();
        int targetY = target.getY();

        if (getX() < targetX) {
            setX(getX() + speed);
        } else if (getX() > targetX) {
            setX(getX() - speed);
        }

        if (getY() < targetY) {
            setY(getY() + speed);
        } else if (getY() > targetY) {
            setY(getY() - speed);
        }

        // Update sprite animation
        updateSprite();

        // Set the direction of the ghost based on movement
        direction = "idle";
    }

    private Player nearestPlayer(Enemy_Ghost ghost, Player player1, Player player2) {
        double distanceToPlayer1 = Math.sqrt(Math.pow(ghost.getX() - player1.getX(), 2) + Math.pow(ghost.getY() - player1.getY(), 2));
        double distanceToPlayer2 = Math.sqrt(Math.pow(ghost.getX() - player2.getX(), 2) + Math.pow(ghost.getY() - player2.getY(), 2));

        return distanceToPlayer1 <= distanceToPlayer2 ? player1 : player2;
    }

    private void updateSprite() {
        spriteCounter++;
        if (spriteCounter > 10) { // Adjust the speed of the animation here
            spriteNum++;
            if (spriteNum >= idle.length) {
                spriteNum = 0;
            }
            spriteCounter = 0;
        }
    }

    public void drawGhost(Graphics2D g) {
        BufferedImage imageGhost = idle[spriteNum];

        if (imageGhost != null) {
            g.drawImage(imageGhost, getX() - 20, getY() - gamePanel.getTileSize() / 2, gamePanel.getTileSize() * 2, gamePanel.getTileSize() * 2, null);
        }
    }
}
