package entity;

import MainGUI.CollisionHandler;
import MainGUI.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Enemy_Troll extends Entity {

    CollisionHandler collisionHandler;

    public Enemy_Troll(GamePanel gamePanel, String imagePath) {
        super(gamePanel);

        collisionHandler = new CollisionHandler(gamePanel);
        getTrollImage(imagePath);
    }

    public void setDefault(int xKoord, int yKoord, int defineSpeed) {
        setX(xKoord);
        setY(yKoord);
        setSpeed(defineSpeed);
        setDirection("down");
    }

    public void getTrollImage(String path) {
        try {
            for (int i = 0; i < 8; i++) {
                //up[i] = ImageIO.read(Objects.requireNonNull(Player.class.getResourceAsStream(path + "up (" + (i + 1) + ").png")));
                down[i] = ImageIO.read(Objects.requireNonNull(Player.class.getResourceAsStream(path + "down (" + (i + 1) + ").png")));
                left[i] = ImageIO.read(Objects.requireNonNull(Player.class.getResourceAsStream(path + "left (" + (i + 1) + ").png")));
                right[i] = ImageIO.read(Objects.requireNonNull(Player.class.getResourceAsStream(path + "right (" + (i + 1) + ").png")));
                //idle[i] = ImageIO.read(Objects.requireNonNull(Player.class.getResourceAsStream(path + "idle (" + (i + 1) + ").png")));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawHitbox(Graphics2D g) {
        g.setColor(Color.red);
        g.drawRect(x, y, 36, 36);
    }

    private boolean directionSet = false;

    public void move(Player player1, Player player2) {
        spriteCounter(8);

        if (!directionSet) {
            if (!collisionHandler.noColisionDown(this.x, this.y, player1.x, player1.y, speed, 36) &&
                    !collisionHandler.noColisionDown(this.x, this.y, player2.x, player2.y, speed, 36)) {
                direction = "up";
                directionSet = true;
            }

        } else if (!collisionHandler.noColisionUp(this.x, this.y, player1.x, player1.y, speed, 36) &&
                !collisionHandler.noColisionUp(this.x, this.y, player2.x, player2.y, speed, 36)) {
            direction = "down";
            directionSet = false;
        }


        switch (direction) {
            case "up":
                y -= speed;
                break;
            case "down":
                y += speed;
                break;
            case "left":
                x -= speed;
                break;
            case "right":
                x += speed;
                break;
        }
//
//        if(i<=25) {
//            direction = "up";
//        }
//        if (i > 25 && i <= 50) {
//            direction = "down";
//        }
//        if (i > 50 && i <= 75) {
//            direction = "left";
//        }
//        if(i > 75 && i <= 100){
//            direction = "right";
//        }
    }

    public void drawTroll(Graphics2D g) {
        BufferedImage imageTroll =
                switch (direction) {
                    //case "idle" -> idle[spriteNum];
                    case "up" -> down[spriteNum];
                    case "down" -> down[spriteNum];
                    case "left" -> left[spriteNum];
                    case "right" -> right[spriteNum];
                    default -> null;
                };

        if (imageTroll != null) {
            g.drawImage(imageTroll, getX() - 32, getY() - 36, gamePanel.getTileSize() * 3, gamePanel.getTileSize() * 3, null);
        }
    }

}
