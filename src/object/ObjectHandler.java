package object;
import MainGUI.GamePanel;

import javax.swing.text.Utilities;
import java.awt.*;
import java.awt.image.BufferedImage;


public class ObjectHandler {
    public BufferedImage image1, image2, image3;
    public String name;
    public boolean collision = false;

    public int worldX, worldY;
    public void draw (Graphics2D g2, GamePanel gp){

        int screenX = worldX - 36 + 36;
        int screenY = worldY - 36 + 36;

        g2.drawImage(image1,screenX ,screenY, 36, 36, null);
    }
}

