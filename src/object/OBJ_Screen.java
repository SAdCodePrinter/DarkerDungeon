package object;

import MainGUI.GamePanel;
import MainGUI.uTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class OBJ_Screen extends ObjectHandler {
    GamePanel gamePanel;

    public OBJ_Screen(GamePanel gamePanel){
        this.gamePanel = gamePanel;


        name = "startScreen";
        try {
            image1 = ImageIO.read(getClass().getResourceAsStream("/objects/start.screen.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/pause.screen.png"));


            int scaledWidth = gamePanel.getTileSize() + 100;
            int scaledHeight = gamePanel.getTileSize() + 100; 
            image1 = uTool.scaleImage(image1, scaledWidth, scaledHeight);



        } catch (IOException e){
            e.printStackTrace();
        }
    }

}



