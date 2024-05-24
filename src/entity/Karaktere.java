package entity;

import MainGUI.CollisionHandler;
import MainGUI.GamePanel;
import MainGUI.KeyHandler;

import java.awt.event.KeyEvent;

public class Karaktere {

    public Player player1;
    public Player player2;
    public Enemy_Troll troll1;
    public KeyHandler kH1;
    public KeyHandler kH2;


    public Karaktere(GamePanel gamePanel) {

        // Spieler
        kH1 = new KeyHandler(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D);
        kH2 = new KeyHandler(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);


        player1 = new Player(gamePanel, kH1, "/players/player1/");
        player2 = new Player(gamePanel, kH2, "/players/player2/");



        player1.setDefault(100, 300, 5);
        player2.setDefault(1050, 500, 3);

        // Gegner
        troll1 = new Enemy_Troll(gamePanel, "/npc/troll1/");
        troll1.setDefault(1050, 500, 1);

    }

}
