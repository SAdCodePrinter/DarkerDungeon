package MainGUI;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private int tileSize = 48;
    private int screenWidth = tileSize * 28;
    private int screenHeight = tileSize * 16;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    public int getTileSize() {
        return tileSize;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }
}

//Deine MUM
