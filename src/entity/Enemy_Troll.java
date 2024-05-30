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

        direction = searchPath(player1.getX() / gamePanel.getTileSize(), player1.getY() / gamePanel.getTileSize());

        // toDo: Troll muss sich genau einen block bewegen bevor die Richtung geändert wird.
        //  Mittels Block Koordinaten checken in welche Richtung er gehen kann

        if (direction == null) {
            return;
        }

        // Überprüfen, ob der Troll eine Wand oder einen Spieler trifft und die Richtung ändern
        switch (direction) {
            case "up":
//                if (collisionHandler.noColisionWithTiles("up", x, y, speed, 36) &&
//                        collisionHandler.insideBoarder(x, y)) {
                    y -= speed;
//                } else {
//                    direction = getRandomDirection();
//                }

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
//                if (collisionHandler.noColisionWithTiles("left", x, y, speed, 36) &&
//                        collisionHandler.insideBoarder(x, y)) {
                    x -= speed;
//                } else {
//                    direction = getRandomDirection();
//                }

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

    public String searchPath(int goalCol, int goalRow) {
        int startCol = x / (gamePanel.getTileSize());
        int startRow = y / (gamePanel.getTileSize());

        pathFinder.setNodes(startCol, startRow, goalCol, goalRow);

        // default
        String direction = "bug";

        if (pathFinder.autoSearch()) {

            int nextX = pathFinder.pathList.get(0).col * gamePanel.getTileSize();
            int nextY = pathFinder.pathList.get(0).row * gamePanel.getTileSize();

            int enLeftX = x;
            int enRightX = x + gamePanel.getTileSize();
            int enTopY = y;
            int enBottomY = y + gamePanel.getTileSize();

            if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gamePanel.getTileSize()) {
                direction = "up";
            } else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gamePanel.getTileSize()) {
                direction = "down";
            } else if (enTopY >= nextY && enBottomY <= nextY + gamePanel.getTileSize()) {
                if (enLeftX > nextX) {
                    direction = "left";
                } else if (enLeftX < nextX) {
                    direction = "right";
                }
                // falls up und links
            } else if (enTopY > nextY && enLeftX > nextX) {
                direction = "up";
                if (!collisionHandler.noColisionWithTiles("up", enLeftX, enTopY, speed, 36)) {
                    direction = "left";
                }
            } else if (enTopY > nextY && enLeftX < nextX) {
                direction = "up";
                if (!collisionHandler.noColisionWithTiles("up", x, y, speed, 36)) {
                    direction = "right";
                }

                // Falls down und schräg
            } else if (enTopY < nextY && enLeftX > nextX) {
                direction = "down";
                if (!collisionHandler.noColisionWithTiles("down", x, y, speed, 36)) {
                    direction = "left";
                }
            } else if (enTopY < nextY && enLeftX < nextX) {
                direction = "down";
                if (!collisionHandler.noColisionWithTiles("down", x, y, speed, 36)) {
                    direction = "right";
                }
            }

            // wenn er den Spieler erreicht hat
            int nextCol = pathFinder.pathList.get(0).col;
            int nextRow = pathFinder.pathList.get(0).row;

            if (nextCol == goalCol && nextRow == goalRow) {
                System.out.println("Ziel erreicht");
                return "down";
            }

        } else {
            System.out.println("kein Pfad mit Pathfinder gefunden");
        }

        if (direction.equals("bug")) {
            pathFinder.setNodes(startCol, startRow, goalCol, goalRow);
        }

        return direction;
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
