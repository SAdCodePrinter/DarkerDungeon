package entity;

import MainGUI.CollisionHandler;
import MainGUI.GamePanel;
import pathFinder.Node;
import pathFinder.PathFinder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


public class Enemy_Troll extends Entity {

    CollisionHandler collisionHandler;
    PathFinder pathFinder = null;


    public Enemy_Troll(GamePanel gamePanel, String imagePath) {
        super(gamePanel);
        collisionHandler = new CollisionHandler(gamePanel);
        getTrollImage(imagePath);
    }

    public void setPathFinder() {
        pathFinder = new PathFinder(gamePanel.tileH.mapTileNum, gamePanel.tileH.getColisionObjekts());
    }

    public void setDefault(int xKoord, int yKoord, int defineSpeed) {
        setX(xKoord);
        setY(yKoord);
        setSpeed(defineSpeed);
        setDirection("down");
    }

    public void getTrollImage(String path) {
        try {
            for (int i = 0; i < 8; i++) {
                //up[i] = ImageIO.read(Objects.requireNonNull(Player.class.getResourceAsStream(path + "up (" + (i + 1) + ").png")));
                down[i] = ImageIO.read(Objects.requireNonNull(Player.class.getResourceAsStream(path + "down (" + (i + 1) + ").png")));
                left[i] = ImageIO.read(Objects.requireNonNull(Player.class.getResourceAsStream(path + "left (" + (i + 1) + ").png")));
                right[i] = ImageIO.read(Objects.requireNonNull(Player.class.getResourceAsStream(path + "right (" + (i + 1) + ").png")));
                //idle[i] = ImageIO.read(Objects.requireNonNull(Player.class.getResourceAsStream(path + "idle (" + (i + 1) + ").png")));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawHitbox(Graphics2D g) {
        g.setColor(Color.red);
        g.drawRect(x, y, 36, 36);
    }

    public void move(Player player1, Player player2) {
        if (pathFinder == null) {
            setPathFinder();
        }
        spriteCounter(8);

        direction = followPlayer(player1);

        // Überprüfen, ob der Troll eine Wand oder einen Spieler trifft und die Richtung ändern
        switch (direction) {
            case "up":
                if (collisionHandler.noColisionWithTiles("up", x, y, speed, 36) &&
                        collisionHandler.insideBoarder(x, y)) {
                    y -= speed;
                } else {
                    direction = getRandomDirection();
                }

                if (!collisionHandler.noCollisionPlayer("up", x, y, player1.x, player1.y, speed, 36) ||
                        !collisionHandler.noCollisionPlayer("up", x, y, player2.x, player2.y, speed, 36)) {
                    // Action
                    direction = getRandomDirection();
                }
                break;

            case "down":
                if (collisionHandler.noColisionWithTiles("down", x, y, speed, 36) &&
                        collisionHandler.insideBoarder(x, y)) {
                    y += speed;
                } else {
                    direction = getRandomDirection();
                }

                if (!collisionHandler.noCollisionPlayer("down", x, y, player1.x, player1.y, speed, 36) ||
                        !collisionHandler.noCollisionPlayer("down", x, y, player2.x, player2.y, speed, 36)) {
                    // Action
                    direction = getRandomDirection();
                }
                break;

            case "left":
                if (collisionHandler.noColisionWithTiles("left", x, y, speed, 36) &&
                        collisionHandler.insideBoarder(x, y)) {
                    x -= speed;
                } else {
                    direction = getRandomDirection();
                }

                if (!collisionHandler.noCollisionPlayer("left", x, y, player1.x, player1.y, speed, 36) ||
                        !collisionHandler.noCollisionPlayer("left", x, y, player2.x, player2.y, speed, 36)) {
                    // Action
                    direction = getRandomDirection();
                }
                break;

            case "right":
                if (collisionHandler.noColisionWithTiles("right", x, y, speed, 36) &&
                        collisionHandler.insideBoarder(x, y)) {
                    x += speed;
                } else {
                    direction = getRandomDirection();
                }
                if (!collisionHandler.noCollisionPlayer("right", x, y, player1.x, player1.y, speed, 36) ||
                        !collisionHandler.noCollisionPlayer("right", x, y, player2.x, player2.y, speed, 36)) {
                    // Action
                    direction = getRandomDirection();
                }
                break;
        }
    }

    private String getRandomDirection() {
        String[] directions = {"up", "down", "left", "right"};
        String newDirection;
        do {
            newDirection = directions[(int) (Math.random() * directions.length)];
        } while (newDirection.equals(direction));
        return newDirection;
    }

    public String followPlayer(Player player) {
        int playerX = player.getX() / gamePanel.getTileSize();
        int playerY = player.getY() / gamePanel.getTileSize();
        int trollx = x / gamePanel.getTileSize();
        int trolly = y / gamePanel.getTileSize();


        int dx = playerX - x;
        int dy = playerY - y;

        // Überprüfen, ob der Troll den Spieler bereits erreicht hat
        if (dx == 0 && dy == 0) {
            return "down";
        }

        pathFinder.setNodes(trollx, y / trolly, playerX, playerY);
        if (pathFinder.autoSearch()) {

            int x1 = pathFinder.pathList.get(0).col;
            int y1 = pathFinder.pathList.get(0).row;

            if (x > x1 && y == y1) {
                return "left";
            } else if (x < x1 && y == y1) {
                return "right";
            } else if (x == x1 && y < y1) {
                return "down";
            } else if (x == x1 && y > y1) {
                return "up";
            } else {
                System.out.println("x: " + x + "x1: " + x1 + "y: " + y + "y1: " + y1);
            }

        }

        // Kein Pfad gefunden
        System.out.println("Kein Pfad zum Spieler gefunden");
        return "down";
        // https://www.youtube.com/watch?v=Hd0D68guFKg
    }


    public void drawTroll(Graphics2D g) {
        BufferedImage imageTroll =
                switch (direction) {
                    //case "idle" -> idle[spriteNum];
                    case "up" -> down[spriteNum];
                    case "down" -> down[spriteNum];
                    case "left" -> left[spriteNum];
                    case "right" -> right[spriteNum];
                    default -> null;
                };

        if (imageTroll != null) {
            g.drawImage(imageTroll, getX() - 32, getY() - 36, gamePanel.getTileSize() * 3, gamePanel.getTileSize() * 3, null);
        }
    }
}
