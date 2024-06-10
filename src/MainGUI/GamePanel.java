package MainGUI;

import entity.Karaktere;
import object.ObjectHandler;
import tile.TileHandler;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    public Karaktere characters;
    public TileHandler tileH;
    public UI ui = new UI(this);
    public ObjectHandler[] obj = new ObjectHandler[10];
    public EventHandler eventHandler = new EventHandler(this);
    //    public AssetSetter assetSetter = new AssetSetter(this);
    private final int screenWidth = 48 * 28;
    private final int screenHeight = 48 * 14;
    private final int delay = 1000 / 60; // Timer delay für 60 FPS

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    // Game State
    private int gameState;
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

    public int getTileSize() {
        return 36;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setupGame() {
        //assetSetter.setObject();
        gameState = playState;
    }

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));

        characters = new Karaktere(this);

        tileH = new TileHandler(this);

        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(characters.kH1);
        this.addKeyListener(characters.kH2);
        this.setFocusable(true);

// Timer für die Aktualisierung und das Neuzeichnen des Panels
        Timer timer = new Timer(delay, e -> {
            update();
            repaint();
        });
        timer.start();
    }

    private void update() {
        if (gameState == playState) {
            // den anderen Player übergeben, um eine Kollision abzufragen
            characters.players.get(0).move(characters.players.get(1));
            characters.players.get(1).move(characters.players.get(0));

            if (characters.troll1.life <= 0) {
                characters.ghost1.move(characters.players.get(0), characters.players.get(1));
            } else {
                characters.troll1.move(characters.players.get(0), characters.players.get(1));
            }

        }
        if (gameState == pauseState) {

        }


    }

    // toDO: das drawn auslagern
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Draw draw = new Draw(this);

        Graphics2D g1 = (Graphics2D) g;

        draw.backGroundTiles(g1, true);


//        characters.ghost1.drawHitbox(g1);

        characters.players.get(0).drawPlayer(g1);
        characters.players.get(0).drawDamage(g1);
//        characters.player1.drawHitbox(g1);

        characters.players.get(1).drawPlayer(g1);
        characters.players.get(1).drawHitbox(g1);
        characters.players.get(1).drawDamage(g1);

        eventHandler.drawEvent(g1);

//        eventHandler.drawRect(g1);
//        characters.player2.drawHitbox(g1);

//        for (int i = 0; i < obj.length; i++) {
//            if (obj [i] != null){
//                obj[i].draw(g1, this);
//            }
//        }

        if (characters.troll1.life <= 0) {
            characters.spawnGhost(1050, 500, 5, "/npc/ghost1/");
            draw.drawGhost(g1);
        } else {
            characters.troll1.drawTroll(g1);
//        characters.troll1.drawHitbox(g1);
            characters.troll1.drawDamage(g1);
        }

        draw.backGroundTiles(g1, false);
        ui.draw(g1);

        g1.dispose();


    }
}





