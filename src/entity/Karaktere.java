package entity;

import MainGUI.CollisionHandler;
import MainGUI.GamePanel;
import MainGUI.KeyHandler;

import java.awt.event.KeyEvent;

public class Karaktere {

    public Player player1;
    public Player player2;
    public Enemy_Troll troll1;
    public Enemy_Ghost ghost1;
    public KeyHandler kH1;
    public KeyHandler kH2;


    public Karaktere(GamePanel gamePanel) {

        // Spieler
        kH1 = new KeyHandler(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_P, KeyEvent.VK_R, gamePanel);
        kH2 = new KeyHandler(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_O, gamePanel);

        player1 = new Player(gamePanel, kH1, "/players/player1/");
        player2 = new Player(gamePanel, kH2, "/players/player2/");

        player1.setDefault(gamePanel.getTileSize() * 12, gamePanel.getTileSize() * 8, 8);
        player2.setDefault(gamePanel.getTileSize() * 7, gamePanel.getTileSize() * 7, 4);

        // Gegner
        troll1 = new Enemy_Troll(gamePanel, "/npc/troll1/");
        troll1.setDefault(1050, 500, 2);

        ghost1 = new Enemy_Ghost(gamePanel, "/npc/ghost1/");
        ghost1.setDefault(900, 500, 1);

    }

}
