package MainGUI;

import entity.Enemy_Ghost;
import entity.Enemy_Troll;
import entity.Karaktere;
import entity.Player;
import tile.TileHandler;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class GamePanel {
    private Timer timer;
    private int level;
    public Karaktere characters;
    public TileHandler tileH;
    //    public ObjectHandler[] obj = new ObjectHandler[10];
    public EventHandler eventHandler = new EventHandler(this);
    //    public AssetSetter assetSetter = new AssetSetter(this);
    private final int screenWidth = 48 * 28;
    private final int screenHeight = 48 * 14;

    // Game State
    private long startTime;
    private long endTime;

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    private int gameState;
    private final int startState = 0;
    private final int playState = 1;
    private final int pauseState = 2;
    private final int endState = 3;
    private boolean enemysSpawned = false;

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

    public int getEndState() {
        return this.endState;
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
        this.level = 1;
        startTime = System.currentTimeMillis();

    }

    public GUI gui;

    public GamePanel() {
        characters = new Karaktere(this);
        tileH = new TileHandler(this);
        this.gui = new GUI(this);
        KeyHandler kH1 = new KeyHandler(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_P, KeyEvent.VK_R, this);
        KeyHandler kH2 = new KeyHandler(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_O, this);
        characters.spawnPlayer(getTileSize() * 12, getTileSize() * 8, 7, kH1, "/players/player1/", "Spieler 1");
        characters.spawnPlayer(getTileSize() * 7, getTileSize() * 7, 5, kH2, "/players/player2/", "Spieler 2");

        gui.setupGame(kH1, kH2);

        // zur Berechnung der Frames
        final long[] lastChecked = {System.currentTimeMillis()};
        final int[] frames = {0};

// Timer für die Aktualisierung und das Neuzeichnen des Panels
        timer = new Timer(20, e -> {
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
            for (Player player : characters.players) {
                player.move(characters.players.get(1));
                player.move(characters.players.get(0));
            }


            if (!enemysSpawned) {
                for (int i = 0; i < this.level; i++) {
                    if (this.level % 2 == 0) {
                        characters.spawnGhost(1050, 500, 3, "/npc/ghost1/");
                    } else {
                        characters.spawnTroll(1050, 500, 2, "/npc/troll1/");
                    }
                }

                enemysSpawned = true;
            }

            if (characters.ghosts.isEmpty() && characters.trolls.isEmpty()) {
                this.level++;
                enemysSpawned = false;
            } else {
                for (Enemy_Troll troll : characters.trolls) {
                    // toDo: Player als Liste übergeben und nicht einzeln
                    troll.move(characters.players.get(0), characters.players.get(1));
                }
                for (Enemy_Ghost ghost : characters.ghosts) {
                    ghost.move(characters.players.get(0), characters.players.get(1));
                }
            }

            for (Player player : characters.players) {
                if (player.life <= 0) {
                    this.endTime = System.currentTimeMillis();
                    this.gameState = endState;

                }
            }

        } else if (gameState == pauseState) {

        } else if (gameState == endState) {
            System.out.println("Du hast " + ((endTime - startTime) / 1000) + " Sekunden überlebt");
            System.out.println("Du hast Level: " + this.level + " erreicht");

            for (Player player : characters.players) {
                player.setTime(((endTime - startTime) / 1000));
                player.setReachedLevel(this.level);
            }


            timer.stop();
        }
    }

}





