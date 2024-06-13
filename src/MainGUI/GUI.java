package MainGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GUI extends JPanel {
    private GamePanel gamePanel;
    public boolean drawTrollPath = true;

    public GUI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    public void backGroundTiles(Graphics2D g, boolean isBackground) {
        int mapRow = 0;
        int mapCol = 0;
        int x = 0;
        int y = 0;

        while (mapCol < gamePanel.getScreenCol() && mapRow < gamePanel.getScreenRow()) {

            int tileNum = gamePanel.tileH.mapTileNum[mapCol][mapRow];
            if (isBackground)
                g.drawImage(gamePanel.tileH.tile[tileNum].image, x, y, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
            if ((tileNum == 19 || tileNum == 20 || tileNum == 23 || tileNum == 31 || tileNum == 26 || tileNum == 29 || tileNum == 9 || tileNum == 10 || tileNum == 30) &&
                    !isBackground)
                g.drawImage(gamePanel.tileH.tile[tileNum].image, x, y, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
            mapCol++;
            x += gamePanel.getTileSize();

            if (mapCol == gamePanel.getScreenCol()) {
                mapCol = 0;
                x = 0;
                mapRow++;
                y += gamePanel.getTileSize();
            }
        }

        if (drawTrollPath) {
            g.setColor(new Color(255, 0, 0, 70));
            for (int i = 0; i < gamePanel.characters.troll1.pathFinder.pathList.size(); i++) {
                int worldX = gamePanel.characters.troll1.pathFinder.pathList.get(i).col * gamePanel.getTileSize();
                int worldY = gamePanel.characters.troll1.pathFinder.pathList.get(i).row * gamePanel.getTileSize();

                g.fillRect(worldX, worldY, gamePanel.getTileSize(), gamePanel.getTileSize());
            }
        }
    }
    public void drawGhost(Graphics2D g) {
        BufferedImage imageGhost = gamePanel.characters.ghost1.idle[gamePanel.characters.ghost1.spriteNum];

        if (imageGhost != null) {
            g.drawImage(imageGhost, gamePanel.characters.ghost1.x - 20, gamePanel.characters.ghost1.y - gamePanel.getTileSize() / 2, gamePanel.getTileSize() * 2, gamePanel.getTileSize() * 2, null);
        }
    }
    public void drawPlayer(Graphics2D g, BufferedImage imagePlayer, int x, int y, int w, int h) {
        g.drawImage(imagePlayer, x, y, w, h, null);
    }
    public void setupGame() {
        this.setPreferredSize(new Dimension(gamePanel.getScreenWidth(), gamePanel.getScreenHeight()));

        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(gamePanel.characters.kH1);
        this.addKeyListener(gamePanel.characters.kH2);
        this.setFocusable(true);
    }
    public boolean drawTroll,drawPlayer1, drawPlayer2, drawGhost;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g1 = (Graphics2D) g;

        backGroundTiles(g1, true);

        gamePanel.characters.players.get(0).drawPlayer(g1);
        gamePanel.characters.players.get(0).drawDamage(g1);

        gamePanel.characters.players.get(1).drawPlayer(g1);
        gamePanel.characters.players.get(1).drawHitbox(g1);
        gamePanel.characters.players.get(1).drawDamage(g1);

        gamePanel.eventHandler.drawEvent(g1);

        if (drawGhost) {
            drawGhost(g1);
        } else {
            gamePanel.characters.troll1.drawTroll(g1);
            gamePanel.characters.troll1.drawDamage(g1);
        }

        backGroundTiles(g1, false);

        // toDo: wofür ist die Klasse UI?
        UI ui = new UI(gamePanel);
        ui.draw(g1);

        g1.dispose();

    }
}
