package MainGUI;

import java.awt.*;

public class Draw {
    private GamePanel gamePanel;
    public boolean drawTrollPath = true;

    public Draw(GamePanel gamePanel) {
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
}
