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
    }

    public void getPlayerImage(String path) {
        try {
            for (int i = 0; i < 8; i++) {
                String tmp = path + "up (" + (i + 1) + ").png";
                up[i] = ImageIO.read(Objects.requireNonNull(Player.class.getResourceAsStream(path + "up (" + (i + 1) + ").png")));
                down[i] = ImageIO.read(Objects.requireNonNull(Player.class.getResourceAsStream(path + "down (" + (i + 1) + ").png")));
                left[i] = ImageIO.read(Objects.requireNonNull(Player.class.getResourceAsStream(path + "left (" + (i + 1) + ").png")));
                right[i] = ImageIO.read(Objects.requireNonNull(Player.class.getResourceAsStream(path + "right (" + (i + 1) + ").png")));
                idle[i] = ImageIO.read(Objects.requireNonNull(Player.class.getResourceAsStream(path + "idle (" + (i + 1) + ").png")));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Updatet die Positionskoordinaten, wenn keine Kollision entsteht
     *
     * @param other ist das Element bei dem geschaut wird, ob es eine Kollision mit diesem geben wird
     */
    public void move(Player other) {

        // toDo: evtl. die Kollisionsabfrage im GamPanel machen, und in dieser Methode nur die Koordinaten updaten
        //  dann aber die Richtung in die der Player geht vor dem Methodenaufruf definieren

        spriteCounter++;
        if (spriteCounter > 7) {
            spriteNum++;
            spriteNum = (spriteNum % 8);
            spriteCounter = 0;
        }


        if (!keyH.upPressed && !keyH.downPressed && !keyH.leftPressed && !keyH.rightPressed) {
            direction = "idle";
            return;
        }

        if (keyH.upPressed) {
            direction = "up";

            if (collisionHandler.noCollisionWithPlayer(direction, this, other, speed) &&
                    collisionHandler.insideBoarder((keyH.rightPressed ? (x + speed) : x), (keyH.leftPressed ? (y + speed) : y)) &&
                    collisionHandler.noCollisionWithTiles(direction, this, speed)) {

                // Wenn schräg gelaufen wird: die Geschwindigkeit verringern
                y -= (keyH.rightPressed || keyH.leftPressed) ?
                        (collisionHandler.noCollisionWithPlayer((keyH.rightPressed ? "right" : "left"), this, other, speed)) ? (int) (speed * 0.1) : 0
                        : speed;
            }

        } else if (keyH.downPressed) {
            direction = "down";

            if (collisionHandler.noCollisionWithPlayer(direction, this, other, speed) &&
                    collisionHandler.insideBoarder((keyH.rightPressed ? (x + speed) : x), (keyH.leftPressed ? (y + speed) : y)) &&
                    collisionHandler.noCollisionWithTiles(direction, this, speed)) {

                // Wenn schräg gelaufen wird: die Geschwindigkeit verringern
                y += (keyH.rightPressed || keyH.leftPressed) ?
                        (collisionHandler.noCollisionWithPlayer((keyH.rightPressed ? "right" : "left"), this, other, speed)) ? (int) (speed * 0.1) : 0
                        : speed;
            }
        }

        if (keyH.leftPressed) {
            direction = "left";

            if (collisionHandler.noCollisionWithPlayer(direction, this, other, speed) &&
                    collisionHandler.insideBoarder((keyH.rightPressed ? (x + speed) : x), (keyH.leftPressed ? (y + speed) : y)) &&
                    collisionHandler.noCollisionWithTiles(direction, this, speed)) {

                // Wenn schräg gelaufen wird: die Geschwindigkeit verringern
                x -= (keyH.upPressed || keyH.downPressed) ?
                        (collisionHandler.noCollisionWithPlayer((keyH.upPressed ? "up" : "down"), this, other, speed)) ? (int) (speed * 0.1) : 0
                        : speed;
            }

        } else if (keyH.rightPressed) {
            direction = "right";

            if (collisionHandler.noCollisionWithPlayer(direction, this, other, speed) &&
                    collisionHandler.insideBoarder((keyH.rightPressed ? (x + speed) : x), (keyH.leftPressed ? (y + speed) : y)) &&
                    collisionHandler.noCollisionWithTiles(direction, this, speed)) {

                // Wenn schräg gelaufen wird: die Geschwindigkeit verringern
                x += (keyH.upPressed || keyH.downPressed) ?
                        (collisionHandler.noCollisionWithPlayer((keyH.upPressed ? "up" : "down"), this, other, speed)) ? (int) (speed * 0.1) : 0
                        : speed;
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
                    default -> null;
                };

        if (imagePlayer != null) {
            g.drawImage(imagePlayer, getX(), getY() - getHeight(), getWidth() + 4, getHeight() * 2, null);
        }
    }

}

