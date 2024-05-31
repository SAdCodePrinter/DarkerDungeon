package MainGUI;

public class Main {

    public static GamePanel gamePanel;

    public static void main(String[] args) {
        Window window = new Window();
        gamePanel = new GamePanel();
        gamePanel.setupGame();


        window.setWindow(gamePanel);
    }
}
