package MainGUI;

import java.util.Objects;

public class CollisionHandler {
    private final GamePanel gamePanel;

    public CollisionHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public boolean noPlayerCollision(int leftX1, int topY1, int leftX2, int topY2, int tileSize) {

        // toDO: auch mit Tiles so machen
        // Punkte
        int rightX1 = leftX1 + tileSize;
        int bottomY1 = topY1 + tileSize;

        int rightX2 = leftX2 + tileSize;
        int bottomY2 = topY2 + tileSize;

        // Rechts oder links vom Rechteck
        if (leftX1 >= rightX2 || leftX2 >= rightX1) {
            return true;
        }

        // Überprüfen, ob eines der Rechtecke oberhalb des anderen liegt
        if (topY1 >= bottomY2 || topY2 >= bottomY1) {
            return true;
        }

        return false;
    }
    public boolean enemyCollision(int leftX1, int topY1, int leftX2, int topY2, int tileSize) {
        // Punkte
        int rightX1 = leftX1 + tileSize;
        int bottomY1 = topY1 + tileSize;

        int rightX2 = leftX2 + tileSize;
        int bottomY2 = topY2 + tileSize;

        // Rechts oder links vom Rechteck
        if (leftX1 >= rightX2 || leftX2 >= rightX1) {
            return false;
        }

        // Überprüfen, ob eines der Rechtecke oberhalb des anderen liegt
        if (topY1 >= bottomY2 || topY2 >= bottomY1) {
            return false;
        }

        return true;
    }

    public boolean noCollisionPlayer(String direction, int leftX1, int topY1, int leftX2, int topY2, int speed, int tileSize) {

        // Punkte
        int rightX1 = leftX1 + tileSize;
        int bottomY1 = topY1 + tileSize;

        int rightX2 = leftX2 + tileSize;
        int bottomY2 = topY2 + tileSize;


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

    public boolean noColisionWithTiles(String direction, int leftX1, int topY1, int speed, int tileSize) {
        // wird um 2 pro Seite verkleinert, da sonst das errechnete Quadrat nicht zwischen zwei tiles passt
        int rightX1 = leftX1 + tileSize;
        int bottomY1 = topY1 + tileSize;

        // die Rows und Cols - Größen berechnen
        // toDO: Entity col
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
        return x > 0 && x < gamePanel.getScreenCol() * gamePanel.getTileSize() + gamePanel.getTileSize()&&
                y > 0 && y < gamePanel.getScreenRow() * gamePanel.getTileSize() + gamePanel.getTileSize();
    }

    /**
     * @param leftX1 Die Koordinaten vom eigenen Objekt
     * @param topY1
     * @param leftX2 Die Koordinaten mit dem eine Collision abgefragt werden soll
     * @param topY2
     * @param speed
     * @return
     */
    public boolean noColisionUp(int leftX1, int topY1, int leftX2, int topY2, int speed, int tileSize) {

        return noPlayerCollision(leftX1, topY1 - speed, leftX2, topY2, tileSize) &&


                (topY1 > 0 || topY1 < gamePanel.getScreenRow() * gamePanel.getTileSize()) &&
                noColisionWithTiles("up", leftX1, topY1, speed, tileSize);
    }

    public boolean noColisionDown(int leftX1, int topY1, int leftX2, int topY2, int speed, int tileSize) {
        return noCollisionPlayer("down", leftX1, topY1, leftX2, topY2, speed, tileSize) &&
                (topY1 > 0 || topY1 < gamePanel.getScreenRow() * gamePanel.getTileSize()) &&
                noColisionWithTiles("down", leftX1, topY1, speed, tileSize);
    }

    public boolean noColisionLeft(int leftX1, int topY1, int leftX2, int topY2, int speed, int tileSize) {
        return noCollisionPlayer("left", leftX1, topY1, leftX2, topY2, speed, tileSize) &&
                (leftX1 > 0 || leftX1 < gamePanel.getScreenCol() * gamePanel.getTileSize()) &&
                noColisionWithTiles("left", leftX1, topY1, speed, tileSize);
    }

    public boolean noColisionRight(int leftX1, int topY1, int leftX2, int topY2, int speed, int tileSize) {
        return noCollisionPlayer("right", leftX1, topY1, leftX2, topY2, speed, tileSize) &&
                (leftX1 > 0 || leftX1 < gamePanel.getScreenCol() * gamePanel.getTileSize()) &&
                noColisionWithTiles("right", leftX1, topY1, speed, tileSize);
    }

//    public boolean noCollision(int leftX1, int topY1, int leftX2, int topY2, int tileSize) {
//
//    }
}
