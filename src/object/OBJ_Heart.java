package object;

import MainGUI.GamePanel;

import javax.imageio.ImageIO;
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

        } catch (IOException e){
            e.printStackTrace();

        }
    }
}


