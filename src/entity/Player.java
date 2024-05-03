package entity;

import MainGUI.CollisionHandler;
import MainGUI.GamePanel;
import MainGUI.KeyHandler;

import java.awt.*;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyH;

    public Player(GamePanel gamePanel, KeyHandler keyH) {
        this.gamePanel = gamePanel;
        this.keyH = keyH;

    }

    public void setDefault(int xKoord, int yKoord, int defineSpeed) {
        setX(xKoord);
        setY(yKoord);
        setSpeed(defineSpeed);
        setDirection("idle");
    }

    public void collisionAndUpdate(Player other) {
        //TODO: Schräg laufen!
        //player.update();

        if (keyH.upPressed && !CollisionHandler.collisionNextStep("up", this, other)) {
            y -= speed;

        } else if (keyH.downPressed) {
            y += speed;
            direction = "down";

        } else if (keyH.leftPressed) {
            x -= speed;
            direction = "left";

        } else if (keyH.rightPressed) {
            x += speed;
            direction = "right";
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
     *
     * @param obj
     */
    public void draw(Graphics2D obj, Color color) {
        obj.setColor(color);

        obj.fillRect(x, y, gamePanel.getTileSize(), gamePanel.getTileSize());

    }
}

