package entity;

import MainGUI.CollisionHandler;
import MainGUI.GamePanel;
import MainGUI.KeyHandler;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Karaktere {

    private GamePanel gamePanel;
    public ArrayList<Player> players = new ArrayList<>();
    public ArrayList<Enemy_Troll> trolls = new ArrayList<>();
    public ArrayList<Enemy_Ghost> ghosts = new ArrayList<>();
    public KeyHandler kH1;
    public KeyHandler kH2;
    private boolean ghostSpawned = false;


    public Karaktere(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        // Spieler
        kH1 = new KeyHandler(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_P, KeyEvent.VK_R, gamePanel);
        kH2 = new KeyHandler(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_O, gamePanel);

        spawnPlayer(gamePanel.getTileSize() * 12, gamePanel.getTileSize() * 8, 7, kH1, "/players/player1/", "Spieler 1");
        spawnPlayer(gamePanel.getTileSize() * 7, gamePanel.getTileSize() * 7, 5, kH2, "/players/player2/", "Spieler 2");

        // Gegner
//        spawnTroll(1050, 500, 2, "/npc/troll1/");
        // spawnGhost(900, 400, 1, "/npc/ghost1/");

    }

    // toDo: Spielernamen integrieren
    public void spawnPlayer(int x, int y, int speed, KeyHandler kH, String imagePath, String name) {
        Player player = new Player(gamePanel, kH, imagePath);
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
