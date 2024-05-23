package MainGUI;

import entity.Player;

import java.util.Objects;

public class CollisionHandler {
    private final GamePanel gamePanel;

    public CollisionHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public boolean noCollisionPlayerPlayer(String direction, Player player1, Player player2, int speed) {

        // Punkte
        int leftX1 = player1.getX();
        int rightX1 = player1.getX() + player1.getWidth();
        int topY1 = player1.getY();
        int bottomY1 = player1.getY() + player1.getHeight();

        int leftX2 = player2.getX();
        int rightX2 = player2.getX() + player2.getWidth();
        int topY2 = player2.getY();
        int bottomY2 = player2.getY() + player2.getHeight();


        // toDo: Bei Y < 0 und Y > 715 Kollision mit Border

        if (Objects.equals(direction, "up")) {
            if (topY1 - speed <= bottomY2 && topY1 - speed > topY2
                    && leftX1 <= rightX2 && rightX1 >= leftX2) {
                return false;
            }
        } else if (Objects.equals(direction, "down")) {
            if (bottomY1 + speed >= topY2 && bottomY1 + speed < bottomY2
                    && leftX1 <= rightX2 && rightX1 > leftX2) {
                return false;
            }
        } else if (Objects.equals(direction, "left")) {
            if (leftX1 - speed <= rightX2 && leftX1 - speed > leftX2
                    && topY1 < bottomY2 && bottomY1 > topY2) {
                return false;
            }
        } else if (Objects.equals(direction, "right")) {
            if (rightX1 + speed >= leftX2 && leftX1 + speed < rightX2
                    && topY1 < bottomY2 && bottomY1 > topY2) {
                return false;
            }
        }

        return true;
    }

    public boolean noCollisionWithTiles(String direction, Player player, int speed) {
        // Mit Playerkoordinaten (TOP LEFT ist x&y = 0)
        int leftX1 = player.getX() + 1;
        int rightX1 = player.getX() + player.getWidth() - 1;
        int topY1 = player.getY() + 1;
        int bottomY1 = player.getY() + player.getHeight() - 1;

        // die Rows und Cols - Größen berechnen
        int playerLeftCol = leftX1 / player.getHeight();
        int playerRightCol = rightX1 / player.getHeight();
        int playerTopRow = topY1 / player.getWidth();
        int playerBottomRow = bottomY1 / player.getWidth();

        // Player muss auf Kollision mit zwei Teilen pro Richtung geprüft werden
        int tileNum1, tileNum2;

        switch (direction) {
            case "up" -> {
                // den nächsten Schritt des Players überprüfen
                playerTopRow = (topY1 - speed) / player.getHeight();

                // die nebenliegenden Tiles definieren
                tileNum1 = gamePanel.tileH.mapTileNum[playerLeftCol][playerTopRow];
                tileNum2 = gamePanel.tileH.mapTileNum[playerRightCol][playerTopRow];

                // und schauen, ob sie Kollision angestellt haben
                if (gamePanel.tileH.tile[tileNum1].collision || gamePanel.tileH.tile[tileNum2].collision) {
                    return false;
                }
            }

            case "down" -> {
                playerBottomRow = (bottomY1 + speed) / player.getHeight();
                tileNum1 = gamePanel.tileH.mapTileNum[playerLeftCol][playerBottomRow];
                tileNum2 = gamePanel.tileH.mapTileNum[playerRightCol][playerBottomRow];

                if (gamePanel.tileH.tile[tileNum1].collision || gamePanel.tileH.tile[tileNum2].collision) {
                    return false;
                }
            }
            case "left" -> {
                playerLeftCol = (leftX1 - speed) / player.getWidth();
                tileNum1 = gamePanel.tileH.mapTileNum[playerLeftCol][playerTopRow];
                tileNum2 = gamePanel.tileH.mapTileNum[playerLeftCol][playerBottomRow];

                if (gamePanel.tileH.tile[tileNum1].collision || gamePanel.tileH.tile[tileNum2].collision) {
                    return false;
                }
            }
            case "right" -> {
                playerRightCol = (rightX1 + speed) / player.getWidth();
                tileNum1 = gamePanel.tileH.mapTileNum[playerRightCol][playerTopRow];
                tileNum2 = gamePanel.tileH.mapTileNum[playerRightCol][playerBottomRow];

                if (gamePanel.tileH.tile[tileNum1].collision || gamePanel.tileH.tile[tileNum2].collision) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean insideBoarder(int x, int y) {
        return x > 0 && x < gamePanel.getScreenCol() * gamePanel.getTileSize() &&
                y > 0 && y < gamePanel.getScreenRow() * gamePanel.getTileSize();
    }

    public boolean noColisionUp(int koordY, Player player1, Player player2, int speed) {
        return noCollisionPlayerPlayer("up", player1, player2, speed) &&
                (koordY > 0 || koordY < gamePanel.getScreenRow() * gamePanel.getTileSize()) &&
                noCollisionWithTiles("up", player1, speed);
    }

    public boolean noCollisionDown(int koordY, Player player1, Player player2, int speed) {
        return noCollisionPlayerPlayer("down", player1, player2, speed) &&
                (koordY > 0 || koordY < gamePanel.getScreenRow() * gamePanel.getTileSize()) &&
                noCollisionWithTiles("down", player1, speed);
    }

    public boolean noColisionLeft(int koordX, Player player1, Player player2, int speed) {
        return noCollisionPlayerPlayer("left", player1, player2, speed) &&
                (koordX > 0 || koordX < gamePanel.getScreenCol() * gamePanel.getTileSize()) &&
                noCollisionWithTiles("left", player1, speed);
    }

    public boolean noColisionRight(int koordX, Player player1, Player player2, int speed) {
        return noCollisionPlayerPlayer("right", player1, player2, speed) &&
                (koordX > 0 || koordX < gamePanel.getScreenCol() * gamePanel.getTileSize()) &&
                noCollisionWithTiles("right", player1, speed);
    }
}
