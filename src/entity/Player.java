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

    /**
     * Updatet die Positionskoordinaten, wenn keine Kollision entsteht
     *
     * @param other ist das Element bei dem geschaut wird, ob es eine Kollision mit diesem geben wird
     */
    public void move(Player other) {
        //TODO: Schräg laufen!

        // toDo: evtl. die Kollisionsabfrage im GamPanel machen, und in dieser Methode nur die Koordinaten updaten
        //  dann aber die Richtung in die der Player geht vor dem Methodenaufruf definieren

        if (keyH.upPressed && CollisionHandler.noCollisionNextStep("up", this, other, speed)) {
            y -= speed;

        } else if (keyH.downPressed && CollisionHandler.noCollisionNextStep("down", this, other, speed)) {
            y += speed;

        } else if (keyH.leftPressed && CollisionHandler.noCollisionNextStep("left", this, other, speed)) {
            x -= speed;

        } else if (keyH.rightPressed && CollisionHandler.noCollisionNextStep("right", this, other, speed)) {
            x += speed;
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
     */
    public void draw(Graphics2D obj, Color color) {
        obj.setColor(color);

        obj.fillRect(x, y, gamePanel.getTileSize(), gamePanel.getTileSize());

    }
}

