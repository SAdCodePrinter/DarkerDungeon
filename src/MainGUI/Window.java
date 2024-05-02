package MainGUI;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public void setWindow(GamePanel gamePanel) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("DarkerDungeon");

        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);


        window.setVisible(true);
    }
}
