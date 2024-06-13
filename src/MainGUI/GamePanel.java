package MainGUI;

import entity.Karaktere;
import tile.TileHandler;

import javax.swing.*;

public class GamePanel {
    public Karaktere characters;
    public TileHandler tileH;
//    public ObjectHandler[] obj = new ObjectHandler[10];
    public EventHandler eventHandler = new EventHandler(this);
    //    public AssetSetter assetSetter = new AssetSetter(this);
    private final int screenWidth = 48 * 28;
    private final int screenHeight = 48 * 14;

    // Game State
    private int gameState;
    public void setGameState(int gameState) {
        this.gameState = gameState;
    }
    private final int startState = 0;
    private final int playState = 1;
    private final int pauseState = 2;

    public int getGameState() {
        return gameState;
    }

    public int getStartState() {
        return startState;
    }

    public int getPlayState() {
        return playState;
    }

    public int getPauseState() {
        return pauseState;
    }

    public int getScreenCol() {
        return 37;
    }

    public int getScreenRow() {
        return 18;
    }

    public int getScreenWidth() {
        return screenWidth;
    }
    public int getScreenHeight() {
        return screenHeight;
    }
    public int getTileSize() {
        return 36;
    }

    public void setStart() {
        //assetSetter.setObject();
        gameState = playState;
    }

    public GUI gui;

    public GamePanel() {
        characters = new Karaktere(this);
        tileH = new TileHandler(this);
        this.gui = new GUI(this);
        gui.setupGame();

        // zur Berechnung der Frames
        final long[] lastChecked = {System.currentTimeMillis()};
        final int[] frames = {0};

// Timer für die Aktualisierung und das Neuzeichnen des Panels
        Timer timer = new Timer(20, e -> {
            frames[0]++;
            update();
            gui.repaint();

            if (System.currentTimeMillis() - lastChecked[0] >= 1000) {

                System.out.println("FPS: " + frames[0]);
                frames[0] = 0;
                lastChecked[0] = System.currentTimeMillis();
            }
        });
        timer.start();
    }

    private void update() {


        if (gameState == playState) {
            // den anderen Player übergeben, um eine Kollision abzufragen
            characters.players.get(0).move(characters.players.get(1));
            characters.players.get(1).move(characters.players.get(0));

            if (characters.troll1.life <= 0) {
                gui.drawGhost = true;
                characters.spawnGhost(1050, 500, 3, "/npc/ghost1/");
                characters.ghost1.move(characters.players.get(0), characters.players.get(1));
            } else {
                characters.troll1.move(characters.players.get(0), characters.players.get(1));
            }

        }
        if (gameState == pauseState) {

        }
    }

}





