package MainGUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gamePanel;
    private int up, down, left, right, pause, attack;
    public boolean upPressed, downPressed, leftPressed, rightPressed, attacking;

    public KeyHandler(int up, int down, int left, int right, int pause, int attack, GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.pause = pause;
        this.attack = attack;
    }

    public KeyHandler(int up, int down, int left, int right, int attack, GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.attack = attack;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();

        if (code == this.up) {
            upPressed = true;
        }
        if (code == this.down) {
            downPressed = true;
        }
        if (code == this.left) {
            leftPressed = true;
        }
        if (code == this.right) {
            rightPressed = true;
        }
        if (code == this.pause) {
            if (gamePanel.getGameState() == gamePanel.getStartState()) {
                gamePanel.setGameState(gamePanel.getPlayState());
                System.out.println("PLAY");
            } else if (gamePanel.getGameState() == gamePanel.getPlayState()) {
                gamePanel.setGameState(gamePanel.getPauseState());
                System.out.println("PAUSE");
            } else if (gamePanel.getGameState() == gamePanel.getPauseState()) {
                gamePanel.setGameState(gamePanel.getPlayState());
                System.out.println("PLAY");
            } else if (gamePanel.getGameState() == gamePanel.getEndState()) {
                gamePanel.setStart();
                System.out.println("START");

            }
        }
        if (code == this.attack) {
            attacking = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == this.up) {
            upPressed = false;
        }
        if (code == this.down) {
            downPressed = false;
        }
        if (code == this.left) {
            leftPressed = false;
        }
        if (code == this.right) {
            rightPressed = false;
        }
//        if (code == this.attack) {
//            attacking = false;
//        }
    }
}
