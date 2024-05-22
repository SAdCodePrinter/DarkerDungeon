package MainGUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    private final int up, down, left, right;
    public boolean upPressed, downPressed, leftPressed, rightPressed;


    public KeyHandler(int up, int down, int left, int right) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
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
    }
}
