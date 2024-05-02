package entity;

import MainGUI.GamePanel;
import MainGUI.KeyHandler;

import java.awt.*;

public class Player extends Entity{

    GamePanel gamePanel;
    KeyHandler keyH;



    public Player (GamePanel gamePanel, KeyHandler keyH){

        this.gamePanel = gamePanel;
        this.keyH = keyH;
        setDefault();
    }
    public void setDefault(){
        x = 100;
        y = 100;
        speed = 5;
        direction = "idle";
    }
    public void update(){
        //TODO: Schräg laufen!
        //player.update();

        if (keyH.upPressed){
            y -= speed;
            direction = "up";

        }else if (keyH.downPressed){
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

    public void draw (Graphics2D g2){
        g2.setColor(Color.white);

        g2.fillRect(x, y, gamePanel.getTileSize(), gamePanel.getTileSize());
    }
}

