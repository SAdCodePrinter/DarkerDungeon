package object;

import MainGUI.GamePanel;
import MainGUI.uTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class OBJ_Heart extends ObjectHandler {
    GamePanel gamePanel;

    public OBJ_Heart(GamePanel gamePanel){
        this.gamePanel = gamePanel;

        name = "Heart";
        try {
            image1 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_blanc.png"));

            int scaledWidth = gamePanel.getTileSize() + 100; // Adjust these values as needed
            int scaledHeight = gamePanel.getTileSize() + 100; // Adjust these values as needed
            image1 = uTool.scaleImage(image1, scaledWidth, scaledHeight);
            image2 = uTool.scaleImage(image2, scaledWidth, scaledHeight);
            image3 = uTool.scaleImage(image3, scaledWidth, scaledHeight);


        } catch (IOException e){
            e.printStackTrace();
        }
    }
}



