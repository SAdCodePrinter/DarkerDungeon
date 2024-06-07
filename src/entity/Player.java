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

    KeyHandler keyH;

    public Player(GamePanel gamePanel, KeyHandler keyH, String imagePath) {
        super(gamePanel);

        collisionHandler = new CollisionHandler(gamePanel);

        //this.gamePanel = gamePanel;
        this.keyH = keyH;
        getPlayerImage(imagePath);

        attackRect.width = gamePanel.getTileSize();
        attackRect.height = gamePanel.getTileSize();
    }

    private void attacking() {
        switch (lastDirection) {
            case "up" -> {
                attackRect.y = y - gamePanel.getTileSize();
                attackRect.x = x;
            }
            case "down" -> {
                attackRect.y = y + gamePanel.getTileSize();
                attackRect.x = x;
            }
            case "left" -> {
                attackRect.x = x - gamePanel.getTileSize();
                attackRect.y = y;
            }
            case "right" -> {
                attackRect.x = x + gamePanel.getTileSize();
                attackRect.y = y;
            }
        }

        if (collisionHandler.enemyCollision(gamePanel.characters.troll1.x, gamePanel.characters.troll1.y, attackRect.x, attackRect.y, gamePanel.getTileSize())) {
            damageMonster();
        }

    }

    // toDo: Den Index des Monsters übergeben, welches getroffen wurde.
    private void damageMonster() {
        gamePanel.characters.troll1.life -= 1;
        System.out.println(" Troll Leben: " + gamePanel.characters.troll1.life);

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
                hit_left[i] = ImageIO.read(Objects.requireNonNull(Player.class.getResourceAsStream(path + "hit_left (" + (i + 1) + ").png")));
                hit_right[i] = ImageIO.read(Objects.requireNonNull(Player.class.getResourceAsStream(path + "hit_right (" + (i + 1) + ").png")));
            }

            // Diese Animation hat 4 Bilder
//            for (int i = 0; i < 4; i++) {
//                hit_left[i] = ImageIO.read(Objects.requireNonNull(Player.class.getResourceAsStream(path + "swing_left (" + (i + 1) + ").png")));
//                hit_right[i] = ImageIO.read(Objects.requireNonNull(Player.class.getResourceAsStream(path + "swing_right (" + (i + 1) + ").png")));
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawDamage(Graphics2D g) {
        g.setColor(Color.blue);
        g.drawRect(attackRect.x, attackRect.y, attackRect.width, attackRect.height);
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

        if (keyH.attacking) {
            if (Objects.equals(lastDirection, "left")) {
                directionHit = "hit_left";
            } else if (Objects.equals(lastDirection, "right")) {
                directionHit = "hit_right";
            } else {
                directionHit = "hit_right";
            }



        }

        if (!keyH.upPressed && !keyH.downPressed && !keyH.leftPressed && !keyH.rightPressed) {
            direction = "idle";

            return;
        }

        if (keyH.upPressed) {
            direction = "up";
            lastDirection = "up";

            if (collisionHandler.noColisionUp(this.x, this.y, other.x, other.y, speed + 2, gamePanel.getTileSize())) {
                // Wenn schräg gelaufen wird: die Geschwindigkeit verringern
                y -= (keyH.rightPressed || keyH.leftPressed) ? (int) (speed * 0.8) : speed;
            }

        } else if (keyH.downPressed) {
            direction = "down";
            lastDirection = "down";

            if (collisionHandler.noColisionDown(this.x, this.y, other.x, other.y, speed + 2, gamePanel.getTileSize())) {
                y += (keyH.rightPressed || keyH.leftPressed) ?
                        (int) (speed * 0.8) : speed;

            }
        }

        if (keyH.leftPressed) {
            direction = "left";
            lastDirection = "left";

            if (collisionHandler.noColisionLeft(this.x, this.y, other.x, other.y, speed + 2, gamePanel.getTileSize())) {
                x -= (keyH.upPressed || keyH.downPressed) ?
                        (int) (speed * 0.8) : speed;
            }

        } else if (keyH.rightPressed) {
            direction = "right";
            lastDirection = "right";

            if (collisionHandler.noColisionRight(this.x, this.y, other.x, other.y, speed + 2, gamePanel.getTileSize())) {
                x += (keyH.upPressed || keyH.downPressed) ?
                        (int) (speed * 0.8) : speed;
            }
        }

        // Check Event
        gamePanel.eventHandler.checkEvent(this, direction);

    }

    public int getWidth() {
        return gamePanel.getTileSize();
    }

    public int getHeight() {
        return gamePanel.getTileSize();
    }

    public void drawPlayer(Graphics2D g) {
        BufferedImage imagePlayer;

        if (keyH.attacking) {
            hitSpritCounter++;

            if (hitSpritCounter <= 4) {
                hitSpriteNum = 1;
            }
            if (hitSpritCounter > 4 && hitSpritCounter <= 8) {
                hitSpriteNum = 2;
            }
            if (hitSpritCounter > 8 && hitSpritCounter <= 12) {
                hitSpriteNum = 3;
            }
            if (hitSpritCounter > 12 && hitSpritCounter <= 16) {
                hitSpriteNum = 4;
                attacking();
            }
            if (hitSpritCounter > 16) {
                hitSpriteNum = 1;
                hitSpritCounter = 0;
                keyH.attacking = false;
            }
            imagePlayer = switch (directionHit) {
                case "hit_left" -> hit_left[hitSpriteNum];
                case "hit_right" -> hit_right[hitSpriteNum];
                default -> null;
            };
            // Andere Höhen und Breiten der Bilder
            if (imagePlayer != null) {
                g.drawImage(imagePlayer, getX() - 15, getY() - getHeight(), getWidth() + 30, getHeight() * 2, null);
            }



        } else {
            imagePlayer =
                    switch (direction) {
                        case "idle" -> idle[spriteNum];
                        case "up" -> up[spriteNum];
                        case "down" -> down[spriteNum];
                        case "left" -> left[spriteNum];
                        case "right" -> right[spriteNum];
//                        // toDO: Schlag fängt nicht bei Bild 1 an
//                        case "hit_left" -> hit_left[spriteNum];
//                        case "hit_right" -> hit_right[spriteNum];
                        default -> null;
                    };
            if (imagePlayer != null) {
                g.drawImage(imagePlayer, getX() -2, getY() - getHeight(), getWidth() + 4, getHeight() * 2, null);
            }
        }


    }

}

