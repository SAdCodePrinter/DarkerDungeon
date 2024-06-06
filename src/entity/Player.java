package entity;

import MainGUI.CollisionHandler;
import MainGUI.GamePanel;
import MainGUI.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    private final CollisionHandler collisionHandler;

    //GamePanel gamePanel;
    KeyHandler keyH;


    public Player(GamePanel gamePanel, KeyHandler keyH, String imagePath) {
        super(gamePanel);

        collisionHandler = new CollisionHandler(gamePanel);

        //this.gamePanel = gamePanel;
        this.keyH = keyH;
        getPlayerImage(imagePath);
    }

    public void setDefault(int xKoord, int yKoord, int defineSpeed) {
        setX(xKoord);
        setY(yKoord);
        setSpeed(defineSpeed);
        setDirection("idle");

        //Status
        maxLife = 6;
        life = maxLife;
    }

    public void getPlayerImage(String path) {
        try {
            for (int i = 0; i < 8; i++) {
                up[i] = ImageIO.read(Objects.requireNonNull(Player.class.getResourceAsStream(path + "up (" + (i + 1) + ").png")));
                down[i] = ImageIO.read(Objects.requireNonNull(Player.class.getResourceAsStream(path + "down (" + (i + 1) + ").png")));
                left[i] = ImageIO.read(Objects.requireNonNull(Player.class.getResourceAsStream(path + "left (" + (i + 1) + ").png")));
                right[i] = ImageIO.read(Objects.requireNonNull(Player.class.getResourceAsStream(path + "right (" + (i + 1) + ").png")));
                idle[i] = ImageIO.read(Objects.requireNonNull(Player.class.getResourceAsStream(path + "idle (" + (i + 1) + ").png")));
                //hit_left[i] = ImageIO.read(Objects.requireNonNull(Player.class.getResourceAsStream(path + "hit_left (" + (i + 1) + ").png")));
                //hit_right[i] = ImageIO.read(Objects.requireNonNull(Player.class.getResourceAsStream(path + "hit_right (" + (i + 1) + ").png")));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void drawHitbox(Graphics2D g) {
        g.setColor(Color.red);
        g.drawRect(x, y, 36, 36);
    }
    /**
     * Updatet die Positionskoordinaten, wenn keine Kollision entsteht
     *
     * @param other ist das Element bei dem geschaut wird, ob es eine Kollision mit diesem geben wird
     */
    public void move(Player other) {
        spriteCounter(8);

        // toDo: evtl. die Kollisionsabfrage im GamPanel machen, und in dieser Methode nur die Koordinaten updaten
        //  dann aber die Richtung in die der Player geht vor dem Methodenaufruf definieren


        if (!keyH.upPressed && !keyH.downPressed && !keyH.leftPressed && !keyH.rightPressed) {
            direction = "idle";

            return;
        }

        if (keyH.upPressed) {
            direction = "up";

            if (collisionHandler.noColisionUp(this.x, this.y, other.x, other.y, speed, gamePanel.getTileSize())) {
                // Wenn schräg gelaufen wird: die Geschwindigkeit verringern
                y -= (keyH.rightPressed || keyH.leftPressed) ?
                        (int) (speed * 0.8) : speed;
                System.out.println("Slay Bitch");
            }

        } else if (keyH.downPressed) {
            direction = "down";

            if (collisionHandler.noColisionDown(this.x, this.y, other.x, other.y, speed, gamePanel.getTileSize())) {
                y += (keyH.rightPressed || keyH.leftPressed) ?
                        (int) (speed * 0.8) : speed;

            }
        }

        if (keyH.leftPressed) {
            direction = "left";

            if (collisionHandler.noColisionLeft(this.x, this.y, other.x, other.y, speed, gamePanel.getTileSize())) {
                x -= (keyH.upPressed || keyH.downPressed) ?
                        (int) (speed * 0.8) : speed;
            }

        } else if (keyH.rightPressed) {
            direction = "right";

            if (collisionHandler.noColisionRight(this.x, this.y, other.x, other.y, speed, gamePanel.getTileSize())) {
                x += (keyH.upPressed || keyH.downPressed) ?
                        (int) (speed * 0.8) : speed;
            }
        }

    }

    public int getWidth() {
        return gamePanel.getTileSize();
    }

    public int getHeight() {
        return gamePanel.getTileSize();
    }

    public void drawPlayer(Graphics2D g) {
        BufferedImage imagePlayer =
                switch (direction) {
                    case "idle" -> idle[spriteNum];
                    case "up" -> up[spriteNum];
                    case "down" -> down[spriteNum];
                    case "left" -> left[spriteNum];
                    case "right" -> right[spriteNum];
                    case "hit_left" -> hit_left[spriteNum];
                    default -> null;
                };

        if (imagePlayer != null) {
            g.drawImage(imagePlayer, getX(), getY() - getHeight(), getWidth() + 4, getHeight() * 2, null);
        }
    }

}

