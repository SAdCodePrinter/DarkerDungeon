package entity;

import MainGUI.CollisionHandler;
import MainGUI.GamePanel;
import pathFinder.PathFinder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class Enemy_Troll extends Entity {

    CollisionHandler collisionHandler;
    public PathFinder pathFinder = null;

    public void drawDamage(Graphics2D g) {
        g.setColor(Color.cyan);
        g.drawRect(attackRect.x, attackRect.y, attackRect.width, attackRect.height);
    }

    public Enemy_Troll(GamePanel gamePanel, String imagePath) {
        super(gamePanel);
        collisionHandler = new CollisionHandler(gamePanel);
        getTrollImage(imagePath);

        attackRect.width = gamePanel.getTileSize();
        attackRect.height = gamePanel.getTileSize();
    }

    public void setPathFinder() {
//        pathFinder = new PathFinder(gamePanel);
        pathFinder = new PathFinder(gamePanel.getScreenCol(), gamePanel.getScreenRow(), gamePanel.tileH.mapTileNum, gamePanel.tileH.tile);
    }

    public void setDefault(int xKoord, int yKoord, int defineSpeed) {
        setX(xKoord);
        setY(yKoord);
        setSpeed(defineSpeed);
        setDirection("down");

        //Status
        maxLife = 6;
        life = maxLife;
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
        // Welcher Spieler angesteuert werden soll
        Player target = nearestPlayer(this, player1, player2);

        spriteCounter(8);
        if (pathFinder == null) {
            setPathFinder();
        }

        // Wenn sich der Troll noch nicht beim Spieler befindet:
        if (collisionHandler.noPlayerCollision(x, y - speed, player1.x, player1.y, 36) ||
                collisionHandler.noPlayerCollision(x, y - speed, player2.x, player2.y, 36)) {
            // Die Richtung in die der Troll gehen soll festlegen
            direction = nextTargetDirection(target.getX() / gamePanel.getTileSize(), target.getY() / gamePanel.getTileSize());

            // Überprüfen, ob der Troll eine Wand oder einen Spieler trifft
            switch (direction) {
                case "up":
                    if (collisionHandler.insideBoarder(x, y)) {
                        if (!collisionHandler.noPlayerCollision(x, y - speed, player1.x, player1.y, 36) ||
                                !collisionHandler.noPlayerCollision(x, y - speed, player2.x, player2.y, 36)) {
                            attacking = true;
                            directionHit = "hit_up";
                            break;
                        }
                        y -= speed;
                    }
                    break;

                case "down":
                    if (collisionHandler.insideBoarder(x, y)) {
                        if (!collisionHandler.noPlayerCollision(x, y + speed, player1.x, player1.y, 36) ||
                                !collisionHandler.noPlayerCollision(x, y + speed, player2.x, player2.y, 36)) {
                            attacking = true;
                            directionHit = "hit_down";
                            break;
                        }
                        y += speed;
                    }
                    break;

                case "left":
                    if (collisionHandler.insideBoarder(x, y)) {
                        if (!collisionHandler.noPlayerCollision(x - speed, y, player1.x, player1.y, 36) ||
                                !collisionHandler.noPlayerCollision(x - speed, y, player2.x, player2.y, 36)) {
                            attacking = true;
                            directionHit = "hit_left";
                            break;
                        }
                        x -= speed;
                    }
                    break;

                case "right":
                    if (collisionHandler.insideBoarder(x, y)) {
                        if (!collisionHandler.noPlayerCollision(x + speed, y, player1.x, player1.y, 36) ||
                                !collisionHandler.noPlayerCollision(x + speed, y, player2.x, player2.y, 36)) {
                            attacking = true;
                            directionHit = "hit_right";
                            break;
                        }
                        x += speed;
                    }
                    break;
            }
        } else {
            attacking = true;
            directionHit = "hit_down";
        }
    }

    private Player nearestPlayer(Enemy_Troll troll, Player player1, Player player2) {
        double distanceToPlayer1 = Math.sqrt(Math.pow(troll.getX() - player1.getX(), 2) + Math.pow(troll.getY() - player1.getY(), 2));
        double distanceToPlayer2 = Math.sqrt(Math.pow(troll.getX() - player2.getX(), 2) + Math.pow(troll.getY() - player2.getY(), 2));

        if (distanceToPlayer1 <= distanceToPlayer2) {
            return player1;
        } else {
            return player2;
        }
    }

    public String nextTargetDirection(int goalCol, int goalRow) {
        int startCol = x / (gamePanel.getTileSize());
        int startRow = y / (gamePanel.getTileSize());

        pathFinder.setNodes(startCol, startRow, goalCol, goalRow);

        // default
        String nextDirection = "idle";

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


            // Richtungen bestimmen in die gelaufen wird
            if (topY > topY2 && leftX >= leftX2 && rightX <= rightX2) {
                nextDirection = "up";
            } else if (topY < topY2 && leftX >= leftX2 && rightX <= rightX2) {
                nextDirection = "down";
            } else if (topY >= topY2 && bottmY <= bottmY2) {
                if (leftX > leftX2) {
                    nextDirection = "left";
                } else if (leftX < leftX2) {
                    nextDirection = "right";
                }

                // falls up & schräg, Kollision mit Teilen beachten
            } else if (topY > topY2 && leftX > leftX2) {
                nextDirection = "up";
                if (!collisionHandler.noColisionWithTiles("up", leftX, topY, speed, 36)) {
                    nextDirection = "left";
                }
            } else if (topY > topY2 && leftX < leftX2) {
                nextDirection = "up";
                if (!collisionHandler.noColisionWithTiles("up", x, y, speed, 36)) {
                    nextDirection = "right";
                }

                // Falls down und schräg
            } else if (topY < topY2 && leftX > leftX2) {
                nextDirection = "down";
                if (!collisionHandler.noColisionWithTiles("down", x, y, speed, 36)) {
                    nextDirection = "left";
                }
            } else if (topY < topY2 && leftX < leftX2) {
                nextDirection = "down";
                if (!collisionHandler.noColisionWithTiles("down", x, y, speed, 36)) {
                    nextDirection = "right";
                }
            }
            // Troll hat sein Ziel erreicht


        } else {

//            System.out.println("Troll hat keinen Pfad zum Ziel mit dem Pathfinder gefunden");
        }

        return nextDirection;
        // https://www.youtube.com/watch?v=Hd0D68guFKg
    }

    public void drawTroll(Graphics2D g) {
        BufferedImage imageTroll;

        if (attacking) {
            hitSpritCounter++;

            if (hitSpritCounter <= 4) hitSpriteNum = 0;
            if (hitSpritCounter > 4 && hitSpritCounter <= 8) hitSpriteNum = 1;
            if (hitSpritCounter > 8 && hitSpritCounter <= 12) hitSpriteNum = 2;
            if (hitSpritCounter > 12 && hitSpritCounter <= 16) hitSpriteNum = 3;
            if (hitSpritCounter > 16 && hitSpritCounter <= 20) hitSpriteNum = 4;
            if (hitSpritCounter > 20 && hitSpritCounter <= 24) hitSpriteNum = 5;

            if (hitSpritCounter > 24 && hitSpritCounter <= 28) {
                hitSpriteNum = 6;
                if (hitSpritCounter == 28) {
                    attacking();
                }
            }

            if (hitSpritCounter > 28 && hitSpritCounter <= 32) hitSpriteNum = 7;

            if (hitSpritCounter > 32) {
                hitSpriteNum = 0;
                hitSpritCounter = 0;
                attacking = false;
            }

            imageTroll = switch (directionHit) {
                // toDo: wenn es einen Schlag nach oben gibt für den Troll, ändern
                case "hit_up" -> hit_down[hitSpriteNum];
                case "hit_down" -> hit_down[hitSpriteNum];
                case "hit_left" -> hit_left[hitSpriteNum];
                case "hit_right" -> hit_right[hitSpriteNum];
                default -> null;
            };
        } else {
            imageTroll =
                    switch (direction) {
                        case "idle" -> idle[spriteNum];
                        case "up" -> down[spriteNum];
                        case "down" -> down[spriteNum];
                        case "left" -> left[spriteNum];
                        case "right" -> right[spriteNum];
                        default -> null;
                    };
        }

        if (imageTroll != null) {
            g.drawImage(imageTroll, getX() - 32, getY() - 36, gamePanel.getTileSize() * 3, gamePanel.getTileSize() * 3, null);
        }
    }

    private void attacking() {
        switch (directionHit) {
            case "hit_up" -> {
                attackRect.y = y - gamePanel.getTileSize();
                attackRect.x = x;
            }
            case "hit_down" -> {
                attackRect.y = y + gamePanel.getTileSize();
                attackRect.x = x;
            }
            case "hit_left" -> {
                attackRect.x = x - gamePanel.getTileSize();
                attackRect.y = y;
            }
            case "hit_right" -> {
                attackRect.x = x + gamePanel.getTileSize();
                attackRect.y = y;
            }
        }

        if (collisionHandler.entityCollision(gamePanel.characters.player1.x, gamePanel.characters.player1.y, attackRect.x, attackRect.y, gamePanel.getTileSize()) ||
                collisionHandler.entityCollision(gamePanel.characters.player2.x, gamePanel.characters.player2.y, attackRect.x, attackRect.y, gamePanel.getTileSize())) {
            damagePlayer();
        }
    }

    // toDo: Die Id des Players übergeben, welcher gehittet wird
    private void damagePlayer() {
        gamePanel.characters.player1.life -= 1;
        System.out.println("Leben Spieler 1 & 2: " + gamePanel.characters.player1.life);
    }
}
