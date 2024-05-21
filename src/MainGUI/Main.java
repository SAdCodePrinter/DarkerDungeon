package MainGUI;

public class Main {

    public static GamePanel gamePanel = new GamePanel();

    public static void main(String[] args) {
        Window window = new Window();
        window.setWindow(gamePanel);

        gamePanel.startGameThread();
    }
}
