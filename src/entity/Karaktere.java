package entity;

import MainGUI.GamePanel;
import MainGUI.KeyHandler;

import java.awt.event.KeyEvent;

public class Karaktere {

    public Player player1;
    public Player player2;
    public KeyHandler kH1;
    public KeyHandler kH2;


    public Karaktere(GamePanel gamePanel) {

        kH1 = new KeyHandler(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D);
        kH2 = new KeyHandler(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);

        player1 = new Player(gamePanel, kH1);
        player2 = new Player(gamePanel, kH2);

        player1.setDefault(100, 100, 5);
        player2.setDefault(200, 200, 5);


    }


}
