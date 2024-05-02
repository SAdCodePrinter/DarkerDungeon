package entity;

import MainGUI.GamePanel;
import MainGUI.KeyHandler2;

import java.awt.*;

public class Player2 extends Entity{

    GamePanel gamePanel;
    KeyHandler2 keyH2;



    public Player2 (GamePanel gamePanel, KeyHandler2 keyH2){

        this.gamePanel = gamePanel;
        this.keyH2 = keyH2;
        setDefault();
    }
    public void setDefault(){
        x = 200;
        y = 200;
        speed = 5;
        direction = "idle";
    }
    public void update(){
        //TODO: Schräg laufen!
        //player.update();

        if (keyH2.upPressed){
            y -= speed;
            direction = "up";

        }else if (keyH2.downPressed){
            y += speed;
            direction = "down";

        } else if (keyH2.leftPressed) {
            x -= speed;
            direction = "left";

        } else if (keyH2.rightPressed) {
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


    public void draw (Graphics2D g3){
        g3.setColor(Color.red);

        g3.fillRect(x, y, gamePanel.getTileSize(), gamePanel.getTileSize());
    }
}

