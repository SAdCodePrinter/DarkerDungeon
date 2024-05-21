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

    //GamePanel gamePanel;
    KeyHandler keyH;

    public Player(GamePanel gamePanel, KeyHandler keyH, String imagePath) {

        super (gamePanel);

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

        if (!keyH.upPressed && !keyH.downPressed && !keyH.leftPressed && !keyH.rightPressed) {
            direction = "idle";

            // Hier kannst du den Code für die Animation der "idle" Richtung einfügen,
            // um den spriteCounter zu erhöhen und die Sprite-Nummer entsprechend zu ändern
            spriteCounter++;
            if (spriteCounter > 7) {
                spriteNum++;
                spriteNum = (spriteNum % 8);
                spriteCounter = 0;
            }
        } else {
            CollisionHandler collisionHandler = new CollisionHandler(gamePanel);

            spriteCounter++;
            if (spriteCounter > 7) {
                spriteNum++;// Da es 8 Bilder gibt, beginnen wir mit 0 als Counter
                spriteNum = (spriteNum % 8); // Der Modulo-Operator (%) ermöglicht es, den Counter auf 1 zurückzusetzen, nachdem 8 erreicht wurde
                spriteCounter = 0;
            }
            if (keyH.upPressed) {
                direction = "up";

// Todo: Boarder Abfrage in Funktion umwandeln:
                // Wenn schräg gelaufen wird: den Speed verringern
                if ((keyH.rightPressed || keyH.leftPressed) &&
                        collisionHandler.noCollisionWithPlayer((keyH.rightPressed ? "right" : "left"), this, other, speed)) {
                    // Um den out-of-Bounds Bereich zu beachten
                    if (x > 0 && x < 27 * 48 - 25) {
                        if (y < 0) {
                            // Koordinaten der Map-Ende
                            y = 14 * 48;
                        }

                        if (collisionHandler.noCollisionWithTiles(direction, this, speed)) {
                            y -= speed - 1;
                        }
                    }

                } else if (collisionHandler.noCollisionWithPlayer(direction, this, other, speed)) {
                    // Um den out-of-Bounds Bereich zu beachten
                    if (x > 0 && x < 27 * 48 - 25) {

                        if (y < 0) {
                            y = 14 * 48;
                        }

                        if (collisionHandler.noCollisionWithTiles("up", this, speed)) {
                            y -= speed;
                        }
                    }
                }

            } else if (keyH.downPressed) {
                direction = "down";

                if ((keyH.rightPressed || keyH.leftPressed) &&
                        collisionHandler.noCollisionWithPlayer((keyH.rightPressed ? "right" : "left"), this, other, speed)) {

                    // Um den out-of-Bounds Bereich zu beachten
                    if (x > 0 && x < 27 * 48 - 25) {

                        // Da die Koordinate oben links vom Player startet, ist das näschte Tile früher da beim Heruntergehen
                        if (y > 13 * 48 - 25) {
                            y = 0;
                        }

                        if (collisionHandler.noCollisionWithTiles(direction, this, speed)) {
                            y += speed - 1;
                        }
                    }

                } else if (collisionHandler.noCollisionWithPlayer(direction, this, other, speed)) {
                    // Um den out-of-Bounds Bereich zu beachten
                    if (x > 0 && x < 27 * 48 - 25) {

                        // Da die Koordinate oben links vom Player startet, ist das näschte Tile früher da beim Heruntergehen
                        if (y > 13 * 48 - 25) {
                            y = 0;
                        }

                        if (collisionHandler.noCollisionWithTiles(direction, this, speed)) {
                            y += speed;
                        }
                    }
                }

            }

            if (keyH.leftPressed && collisionHandler.noCollisionWithPlayer("left", this, other, speed)) {
                // Um den out-of-Bounds Bereich zu beachten
                if (y > 0 && y < 13 * 48 - 25) {
                    direction = "left";

                    if (x < 0) {
                        x = 28 * 48;
                    }

                    if (collisionHandler.noCollisionWithTiles("left", this, speed)) {
                        x -= speed;
                    }
                }

            } else if (keyH.rightPressed && collisionHandler.noCollisionWithPlayer("right", this, other, speed)) {
                // Um den out-of-Bounds Bereich zu beachten
                if (y > 0 && y < 13 * 48 - 25) {
                    direction = "right";

                    if (x > 27 * 48 - 25) {
                        x = 0;
                    }

                    if (collisionHandler.noCollisionWithTiles("right", this, speed)) {
                        x += speed;
                    }
                }
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

