package entity;

import MainGUI.CollisionHandler;
import MainGUI.GamePanel;
import MainGUI.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyH;
    private boolean isPlayerOne = false;

    public Player(GamePanel gamePanel, KeyHandler keyH) {
        this.gamePanel = gamePanel;
        this.keyH = keyH;
        getPlayerImage();
        getPlayer2Image();
    }

    public void setDefault(int xKoord, int yKoord, int defineSpeed) {
        setX(xKoord);
        setY(yKoord);
        setSpeed(defineSpeed);
        setDirection("idle");
    }

    public void getPlayerImage() {
        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("/player/player_up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/player_up2.png"));

            up3 = ImageIO.read(getClass().getResourceAsStream("/player/player_up3.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/player/player_up4.png"));
            up5 = ImageIO.read(getClass().getResourceAsStream("/player/player_up5.png"));
            up6 = ImageIO.read(getClass().getResourceAsStream("/player/player_up6.png"));
            up7 = ImageIO.read(getClass().getResourceAsStream("/player/player_up7.png"));
            up8 = ImageIO.read(getClass().getResourceAsStream("/player/player_up8.png"));

            down1 = ImageIO.read(getClass().getResourceAsStream("/player/player_down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/player_down2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/player_down3.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/player/player_down4.png"));
            down5 = ImageIO.read(getClass().getResourceAsStream("/player/player_down5.png"));
            down6 = ImageIO.read(getClass().getResourceAsStream("/player/player_down6.png"));
            down7 = ImageIO.read(getClass().getResourceAsStream("/player/player_down7.png"));
            down8 = ImageIO.read(getClass().getResourceAsStream("/player/player_down8.png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("/player/player_left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/player_left2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/player/player_left3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/player/player_left4.png"));
            left5 = ImageIO.read(getClass().getResourceAsStream("/player/player_left5.png"));
            left6 = ImageIO.read(getClass().getResourceAsStream("/player/player_left6.png"));
            left7 = ImageIO.read(getClass().getResourceAsStream("/player/player_left7.png"));
            left8 = ImageIO.read(getClass().getResourceAsStream("/player/player_left8.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("/player/player_right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/player_right2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/player/player_right3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/player/player_right4.png"));
            right5 = ImageIO.read(getClass().getResourceAsStream("/player/player_right5.png"));
            right6 = ImageIO.read(getClass().getResourceAsStream("/player/player_right6.png"));
            right7 = ImageIO.read(getClass().getResourceAsStream("/player/player_right7.png"));
            right8 = ImageIO.read(getClass().getResourceAsStream("/player/player_right8.png"));

            idle1 = ImageIO.read(getClass().getResourceAsStream("/player/player_idle1.png"));
            idle2 = ImageIO.read(getClass().getResourceAsStream("/player/player_idle2.png"));
            idle3 = ImageIO.read(getClass().getResourceAsStream("/player/player_idle3.png"));
            idle4 = ImageIO.read(getClass().getResourceAsStream("/player/player_idle4.png"));
            idle5 = ImageIO.read(getClass().getResourceAsStream("/player/player_idle5.png"));
            idle6 = ImageIO.read(getClass().getResourceAsStream("/player/player_idle6.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getPlayer2Image() {
        try {

            up10 = ImageIO.read(getClass().getResourceAsStream("/player2/player2_up1.png"));
            up20 = ImageIO.read(getClass().getResourceAsStream("/player2/player2_up2.png"));

            up30 = ImageIO.read(getClass().getResourceAsStream("/player2/player2_up3.png"));
            up40 = ImageIO.read(getClass().getResourceAsStream("/player2/player2_up4.png"));
            up50 = ImageIO.read(getClass().getResourceAsStream("/player2/player2_up5.png"));
            up60 = ImageIO.read(getClass().getResourceAsStream("/player2/player2_up6.png"));
            up70 = ImageIO.read(getClass().getResourceAsStream("/player2/player2_up7.png"));
            up80 = ImageIO.read(getClass().getResourceAsStream("/player2/player2_up8.png"));

            down10 = ImageIO.read(getClass().getResourceAsStream("/player2/player2_down1.png"));
            down20 = ImageIO.read(getClass().getResourceAsStream("/player2/player2_down2.png"));
            down30 = ImageIO.read(getClass().getResourceAsStream("/player2/player2_down3.png"));
            down40 = ImageIO.read(getClass().getResourceAsStream("/player2/player2_down4.png"));
            down50 = ImageIO.read(getClass().getResourceAsStream("/player2/player2_down5.png"));
            down60 = ImageIO.read(getClass().getResourceAsStream("/player2/player2_down6.png"));
            down70 = ImageIO.read(getClass().getResourceAsStream("/player2/player2_down7.png"));
            down80 = ImageIO.read(getClass().getResourceAsStream("/player2/player2_down8.png"));

            left10 = ImageIO.read(getClass().getResourceAsStream("/player2/player2_left1.png"));
            left20 = ImageIO.read(getClass().getResourceAsStream("/player2/player2_left2.png"));
            left30 = ImageIO.read(getClass().getResourceAsStream("/player2/player2_left3.png"));
            left40 = ImageIO.read(getClass().getResourceAsStream("/player2/player2_left4.png"));
            left50 = ImageIO.read(getClass().getResourceAsStream("/player2/player2_left5.png"));
            left60 = ImageIO.read(getClass().getResourceAsStream("/player2/player2_left6.png"));
            left70 = ImageIO.read(getClass().getResourceAsStream("/player2/player2_left7.png"));
            left80 = ImageIO.read(getClass().getResourceAsStream("/player2/player2_left8.png"));

            right10 = ImageIO.read(getClass().getResourceAsStream("/player2/player2_right1.png"));
            right20 = ImageIO.read(getClass().getResourceAsStream("/player2/player2_right2.png"));
            right30 = ImageIO.read(getClass().getResourceAsStream("/player2/player2_right3.png"));
            right40 = ImageIO.read(getClass().getResourceAsStream("/player2/player2_right4.png"));
            right50 = ImageIO.read(getClass().getResourceAsStream("/player2/player2_right5.png"));
            right60 = ImageIO.read(getClass().getResourceAsStream("/player2/player2_right6.png"));
            right70 = ImageIO.read(getClass().getResourceAsStream("/player2/player2_right7.png"));
            right80 = ImageIO.read(getClass().getResourceAsStream("/player2/player2_right8.png"));

            idle10 = ImageIO.read(getClass().getResourceAsStream("/player2/player2_idle1.png"));
            idle20 = ImageIO.read(getClass().getResourceAsStream("/player2/player2_idle2.png"));
            idle30 = ImageIO.read(getClass().getResourceAsStream("/player2/player2_idle3.png"));
            idle40 = ImageIO.read(getClass().getResourceAsStream("/player2/player2_idle4.png"));
            idle50 = ImageIO.read(getClass().getResourceAsStream("/player2/player2_idle5.png"));
            idle60 = ImageIO.read(getClass().getResourceAsStream("/player2/player2_idle6.png"));

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
                spriteNum = (spriteNum % 6) + 1;
                spriteCounter = 0;
            }
        } else {
            CollisionHandler collisionHandler = new CollisionHandler(gamePanel);

            spriteCounter++;
            if (spriteCounter > 7) { // Da es 8 Bilder gibt, beginnen wir mit 0 als Counter
                spriteNum = (spriteNum % 8) + 1; // Der Modulo-Operator (%) ermöglicht es, den Counter auf 1 zurückzusetzen, nachdem 8 erreicht wurde
                spriteCounter = 0;
            }
            if (keyH.upPressed) {
                direction = "up";

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

    /**
     * Player, nicht Hitbox
     */
    public void drawPlayer1(Graphics2D g1) {
        //g1.setColor(Color.white);
        //g1.fillRect(x, y, gamePanel.getTileSize(), gamePanel.getTileSize());
        BufferedImage imagePlayer1 = null;

        switch (direction) {
            case "idle":
                imagePlayer1 = switch (spriteNum) {
                    case 1 -> isPlayerOne ? idle1 : idle10;
                    case 2 -> idle2;
                    case 3 -> idle3;
                    case 4 -> idle4;
                    case 5 -> idle5;
                    case 6 -> idle6;
                    default -> imagePlayer1;
                };
                break;
            case "up":
                imagePlayer1 = switch (spriteNum) {
                    case 1 -> up1;
                    case 2 -> up2;
                    case 3 -> up3;
                    case 4 -> up4;
                    case 5 -> up5;
                    case 6 -> up6;
                    case 7 -> up7;
                    case 8 -> up8;
                    default -> imagePlayer1;
                };
                break;
            case "down":
                imagePlayer1 = switch (spriteNum) {
                    case 1 -> down1;
                    case 2 -> down2;
                    case 3 -> down3;
                    case 4 -> down4;
                    case 5 -> down5;
                    case 6 -> down6;
                    case 7 -> down7;
                    case 8 -> down8;
                    default -> imagePlayer1;
                };
                break;
            case "left":
                imagePlayer1 = switch (spriteNum) {
                    case 1 -> left1;
                    case 2 -> left2;
                    case 3 -> left3;
                    case 4 -> left4;
                    case 5 -> left5;
                    case 6 -> left6;
                    case 7 -> left7;
                    case 8 -> left8;
                    default -> imagePlayer1;
                };
                break;
            case "right":
                imagePlayer1 = switch (spriteNum) {
                    case 1 -> right1;
                    case 2 -> right2;
                    case 3 -> right3;
                    case 4 -> right4;
                    case 5 -> right5;
                    case 6 -> right6;
                    case 7 -> right7;
                    case 8 -> right8;
                    default -> imagePlayer1;
                };
                break;
        }
        // Zeichne das Bild des Players
        if (imagePlayer1 != null) {
            g1.drawImage(imagePlayer1, getX(), getY() - getHeight(), getWidth() + 4, getHeight() * 2, null);
        }
    }

    public void drawPlayer2(Graphics2D g) {
        //g.setColor(Color.BLUE);
        //g.fillRect(x, y, gamePanel.getTileSize(), gamePanel.getTileSize());
        BufferedImage imagePlayer2 = null;

        switch (direction) {
            case "idle":
                imagePlayer2 = switch (spriteNum) {
                    case 1 -> idle10;
                    case 2 -> idle20;
                    case 3 -> idle30;
                    case 4 -> idle40;
                    case 5 -> idle50;
                    case 6 -> idle60;
                    default -> imagePlayer2;
                };
                break;
            case "up":
                imagePlayer2 = switch (spriteNum) {
                    case 1 -> up10;
                    case 2 -> up20;
                    case 3 -> up30;
                    case 4 -> up40;
                    case 5 -> up50;
                    case 6 -> up60;
                    case 7 -> up70;
                    case 8 -> up80;
                    default -> imagePlayer2;
                };
                break;
            case "down":
                imagePlayer2 = switch (spriteNum) {
                    case 1 -> down10;
                    case 2 -> down20;
                    case 3 -> down30;
                    case 4 -> down40;
                    case 5 -> down50;
                    case 6 -> down60;
                    case 7 -> down70;
                    case 8 -> down80;
                    default -> imagePlayer2;
                };
                break;
            case "left":
                imagePlayer2 = switch (spriteNum) {
                    case 1 -> left10;
                    case 2 -> left20;
                    case 3 -> left30;
                    case 4 -> left40;
                    case 5 -> left50;
                    case 6 -> left60;
                    case 7 -> left70;
                    case 8 -> left80;
                    default -> imagePlayer2;
                };
                break;
            case "right":
                imagePlayer2 = switch (spriteNum) {
                    case 1 -> right10;
                    case 2 -> right20;
                    case 3 -> right30;
                    case 4 -> right40;
                    case 5 -> right50;
                    case 6 -> right60;
                    case 7 -> right70;
                    case 8 -> right80;
                    default -> imagePlayer2;
                };
                break;
        }
        // Zeichne das Bild des Players
        if (imagePlayer2 != null) {
            g.drawImage(imagePlayer2, x, y - getHeight(), gamePanel.getTileSize() + 4, gamePanel.getTileSize() * 2, null);
        }
    }
}

