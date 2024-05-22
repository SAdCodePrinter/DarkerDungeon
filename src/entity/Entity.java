package entity;

import MainGUI.GamePanel;

import java.awt.image.BufferedImage;

public class Entity {
    public BufferedImage[] up = new BufferedImage[8];
    public BufferedImage[] down = new BufferedImage[8];
    public BufferedImage[] left = new BufferedImage[8];
    public BufferedImage[] right = new BufferedImage[8];
    public BufferedImage[] idle = new BufferedImage[8];

    public boolean collision = false;
    public int spriteCounter = 0;
    public int spriteNum = 1;

    // ToDo: Private machen und vielleicht abstract
    public int x, y;
    public int speed;
    public String direction;

    GamePanel gamePanel;

    public Entity(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
