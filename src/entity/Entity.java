package entity;

import java.awt.image.BufferedImage;

public class Entity {
    // ToDo: Auslagern
    public BufferedImage up1, up2, up3, up4, up5, up6, up7, up8;
    public BufferedImage down1, down2, down3, down4, down5, down6, down7, down8;
    public BufferedImage left1, left2, left3, left4, left5, left6, left7, left8;
    public BufferedImage right1, right2, right3, right4, right5, right6, right7, right8;
    public BufferedImage idle1, idle2, idle3, idle4, idle5, idle6;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    // ToDo: Private machen und vielleicht abstract
    public int x, y;
    public int speed;
    public String direction;

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
