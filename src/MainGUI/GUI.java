package MainGUI;

import entity.Enemy_Ghost;
import entity.Enemy_Troll;
import entity.Player;
import object.OBJ_Heart;
import object.ObjectHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class GUI extends JPanel {
    private GamePanel gamePanel;
    public boolean drawTrollPath = true;
    public boolean drawHitbox = true;
    public boolean drawDamage = true;
    BufferedImage heart_full, heart_half, heart_blanc;


    public GUI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        //HUD
        ObjectHandler heart = new OBJ_Heart(gamePanel);
        heart_full = heart.image1;
        heart_half = heart.image2;
        heart_blanc = heart.image3;

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

    public void setupGame(KeyHandler kH1, KeyHandler kH2) {
        this.setPreferredSize(new Dimension(gamePanel.getScreenWidth(), gamePanel.getScreenHeight()));

        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(kH1);
        this.addKeyListener(kH2);
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

        drawGUI(g1);

        g1.dispose();

    }

    private void drawGUI(Graphics2D g1) {
        Font font = new Font("Arial", Font.PLAIN, 40);
        g1.setFont(font);
        g1.setColor(Color.white);

        if (gamePanel.getGameState() == gamePanel.getPlayState()) {
            drawPlayerLife(g1);
        }

        if (gamePanel.getGameState() == gamePanel.getPauseState()) {
            String text = "PAUSE";
            int length = (int) g1.getFontMetrics().getStringBounds(text, g1).getWidth();
            int x = gamePanel.getScreenWidth() / 2 - length / 2;
            int y = gamePanel.getScreenHeight() / 2;

            g1.drawString(text, x, y);
        }

        if (gamePanel.getGameState() == gamePanel.getStartState()) {
            drawStartScreen(g1);
        }

        if (gamePanel.getGameState() == gamePanel.getEndState()) {

            if (gamePanel.getGameState() == gamePanel.getEndState()) {
                g1.setColor(Color.BLACK);
                g1.fillRect(0, 0, gamePanel.getScreenWidth(), gamePanel.getScreenHeight());

                g1.setFont(new Font("Chiller", Font.BOLD, 60));
                g1.setColor(Color.RED);

                String text = "YOU DIED";
                FontMetrics fm = g1.getFontMetrics();
                int length = fm.stringWidth(text);
                int x = (gamePanel.getScreenWidth() - length) / 2;
                int y = gamePanel.getScreenHeight() / 2 + fm.getHeight() / 4;

                g1.drawString(text, x, y);


                g1.setFont(new Font("Chiller", Font.PLAIN, 30));
                g1.setColor(Color.WHITE);

                String scoreText = "Score: " + gamePanel.getScore() + " Sekunden überlebt";
                int scoreLength = fm.stringWidth(scoreText);
                int scoreX = (gamePanel.getScreenWidth() - scoreLength) / 2;
                int scoreY = y + fm.getHeight();

                g1.drawString(scoreText, scoreX, scoreY);


                g1.setFont(new Font("Chiller", Font.PLAIN, 30));
                g1.setColor(Color.WHITE);

                String levelText = "Level: " + gamePanel.getLevel()+ " Erreicht";
                int levelLength = fm.stringWidth(scoreText);
                int levelX = (gamePanel.getScreenWidth() - levelLength) / 2;
                int levelY = y + fm.getHeight() - 25;

                g1.drawString(levelText, levelX, levelY);
            }


        }
    }

    private void drawEndScreen(Graphics2D g1) {
        g1.drawImage(heart_full, gamePanel.getTileSize(), gamePanel.getTileSize(), gamePanel.getScreenWidth(), gamePanel.getScreenHeight(), null);
    }

    private void drawStartScreen(Graphics2D g1) {
        g1.drawImage(heart_full, gamePanel.getTileSize(), gamePanel.getTileSize(), gamePanel.getScreenWidth(), gamePanel.getScreenHeight(), null);
    }

    private void drawPlayerLife(Graphics2D g1) {
        int x = gamePanel.getTileSize();
        int y = gamePanel.getTileSize() * 16;
        int spaceBetweenHearts = 20; // Abstand zwischen den Herzen

        // MaxLife
        for (int i = 0; i < gamePanel.characters.players.get(0).maxLife / 2; i++) {
            g1.drawImage(heart_blanc, x, y, null);
            x += gamePanel.getTileSize() + spaceBetweenHearts;
        }

        x = gamePanel.getTileSize();
        y = gamePanel.getTileSize() * 16;

        // CurrentLife
        for (int i = 0; i < gamePanel.characters.players.get(0).life; ) {
            g1.drawImage(heart_half, x, y, null);
            i++;
            if (i < gamePanel.characters.players.get(0).life) {
                g1.drawImage(heart_full, x, y, null);
                i++;
            }
            x += gamePanel.getTileSize() + spaceBetweenHearts;
        }
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
