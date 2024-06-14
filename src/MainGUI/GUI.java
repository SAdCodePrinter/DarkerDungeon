package MainGUI;

import entity.Enemy_Ghost;
import entity.Enemy_Troll;
import entity.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GUI extends JPanel {
    private GamePanel gamePanel;
    public boolean drawTrollPath = true;
    public boolean drawHitbox = true;
    public boolean drawDamage = true;

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
            for (Enemy_Troll troll : gamePanel.characters.trolls) {
                for (int i = 0; i < troll.pathFinder.pathList.size(); i++) {
                    int worldX = troll.pathFinder.pathList.get(i).col * gamePanel.getTileSize();
                    int worldY = troll.pathFinder.pathList.get(i).row * gamePanel.getTileSize();

                    g.fillRect(worldX, worldY, gamePanel.getTileSize(), gamePanel.getTileSize());
                }
            }

        }
    }

    public void drawAllGhosts(Graphics2D g) {
        for (Enemy_Ghost ghost : gamePanel.characters.ghosts) {
            BufferedImage imageGhost = ghost.idle[ghost.spriteNum];

            if (imageGhost != null) {
                g.drawImage(imageGhost, ghost.x - 20, ghost.y - gamePanel.getTileSize() / 2, gamePanel.getTileSize() * 2, gamePanel.getTileSize() * 2, null);
            }
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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g1 = (Graphics2D) g;

        backGroundTiles(g1, true);

//        gamePanel.eventHandler.drawEvent(g1);
        drawAllPlayer(g1);

        drawAllGhosts(g1);

        drawAllTrolls(g1);

        backGroundTiles(g1, false);

        // toDo: wofür ist die Klasse UI?
        UI ui = new UI(gamePanel);
        ui.draw(g1);

        g1.dispose();

    }

    private void drawAllTrolls(Graphics2D g1) {
        for (Enemy_Troll troll : gamePanel.characters.trolls) {
            troll.drawTroll(g1);
            if (drawHitbox) {
                troll.drawHitbox(g1);
            }
            if (drawDamage) {
                troll.drawDamage(g1);
            }
        }
    }

    private void drawAllPlayer(Graphics2D g1) {
        for (Player player : gamePanel.characters.players) {
            player.drawPlayer(g1);
            if (drawHitbox) {
                player.drawHitbox(g1);
            }
            if (drawDamage) {
                player.drawDamage(g1);
            }
        }
    }
}
