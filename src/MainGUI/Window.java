package MainGUI;

import javax.swing.*;

public class Window extends JFrame {
    public void setWindow(GUI gui) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("DarkerDungeon");

        window.add(gui);

        window.pack();

        window.setLocationRelativeTo(null);

        window.setVisible(true);
    }
}
