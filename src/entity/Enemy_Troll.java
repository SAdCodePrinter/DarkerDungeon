package entity;

import MainGUI.CollisionHandler;
import MainGUI.GamePanel;
import pathFinder.Node;
import pathFinder.PathFinder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Objects;


public class Enemy_Troll extends Entity {

    CollisionHandler collisionHandler;
    public PathFinder pathFinder = null;


    public Enemy_Troll(GamePanel gamePanel, String imagePath) {
        super(gamePanel);
        collisionHandler = new CollisionHandler(gamePanel);
        getTrollImage(imagePath);
    }

    public void setPathFinder() {
        pathFinder = new PathFinder(gamePanel);
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
                down[i] = ImageIO.read(Objects.requireNonNull(Player.class.getResourceAsStream(path + "down (" + (i + 1) + ").png")));
                left[i] = ImageIO.read(Objects.requireNonNull(Player.class.getResourceAsStream(path + "left (" + (i + 1) + ").png")));
                right[i] = ImageIO.read(Objects.requireNonNull(Player.class.getResourceAsStream(path + "right (" + (i + 1) + ").png")));
                hit_right[i] = ImageIO.read(Objects.requireNonNull(Player.class.getResourceAsStream(path + "hit_right (" + (i + 1) + ").png")));
                hit_left[i] = ImageIO.read(Objects.requireNonNull(Player.class.getResourceAsStream(path + "hit_left (" + (i + 1) + ").png")));
                hit_down[i] = ImageIO.read(Objects.requireNonNull(Player.class.getResourceAsStream(path + "hit_down (" + (i + 1) + ").png")));
                idle[i] = ImageIO.read(Objects.requireNonNull(Player.class.getResourceAsStream(path + "idle (" + (i + 1) + ").png")));

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

        direction = searchPath(player1.getX() / gamePanel.getTileSize(), player1.getY() / gamePanel.getTileSize());

        // Überprüfen, ob der Troll eine Wand oder einen Spieler trifft und die Richtung ändern
        switch (direction) {
            case "up":
                if (collisionHandler.noColisionWithTiles("up", x, y, speed, 36) &&
                        collisionHandler.insideBoarder(x, y)) {

                    if (!collisionHandler.noCollisionPlayer("up", x, y, player1.x, player1.y, speed, 36) ||
                            !collisionHandler.noCollisionPlayer("up", x, y, player2.x, player2.y, speed, 36)) {

                        System.out.println("ACTIONNNN UPPP");
                        direction = "hit_left";
                        break;
                    }
                    y -= speed;
                }
                break;

            case "down":
                if (collisionHandler.noColisionWithTiles("down", x, y, speed, 36) &&
                        collisionHandler.insideBoarder(x, y)) {
                    if (!collisionHandler.noCollisionPlayer("down", x, y, player1.x, player1.y, speed, 36) ||
                            !collisionHandler.noCollisionPlayer("down", x, y, player2.x, player2.y, speed, 36)) {

                        System.out.println("ACTIONNNN DOWNN");
                        direction = "hit_down";
                        break;
                    }
                    y += speed;
                }
                break;

            case "left":
                if (collisionHandler.noColisionWithTiles("left", x, y, speed, 36) &&
                        collisionHandler.insideBoarder(x, y)) {
                    if (!collisionHandler.noCollisionPlayer("left", x, y, player1.x, player1.y, speed, 36) ||
                            !collisionHandler.noCollisionPlayer("left", x, y, player2.x, player2.y, speed, 36)) {

                        System.out.println("ACTIONNNN LEFTTT");
                        direction = "hit_left";
                        break;
                    }
                    x -= speed;
                }
                break;

            case "right":
                if (collisionHandler.noColisionWithTiles("right", x, y, speed, 36) &&
                        collisionHandler.insideBoarder(x, y)) {
                    if (!collisionHandler.noCollisionPlayer("right", x, y, player1.x, player1.y, speed, 36) ||
                            !collisionHandler.noCollisionPlayer("right", x, y, player2.x, player2.y, speed, 36)) {

                        System.out.println("ACTIONNNN RIGHHTTT");
                        direction = "hit_right";
                        break;
                    }
                    x += speed;
                }
                break;
        }
    }

    public String searchPath(int goalCol, int goalRow) {
        int startCol = x / (gamePanel.getTileSize());
        int startRow = y / (gamePanel.getTileSize());

        pathFinder.setNodes(startCol, startRow, goalCol, goalRow);

        // default
        String direction = "idle";

        if (pathFinder.autoSearch()) {

            // Aktuelle Koordinaten
            int leftX = x;
            int rightX = x + gamePanel.getTileSize();
            int topY = y;
            int bottmY = y + gamePanel.getTileSize();

            // Koordinaten des nächsten Schrittes
            int leftX2 = pathFinder.pathList.get(0).col * gamePanel.getTileSize();
            int rightX2 = pathFinder.pathList.get(0).col * gamePanel.getTileSize() + gamePanel.getTileSize();
            int topY2 = pathFinder.pathList.get(0).row * gamePanel.getTileSize();
            int bottmY2 = pathFinder.pathList.get(0).row * gamePanel.getTileSize() + gamePanel.getTileSize();


            // Verschiedene Richtungen
            if (topY > topY2 && leftX >= leftX2 && rightX <= rightX2) {
                direction = "up";
            } else if (topY < topY2 && leftX >= leftX2 && rightX <= rightX2) {
                direction = "down";
            } else if (topY >= topY2 && bottmY <= bottmY2) {
                if (leftX > leftX2) {
                    direction = "left";
                } else if (leftX < leftX2) {
                    direction = "right";
                }

                // falls up und schräg, Kollision mit Teilen beachten
            } else if (topY > topY2 && leftX > leftX2) {
                direction = "up";
                if (!collisionHandler.noColisionWithTiles("up", leftX, topY, speed, 36)) {
                    direction = "left";
                }
            } else if (topY > topY2 && leftX < leftX2) {
                direction = "up";
                if (!collisionHandler.noColisionWithTiles("up", x, y, speed, 36)) {
                    direction = "right";
                }

                // Falls down und schräg
            } else if (topY < topY2 && leftX > leftX2) {
                direction = "down";
                if (!collisionHandler.noColisionWithTiles("down", x, y, speed, 36)) {
                    direction = "left";
                }
            } else if (topY < topY2 && leftX < leftX2) {
                direction = "down";
                if (!collisionHandler.noColisionWithTiles("down", x, y, speed, 36)) {
                    direction = "right";
                }
            }
            // Troll hat sein Ziel erreicht


        } else {

//            System.out.println("Troll hat keinen Pfad zum Ziel mit dem Pathfinder gefunden");
        }

        return direction;
        // https://www.youtube.com/watch?v=Hd0D68guFKg
    }

    public void drawTroll(Graphics2D g) {
        BufferedImage imageTroll =
                switch (direction) {
                    case "idle" -> idle[spriteNum];
                    case "up" -> down[spriteNum];
                    case "down" -> down[spriteNum];
                    case "left" -> left[spriteNum];
                    case "right" -> right[spriteNum];
                    case "hit_right" -> hit_right[spriteNum];
                    case "hit_left" -> hit_left[spriteNum];
                    case "hit_down" -> hit_down[spriteNum];
                    default -> null;
                };

        if (imageTroll != null) {
            g.drawImage(imageTroll, getX() - 32, getY() - 36, gamePanel.getTileSize() * 3, gamePanel.getTileSize() * 3, null);
        }
    }
}
