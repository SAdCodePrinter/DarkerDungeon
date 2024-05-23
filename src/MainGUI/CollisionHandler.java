package MainGUI;

import entity.Player;

import java.util.Objects;

public class CollisionHandler {
    private final GamePanel gamePanel;

    public CollisionHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public boolean noCollisionPlayer(String direction, int leftX1, int topY1, int leftX2, int topY2, int speed) {

        // Punkte
        int rightX1 = leftX1 + gamePanel.getTileSize();
        int bottomY1 = topY1 + gamePanel.getTileSize();

        int rightX2 = leftX2 + gamePanel.getTileSize();
        int bottomY2 = topY2 + gamePanel.getTileSize();


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

    public boolean noCollisionWithTiles(String direction, int leftX1, int topY1, int speed) {
        // wird um 2 pro Seite verkleinert, da sonst das errechnete Quadrat nicht zwischen zwei tiles passt
        int rightX1 = leftX1 + gamePanel.getTileSize() - 2;
        leftX1 += 2;
        int bottomY1 = topY1 + gamePanel.getTileSize() - 2;
        topY1 += 2;

        // die Rows und Cols - Größen berechnen
        int playerLeftCol = leftX1 / gamePanel.getTileSize();
        int playerRightCol = rightX1 / gamePanel.getTileSize();
        int playerTopRow = topY1 / gamePanel.getTileSize();
        int playerBottomRow = bottomY1 / gamePanel.getTileSize();

        // Player muss auf Kollision mit zwei Teilen pro Richtung geprüft werden
        int tileNum1, tileNum2;

        switch (direction) {
            case "up" -> {
                // den nächsten Schritt des Players überprüfen
                playerTopRow = (topY1 - speed) / gamePanel.getTileSize();

                // die nebenliegenden Tiles definieren
                tileNum1 = gamePanel.tileH.mapTileNum[playerLeftCol][playerTopRow];
                tileNum2 = gamePanel.tileH.mapTileNum[playerRightCol][playerTopRow];

                // und schauen, ob sie Kollision angestellt haben
                if (gamePanel.tileH.tile[tileNum1].collision || gamePanel.tileH.tile[tileNum2].collision) {
                    return false;
                }
            }

            case "down" -> {
                playerBottomRow = (bottomY1 + speed) / gamePanel.getTileSize();
                tileNum1 = gamePanel.tileH.mapTileNum[playerLeftCol][playerBottomRow];
                tileNum2 = gamePanel.tileH.mapTileNum[playerRightCol][playerBottomRow];

                if (gamePanel.tileH.tile[tileNum1].collision || gamePanel.tileH.tile[tileNum2].collision) {
                    return false;
                }
            }
            case "left" -> {
                playerLeftCol = (leftX1 - speed) / gamePanel.getTileSize();
                tileNum1 = gamePanel.tileH.mapTileNum[playerLeftCol][playerTopRow];
                tileNum2 = gamePanel.tileH.mapTileNum[playerLeftCol][playerBottomRow];

                if (gamePanel.tileH.tile[tileNum1].collision || gamePanel.tileH.tile[tileNum2].collision) {
                    return false;
                }
            }
            case "right" -> {
                playerRightCol = (rightX1 + speed) / gamePanel.getTileSize();
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

    /**
     * @param leftX1 Die Koordinaten vom eigenen Objekt
     * @param topY1
     * @param leftX2 Die Koordinaten mit dem eine Collision abgefragt werden soll
     * @param topY2
     * @param speed
     * @return
     */
    public boolean noColisionUp(int leftX1, int topY1, int leftX2, int topY2, int speed) {
        return noCollisionPlayer("up", leftX1, topY1, leftX2, topY2, speed) &&
                (topY1 > 0 || topY1 < gamePanel.getScreenRow() * gamePanel.getTileSize()) &&
                noCollisionWithTiles("up", leftX1, topY1, speed);
    }

    public boolean noCollisionDown(int leftX1, int topY1, int leftX2, int topY2, int speed) {
        return noCollisionPlayer("down", leftX1, topY1, leftX2, topY2, speed) &&
                (topY1 > 0 || topY1 < gamePanel.getScreenRow() * gamePanel.getTileSize()) &&
                noCollisionWithTiles("down", leftX1, topY1, speed);
    }

    public boolean noColisionLeft(int leftX1, int topY1, int leftX2, int topY2, int speed) {
        return noCollisionPlayer("left", leftX1, topY1, leftX2, topY2, speed) &&
                (leftX1 > 0 || leftX1 < gamePanel.getScreenCol() * gamePanel.getTileSize()) &&
                noCollisionWithTiles("left", leftX1, topY1, speed);
    }

    public boolean noColisionRight(int leftX1, int topY1, int leftX2, int topY2, int speed) {
        return noCollisionPlayer("right", leftX1, topY1, leftX2, topY2, speed) &&
                (leftX1 > 0 || leftX1 < gamePanel.getScreenCol() * gamePanel.getTileSize()) &&
                noCollisionWithTiles("right", leftX1, topY1, speed);
    }
}
