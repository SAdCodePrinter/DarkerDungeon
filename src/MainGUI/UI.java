package MainGUI;

import java.awt.*;

public class UI {

    GamePanel gamePanel;
    Font arial_40;
    Graphics2D g1;

    public UI(GamePanel gamePanel) {

        this.gamePanel = gamePanel;

        new Font("Arial", Font.PLAIN, 40);
    }

    public void draw(Graphics2D g1) {
        this.g1 = g1;

        g1.setFont(arial_40);
        g1.setColor(Color.white);

        if (gamePanel.getGameState() == gamePanel.getPlayState()) {

        }

        if (gamePanel.getGameState() == gamePanel.getPauseState()) {
            String text = "PAUSE";
            int length = (int) g1.getFontMetrics().getStringBounds(text, g1).getWidth();
            int x = gamePanel.getScreenWidth() / 2 - length / 2;
            int y = gamePanel.getScreenHeight() / 2;

            g1.drawString(text, x, y);

        }

        g1.setFont(arial_40);
        g1.setColor(Color.BLUE);
        g1.drawString("SYYYMON", 50, 50);


    }

    private void drawPauseScreen() {
        String text = "PAUSE";
        int length = (int) g1.getFontMetrics().getStringBounds(text, g1).getWidth();
        int x = gamePanel.getScreenWidth() / 2 - length / 2;
        int y = gamePanel.getScreenHeight() / 2;


    }
}


