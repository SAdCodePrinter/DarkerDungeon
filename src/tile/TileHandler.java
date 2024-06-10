package tile;

import MainGUI.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TileHandler {

    GamePanel gamePanel;
    public Tile[] tile;
    public int[][] mapTileNum;
    public boolean drawTrollPath = true;

    public TileHandler(GamePanel gamePanel) {

        this.gamePanel = gamePanel;
        tile = new Tile[37];
        mapTileNum = new int[gamePanel.getScreenCol()][gamePanel.getScreenRow()];

        getTileImage();
        loadMap();

    }

    private void getTileImage() {
        //Instanziieren der Tiles:
        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/ground_1.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/ground_2.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/ground_3.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/ground_4.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_ground_shadow_1.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_north_bottom_1.png"));
            //tile [5].collision = true;

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_north_bottom_2.png"));
            //tile [6].collision = true;

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_north_middle_1.png"));
            tile[7].collision = true;

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_north_middle_2.png"));
            tile[8].collision = true;

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_north_top_1.png"));
            //tile[9].collision = true;

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_north_top_2.png"));
            //tile[10].collision = true;

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_north_west_corner.png"));
            tile[11].collision = true;

            tile[12] = new Tile();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_west_1.png"));
            tile[12].collision = true;

            tile[13] = new Tile();
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_west_2.png"));
            tile[13].collision = true;

            tile[14] = new Tile();
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_south_west_corner.png"));
            tile[14].collision = true;

            tile[15] = new Tile();
            tile[15].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_north_east_corner.png"));
            tile[15].collision = true;

            tile[16] = new Tile();
            tile[16].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_east_1.png"));
            tile[16].collision = true;

            tile[17] = new Tile();
            tile[17].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_east_2.png"));
            tile[17].collision = true;

            tile[18] = new Tile();
            tile[18].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_south_east_corner.png"));
            tile[18].collision = true;

            tile[19] = new Tile();
            tile[19].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_south_1.png"));
            //tile[19].collision = true;

            tile[20] = new Tile();
            tile[20].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_south_2.png"));
            //tile[20].collision = true;

            tile[21] = new Tile();
            tile[21].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_ground_shadow_2.png"));

            tile[22] = new Tile();
            tile[22].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_ground_shadow_3.png"));

            tile[23] = new Tile();
            tile[23].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_connection_east_south.png"));
            //tile[23].collision = true;

            tile[24] = new Tile();
            tile[24].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_edge_left_bottom.png"));
            //tile [24].collision = true;

            tile[25] = new Tile();
            tile[25].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_edge_left_middle.png"));
            tile[25].collision = true;

            tile[26] = new Tile();
            tile[26].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_edge_left_top.png"));
            tile[26].collision = true;

            tile[27] = new Tile();
            tile[27].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_edge_right_bottom.png"));
            //tile [27].collision = true;

            tile[28] = new Tile();
            tile[28].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_edge_right_middle.png"));
            tile[28].collision = true;

            tile[29] = new Tile();
            tile[29].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_edge_right_top.png"));
//            tile[29].collision = true;

            tile[30] = new Tile();
            tile[30].image = ImageIO.read(getClass().getResourceAsStream("/tiles/blanc.png"));
            tile[30].collision = true;

            tile[31] = new Tile();
            tile[31].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_connection_west_south.png"));
            //tile[31].collision = true;

            tile[32] = new Tile();
            tile[32].image = ImageIO.read(getClass().getResourceAsStream("/tiles/object_door_top.png"));
            tile[32].collision = true;

            tile[33] = new Tile();
            tile[33].image = ImageIO.read(getClass().getResourceAsStream("/tiles/object_door_wall_east_bottom.png"));
            //tile [34].collision = true;

            tile[34] = new Tile();
            tile[34].image = ImageIO.read(getClass().getResourceAsStream("/tiles/object_door_wall_east_top.png"));
            tile[34].collision = true;

            tile[35] = new Tile();
            tile[35].image = ImageIO.read(getClass().getResourceAsStream("/tiles/object_door_wall_west_bottom.png"));
            //tile [36].collision = true;

            tile[36] = new Tile();
            tile[36].image = ImageIO.read(getClass().getResourceAsStream("/tiles/object_door_wall_west_top.png"));
            tile[36].collision = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/maps/SampleDungeon.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int row = 0;
            int col = 0;

            // Lesen des Text Files:
            while (col < gamePanel.getScreenCol() && row < gamePanel.getScreenRow()) {

                String line = br.readLine();

                while (col < gamePanel.getScreenCol()) {

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col <= gamePanel.getScreenCol()) {
                    col = 0;
                    row++;
                }
            }

            // Schließen des BufferedReader
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Ausgelagert in Draw klasse
     * @param g
     * @param isBackground
     */
//    public void drawBackGroundTiles(Graphics2D g, boolean isBackground) {
//        int mapRow = 0;
//        int mapCol = 0;
//        int x = 0;
//        int y = 0;
//
//        while (mapCol < gamePanel.getScreenCol() && mapRow < gamePanel.getScreenRow()) {
//
//            int tileNum = mapTileNum[mapCol][mapRow];
//            if (isBackground)
//                g.drawImage(tile[tileNum].image, x, y, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
//            if ((tileNum == 19 || tileNum == 20 || tileNum == 23 || tileNum == 31 || tileNum == 26 || tileNum == 29 || tileNum == 9 || tileNum == 10 || tileNum == 30) &&
//                    !isBackground)
//                g.drawImage(tile[tileNum].image, x, y, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
//            mapCol++;
//            x += gamePanel.getTileSize();
//
//            if (mapCol == gamePanel.getScreenCol()) {
//                mapCol = 0;
//                x = 0;
//                mapRow++;
//                y += gamePanel.getTileSize();
//            }
//        }
//
//
//        if (drawTrollPath) {
//            g.setColor(new Color(255, 0, 0, 70));
//            for (int i = 0; i < gamePanel.characters.troll1.pathFinder.pathList.size(); i++) {
//                int worldX = gamePanel.characters.troll1.pathFinder.pathList.get(i).col * gamePanel.getTileSize();
//                int worldY = gamePanel.characters.troll1.pathFinder.pathList.get(i).row * gamePanel.getTileSize();
//
//                g.fillRect(worldX, worldY, gamePanel.getTileSize(), gamePanel.getTileSize());
//            }
//        }
//    }
}
