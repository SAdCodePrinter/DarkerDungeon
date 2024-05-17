package tile;

import MainGUI.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;

public class TileHandler {

    GamePanel gamePanel;
    Tile[] tile;

    public TileHandler(GamePanel gamePanel){

        this.gamePanel = gamePanel;
        tile = new Tile [38];
        getTileImage();

        //BufferedReader
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
    public void drawBackGroundTiles(Graphics2D g3){
        //g3.drawImage(tile[0].image, 0,0, gamePanel.getTileSize(), gamePanel.getTileSize(), null);

        int mapRow = 0; //Breite
        int mapCol = 0; //Höhe
        int x = 0;
        int y = 0;

        while (mapCol < gamePanel.getScreenHeight() && mapRow < gamePanel.getScreenWith()) {
            g3.drawImage(tile[0].image,x ,y , gamePanel.getTileSize(), gamePanel.getTileSize(), null);
            mapCol++;
            x += gamePanel.getTileSize();

            if(mapCol == gamePanel.getScreenHeight()){
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
