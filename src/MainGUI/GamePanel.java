package MainGUI;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import entity.Enemy_Ghost;
import entity.Enemy_Troll;
import entity.Karaktere;
import entity.Player;
import tile.TileHandler;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.KeyEvent;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GamePanel {
    private Timer timer;
    private int level;
    private double score;
    public Karaktere characters;
    public TileHandler tileH;
    //    public ObjectHandler[] obj = new ObjectHandler[10];
    public EventHandler eventHandler = new EventHandler(this);
    //    public AssetSetter assetSetter = new AssetSetter(this);
    private final int screenWidth = 48 * 28;
    private final int screenHeight = 48 * 14;

    // Game State
    private double startTime;
    private double endTime;

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    private int gameState;
    private final int startState = 0;
    private final int playState = 1;
    private final int pauseState = 2;
    private final int endState = 3;
    private boolean enemysSpawned = false;

    private List<int[]> spawnPoints = new ArrayList<>();
    private int currentSpawnIndex = 0;

    public double getScore() {
        return this.score;
    }

    public int getLevel() {
        return this.level;
    }

    private void calculateMonstersForLevel(int level, List<String> monsters) {
        int numMonsters = Math.min(level, 15);
        boolean spawnBoth = level % 5 == 0;     // Jedes 5. Level spawnt sowohl Geister als auch Trolle

        for (int i = 0; i < numMonsters; i++) {
            if (spawnBoth) {
                monsters.add(i % 2 == 0 ? "ghost" : "troll");
            } else {
                monsters.add(level % 2 == 0 ? "ghost" : "troll");
            }
        }
    }


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
        gameState = startState;
        this.level = 1;
        startTime = System.currentTimeMillis();
    }

    public void resetGame() {
        this.level = 1;
        this.score = 0;
        for (Player player : characters.players) {
            player.reset();  // Diese Methode sollte in der Player-Klasse definiert werden, um das Leben und andere Parameter zurückzusetzen
        }
        this.startTime = System.currentTimeMillis();
        this.enemysSpawned = false;
        this.currentSpawnIndex = 0;
        this.timer.start();
        characters.players.get(0).setPosition(getTileSize() * 17, getTileSize() * 8);
        characters.players.get(1).setPosition(getTileSize() * 18, getTileSize() * 8);
    }


    public GUI gui;

    public GamePanel() {

        // Spieler-Namen-Eingabe
        String namePlayer1 = JOptionPane.showInputDialog("Spieler 1 Name:");
        String namePlayer2 = JOptionPane.showInputDialog("Spieler 2 Name:");

        if (namePlayer1 == null || namePlayer1.isEmpty()) {
            namePlayer1 = "Spieler 1"; // Standardname setzen, falls nichts eingegeben wurde
        }

        if (namePlayer2 == null || namePlayer2.isEmpty()) {
            namePlayer2 = "Spieler 2"; // Standardname setzen, falls nichts eingegeben wurde
        }

        characters = new Karaktere(this);
        tileH = new TileHandler(this);
        this.gui = new GUI(this);
        KeyHandler kH1 = new KeyHandler(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_P, KeyEvent.VK_R, this);
        KeyHandler kH2 = new KeyHandler(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_O, this);
        characters.spawnPlayer(getTileSize() * 18, getTileSize() * 8, 7, kH1, "/players/player1/", namePlayer1);
        characters.spawnPlayer(getTileSize() * 17, getTileSize() * 8, 5, kH2, "/players/player2/", namePlayer2);

        gui.setupGame(kH1, kH2);



        //Spawnpunkte der Monster
        spawnPoints.add(new int[]{100, 100});
        spawnPoints.add(new int[]{200, 150});
        spawnPoints.add(new int[]{300, 200});
        spawnPoints.add(new int[]{400, 250});
        spawnPoints.add(new int[]{500, 300});
        spawnPoints.add(new int[]{600, 350});
        spawnPoints.add(new int[]{700, 400});
        spawnPoints.add(new int[]{1000, 400});


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
            for (Player player : characters.players) {
                player.move(characters.players);
            }

            if (!enemysSpawned) {
                List<String> monstersToSpawn = new ArrayList<>();
                calculateMonstersForLevel(this.level, monstersToSpawn);

                for (String monsterType : monstersToSpawn) {
                    int[] spawnPoint = spawnPoints.get(currentSpawnIndex);
                    currentSpawnIndex = (currentSpawnIndex + 1) % spawnPoints.size();

                    if (monsterType.equals("ghost")) {
                        characters.spawnGhost(spawnPoint[0], spawnPoint[1], 3, "/npc/ghost1/");
                    } else {
                        characters.spawnTroll(spawnPoint[0], spawnPoint[1], 2, "/npc/troll1/");
                    }
                }

                enemysSpawned = true;
            }

            // Sobald alle Monster tot sind, ist das Level vorbei
            if (characters.ghosts.isEmpty() && characters.trolls.isEmpty()) {
                this.level++;
                for (Player player : characters.players) {
                    player.life = 6;
                }

                enemysSpawned = false;
            } else {
                for (Enemy_Troll troll : characters.trolls) {
                    troll.move(characters.players.get(0), characters.players.get(1));
                }
                for (Enemy_Ghost ghost : characters.ghosts) {
                    ghost.move(characters.players.get(0), characters.players.get(1));
                }
            }

            // Spieler tot: Spiel vorbei
            for (Player player : characters.players) {
                if (player.life <= 0) {
                    this.endTime = System.currentTimeMillis();
                    this.gameState = endState;
                }
            }
        } else if (gameState == pauseState) {
            // toDo: Pause Logik

        } else if (gameState == endState) {
            this.score = (endTime - startTime) / 1000.0;
            System.out.println("Du hast " + (score) + " Sekunden überlebt");
            System.out.println("Du hast Level: " + this.level + " erreicht");

            for (Player player : characters.players) {
                player.setTime(((endTime - startTime) / 1000.0));
                player.setReachedLevel(this.level);
                saveScoreToCSV(player.getName(), player.getReachedLevel(), player.getTime(), player.getKillCounter());
            }

            // entfernen der Entitäten
            characters.trolls.clear();
            characters.ghosts.clear();

            timer.stop();
        }
    }

    // toDo: Auslagern in eigene Klasse?
    public static void saveScoreToCSV(String name, int reachedLevel, double time, int killCounter) {
        String[] data = {name, Integer.toString(reachedLevel), Double.toString(time), Integer.toString(killCounter)};
        File file = new File("highscores/Highscores.csv");

        // Ensure the directory exists
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        List<String[]> existingData = new ArrayList<>();

        // Read existing data if the file exists
        if (file.exists()) {
            try (FileReader inputFile = new FileReader(file);
                 CSVReader reader = new CSVReader(inputFile)) {
                existingData = reader.readAll();
            } catch (IOException | CsvException e) {
                System.out.println("Konnte den Highscore nicht lesen: " + e.getMessage());
                e.printStackTrace();
            }
        }

        // Update or add new data
        boolean found = false;
        Iterator<String[]> iterator = existingData.iterator();
        while (iterator.hasNext()) {
            String[] existing = iterator.next();
            if (Objects.equals(name, existing[0])) {
                if (reachedLevel > Integer.parseInt(existing[1]) ||
                        (reachedLevel == Integer.parseInt(existing[1]) && time < Double.parseDouble(existing[2]))) {
                    iterator.remove();
                    existingData.add(data);
                }
                found = true;
                break;
            }
        }

        if (!found) {
            existingData.add(data);
        }

        existingData.sort(Comparator.comparingDouble((zeit) -> Double.parseDouble(zeit[2])));
        existingData.sort(Comparator.comparingInt((String[] level) -> Integer.parseInt(level[1])).reversed());

        // Zeigen der Highscores
        // Write updated data back to file
        try (FileWriter outputFile = new FileWriter(file);
             CSVWriter writer = new CSVWriter(outputFile)) {
            writer.writeAll(existingData);
            System.out.println("Highscore erfolgreich gespeichert!");
        } catch (IOException e) {
            System.out.println("Konnte den Highscore nicht speichern: " + e.getMessage());
            e.printStackTrace();
        }
    }
}




