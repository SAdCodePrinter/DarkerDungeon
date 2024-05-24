package MainGUI;

import java.awt.*;

public class UI {

    GamePanel gamePanel;
    Font arial_40;

    public UI(GamePanel gamePanel) {

        this.gamePanel = gamePanel;

        new Font ("Arial", Font.PLAIN, 40);
    }
    public void draw (Graphics2D g1){
        g1.setFont(arial_40);
        g1.setColor(Color.BLUE);
        g1.drawString("SYYYMON", 50, 50);
    }
}


