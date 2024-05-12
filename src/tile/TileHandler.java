package tile;

import MainGUI.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileHandler {

    GamePanel gamePanel;
    Tile[] tile;

    public TileHandler(GamePanel gamePanel){

        this.gamePanel = gamePanel;
        tile = new Tile [38];
        getTileImage();
    }
    public void getTileImage(){
        //Instanzieren der Tiles:
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
        g3.drawImage(tile[0].image, 0,0, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
    }
    public void drawForGroundTiles(Graphics2D g4){
        g4.drawImage(tile[4].image, 64,64, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
    }
}
