package entity;

import MainGUI.GamePanel;
import MainGUI.KeyHandler;

import java.util.ArrayList;

public class Spawn {
    private GamePanel gamePanel;
    public ArrayList<Player> players = new ArrayList<>();
    public ArrayList<Enemy_Troll> trolls = new ArrayList<>();
    public ArrayList<Enemy_Ghost> ghosts = new ArrayList<>();


    public Spawn(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

    }

    public void spawnPlayer(int x, int y, int speed, KeyHandler kH, String imagePath, String name) {
        Player player = new Player(gamePanel, kH, imagePath, name);
        player.setDefault(x, y, speed);

        players.add(player);
    }

    public void spawnTroll(int x, int y, int speed, String imagePath) {
        Enemy_Troll troll = new Enemy_Troll(gamePanel, imagePath);
        troll.setDefault(x, y, speed);

        trolls.add(troll);
    }

    public void spawnGhost(int x, int y, int speed, String imagePath) {
        Enemy_Ghost ghost = new Enemy_Ghost(gamePanel, imagePath);
        ghost.setDefault(x, y, speed);

        ghosts.add(ghost);
    }

}
