package entity;

import java.awt.image.BufferedImage;

public class Entity {
    // ToDo: Auslagern
    public BufferedImage up1, up2, up3, up4, up5, up6, up7, up8;
    public BufferedImage down1, down2, down3, down4, down5, down6, down7, down8;
    public BufferedImage left1, left2, left3, left4, left5, left6, left7, left8;
    public BufferedImage right1, right2, right3, right4, right5, right6, right7, right8;
    public BufferedImage idle1, idle2, idle3, idle4, idle5, idle6;

    public BufferedImage up10, up20, up30, up40, up50, up60, up70, up80;
    public BufferedImage down10, down20, down30, down40, down50, down60, down70, down80;
    public BufferedImage left10, left20, left30, left40, left50, left60, left70, left80;
    public BufferedImage right10, right20, right30, right40, right50, right60, right70, right80;
    public BufferedImage idle10, idle20, idle30, idle40, idle50, idle60;

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
