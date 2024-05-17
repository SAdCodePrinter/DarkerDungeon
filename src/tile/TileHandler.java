package tile;

import MainGUI.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileHandler {

    GamePanel gamePanel;
    Tile[] tile;
    int mapTileNum[][];

    public TileHandler(GamePanel gamePanel){

        this.gamePanel = gamePanel;
        tile = new Tile [38];
        mapTileNum = new int[gamePanel.getScreenWith()][gamePanel.getScreenHeight()];
        getTileImage();
        loadMap();

    }
    private void getTileImage(){
        //Instanziieren der Tiles:
        try {

        tile [0] = new Tile ();
        tile [0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/ground_1.png"));

        tile [1] = new Tile ();
        tile [1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/ground_2.png"));

        tile [2] = new Tile ();
        tile [2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/ground_3.png"));

        tile [3] = new Tile ();
        tile [3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/ground_4.png"));

        tile [4] = new Tile ();
        tile [4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_south_1.png"));
        tile [4].collision = true;

    } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/maps/SampleDungeon.rtf");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int row = 0;
            int col = 0;

            // Lesen des Text Files:
            while (col < gamePanel.getScreenWith() && row < gamePanel.getScreenHeight()) {

                String line = br.readLine();

                while (col < gamePanel.getScreenWith()){

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gamePanel.getScreenWith()) {
                    col = 0;
                    row ++;
                }
            }

            // Schließen des BufferedReader
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void drawBackGroundTiles(Graphics2D g3){
        //g3.drawImage(tile[0].image, 0,0, gamePanel.getTileSize(), gamePanel.getTileSize(), null);

        int mapRow = 0; //Höhe
        int mapCol = 0; //Breite
        int x = 0;
        int y = 0;

        while (mapCol < gamePanel.getScreenWith() && mapRow < gamePanel.getScreenHeight()) {

            int tileNum = mapTileNum[mapCol][mapRow];

            g3.drawImage(tile[tileNum].image,x ,y , gamePanel.getTileSize(), gamePanel.getTileSize(), null);
            mapCol++;
            x += gamePanel.getTileSize();

            if(mapCol == gamePanel.getScreenWith()){
                mapCol = 0;
                x = 0;
                mapRow++;
                y += gamePanel.getTileSize();
            }
        }
        System.out.println("Image drawed");
    }


    public void drawForGroundTiles(Graphics2D g4){
        g4.drawImage(tile[4].image, 64,64, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
    }
}
