package entity;

import MainGUI.CollisionHandler;
import MainGUI.GamePanel;

import java.awt.image.BufferedImage;

public class Entity {
    GamePanel gamePanel;
    public Entity(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }


    public BufferedImage[] up = new BufferedImage[8];
    public BufferedImage[] down = new BufferedImage[8];
    public BufferedImage[] left = new BufferedImage[8];
    public BufferedImage[] right = new BufferedImage[8];
    public BufferedImage[] idle = new BufferedImage[8];

    public boolean collisionOn = false;
    public int spriteCounter = 0;
    public int spriteNum = 1;


    public void setAI (){

    }

    //TODO: Collision mit Player und Tiles callen???
    // Glaube Tiles Collision muss Entity übergeben bekommen
    public void update (){
        setAI();

        if (!collisionOn){
            switch (direction){
                case "up": y -= speed; break;
                case "down": y += speed; break;
                case "left": x -= speed; break;
                case "right": x += speed; break;
            }
        }
    }

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
