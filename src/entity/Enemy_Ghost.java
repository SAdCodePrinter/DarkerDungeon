package entity;

import MainGUI.CollisionHandler;
import MainGUI.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Enemy_Ghost extends Entity {
    CollisionHandler collisionHandler;

    private int spriteCounter = 0;

    public Enemy_Ghost(GamePanel gamePanel, String imagePath) {
        super(gamePanel);
        collisionHandler = new CollisionHandler(gamePanel);
        getGhostImage(imagePath);

        attackRect.width = gamePanel.getTileSize();
        attackRect.height = gamePanel.getTileSize();
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

        direction = "idle";

        attacking();
    }

    private Player nearestPlayer(Enemy_Ghost ghost, Player player1, Player player2) {
        double distanceToPlayer1 = Math.sqrt(Math.pow(ghost.getX() - player1.getX(), 2) + Math.pow(ghost.getY() - player1.getY(), 2));
        double distanceToPlayer2 = Math.sqrt(Math.pow(ghost.getX() - player2.getX(), 2) + Math.pow(ghost.getY() - player2.getY(), 2));

        return distanceToPlayer1 <= distanceToPlayer2 ? player1 : player2;
        // Quelle ChatGPT (Überarbeitet)
    }

    private void updateSprite() {
        spriteCounter++;
        if (spriteCounter > 10) {
            spriteNum++;
            if (spriteNum >= idle.length) {
                spriteNum = 0;
            }
            spriteCounter = 0;
        }
    }
    private void attacking() {
        attackRect.x = getX();
        attackRect.y = getY();

        boolean playerInRange = false;
        for (Player player : gamePanel.characters.players) {
            if (collisionHandler.entityCollision(player.getX(), player.getY(), attackRect.x, attackRect.y, gamePanel.getTileSize())) {
                playerInRange = true;
                break;
            }
        }

        if (playerInRange) {
            hitSpritCounter++;
            if (hitSpritCounter == 28) {
                for (Player player : gamePanel.characters.players) {
                    if (collisionHandler.entityCollision(player.getX(), player.getY(), attackRect.x, attackRect.y, gamePanel.getTileSize())) {
                        damagePlayer();
                    }
                }
                hitSpritCounter = 0;
            }
        } else {
            hitSpritCounter = 0;
        }
    }

    private void damagePlayer() {
        gamePanel.characters.players.get(0).life -= 1;
    }

}
