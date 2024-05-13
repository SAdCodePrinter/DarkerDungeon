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

    public Player(GamePanel gamePanel, KeyHandler keyH) {
        this.gamePanel = gamePanel;
        this.keyH = keyH;
        getPlayerImage();

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

    /**
     * Updatet die Positionskoordinaten, wenn keine Kollision entsteht
     *
     * @param other ist das Element bei dem geschaut wird, ob es eine Kollision mit diesem geben wird
     */
    public void move(Player other) {
        //TODO: Schräg laufen!

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
            spriteCounter++;
            if (spriteCounter > 7) { // Da es 8 Bilder gibt, beginnen wir mit 0 als Counter
                spriteNum = (spriteNum % 8) + 1; // Der Modulo-Operator (%) ermöglicht es, den Counter auf 1 zurückzusetzen, nachdem 8 erreicht wurde
                spriteCounter = 0;
            }
            if (keyH.upPressed && CollisionHandler.noCollisionNextStep("up", this, other, speed)) {
                y -= speed;
                direction = "up";

            } else if (keyH.downPressed && CollisionHandler.noCollisionNextStep("down", this, other, speed)) {
                y += speed;
                direction = "down";

            } else if (keyH.leftPressed && CollisionHandler.noCollisionNextStep("left", this, other, speed)) {
                x -= speed;
                direction = "left";

            } else if (keyH.rightPressed && CollisionHandler.noCollisionNextStep("right", this, other, speed)) {
                x += speed;
                direction = "right";
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
                switch (spriteNum) {
                    case 1:
                        imagePlayer1 = idle1;
                        break;
                    case 2:
                        imagePlayer1 = idle2;
                        break;
                    case 3:
                        imagePlayer1 = idle3;
                        break;
                    case 4:
                        imagePlayer1 = idle4;
                        break;
                    case 5:
                        imagePlayer1 = idle5;
                        break;
                    case 6:
                        imagePlayer1 = idle6;
                        break;
                }
                break;
            case "up":
                switch (spriteNum) {
                    case 1:
                        imagePlayer1 = up1;
                        break;
                    case 2:
                        imagePlayer1 = up2;
                        break;
                    case 3:
                        imagePlayer1 = up3;
                        break;
                    case 4:
                        imagePlayer1 = up4;
                        break;
                    case 5:
                        imagePlayer1 = up5;
                        break;
                    case 6:
                        imagePlayer1 = up6;
                        break;
                    case 7:
                        imagePlayer1 = up7;
                        break;
                    case 8:
                        imagePlayer1 = up8;
                        break;
                }
                break;
            case "down":
                switch (spriteNum) {
                    case 1:
                        imagePlayer1 = down1;
                        break;
                    case 2:
                        imagePlayer1 = down2;
                        break;
                    case 3:
                        imagePlayer1 = down3;
                        break;
                    case 4:
                        imagePlayer1 = down4;
                        break;
                    case 5:
                        imagePlayer1 = down5;
                        break;
                    case 6:
                        imagePlayer1 = down6;
                        break;
                    case 7:
                        imagePlayer1 = down7;
                        break;
                    case 8:
                        imagePlayer1 = down8;
                        break;
                }
                break;
            case "left":
                switch (spriteNum) {
                    case 1:
                        imagePlayer1 = left1;
                        break;
                    case 2:
                        imagePlayer1 = left2;
                        break;
                    case 3:
                        imagePlayer1 = left3;
                        break;
                    case 4:
                        imagePlayer1 = left4;
                        break;
                    case 5:
                        imagePlayer1 = left5;
                        break;
                    case 6:
                        imagePlayer1 = left6;
                        break;
                    case 7:
                        imagePlayer1 = left7;
                        break;
                    case 8:
                        imagePlayer1 = left8;
                        break;
                }
                break;
            case "right":
                switch (spriteNum) {
                    case 1:
                        imagePlayer1 = right1;
                        break;
                    case 2:
                        imagePlayer1 = right2;
                        break;
                    case 3:
                        imagePlayer1 = right3;
                        break;
                    case 4:
                        imagePlayer1 = right4;
                        break;
                    case 5:
                        imagePlayer1 = right5;
                        break;
                    case 6:
                        imagePlayer1 = right6;
                        break;
                    case 7:
                        imagePlayer1 = right7;
                        break;
                    case 8:
                        imagePlayer1 = right8;
                        break;
                }
                break;
        }
        // Zeichne das Bild des Players
        if (imagePlayer1 != null) {
            g1.drawImage(imagePlayer1, x, y, gamePanel.getTileSize() + 4, gamePanel.getTileSize()*2, null);
        }
    }

    public void drawPlayer2(Graphics2D g2) {
        g2.setColor(Color.BLUE);
        g2.fillRect(x, y, gamePanel.getTileSize(), gamePanel.getTileSize());

    }
}

