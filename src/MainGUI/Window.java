package MainGUI;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private Container container;
    GridBagConstraints c = new GridBagConstraints();




    public void setWindow() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("DarkerDungeon");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);


        window.setVisible(true);
    }
}
