package MainGUI;


public class CollisionHandler {
    private final GamePanel gamePanel;

    public CollisionHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public boolean noPlayerCollision(int leftX1, int topY1, int leftX2, int topY2, int tileSize) {
        // Punkte
        int rightX1 = leftX1 + tileSize;
        int bottomY1 = topY1 + tileSize;

        int rightX2 = leftX2 + tileSize;
        int bottomY2 = topY2 + tileSize;

        // Rechts oder links vom Rechteck
        if (leftX1 > rightX2 || leftX2 > rightX1) {
            return true;
        }

        // Überprüfen, ob eines der Rechtecke oberhalb des anderen liegt
        if (topY1 > bottomY2 || topY2 > bottomY1) {
            return true;
        }

        return false;
    }

    public boolean entityCollision(int leftX1, int topY1, int leftX2, int topY2, int tileSize) {
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


    public boolean noColisionWithTiles(String direction, int leftX1, int topY1, int speed, int tileSize) {
        // wird um 2 pro Seite verkleinert, da sonst das errechnete Quadrat nicht zwischen zwei tiles passt

        int rightX1 = leftX1 + tileSize;
        int bottomY1 = topY1 + tileSize;

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
        return x > 0 && x < gamePanel.getScreenCol() * gamePanel.getTileSize() + gamePanel.getTileSize() &&
                y > 0 && y < gamePanel.getScreenRow() * gamePanel.getTileSize() + gamePanel.getTileSize();
    }

    public boolean noColisionUp(int leftX1, int topY1, int leftX2, int topY2, int speed, int tileSize) {
        return noPlayerCollision(leftX1, topY1 - speed, leftX2, topY2, tileSize) &&
                (topY1 > 0 || topY1 < gamePanel.getScreenRow() * gamePanel.getTileSize()) &&
                noColisionWithTiles("up", leftX1, topY1, speed, tileSize);
    }

    public boolean noColisionDown(int leftX1, int topY1, int leftX2, int topY2, int speed, int tileSize) {
        return noPlayerCollision(leftX1, topY1 + speed, leftX2, topY2, tileSize) &&
                (topY1 > 0 || topY1 < gamePanel.getScreenRow() * gamePanel.getTileSize()) &&
                noColisionWithTiles("down", leftX1, topY1, speed, tileSize);
    }

    public boolean noColisionLeft(int leftX1, int topY1, int leftX2, int topY2, int speed, int tileSize) {
        return noPlayerCollision(leftX1 - speed, topY1, leftX2, topY2, tileSize) &&
                (leftX1 > 0 || leftX1 < gamePanel.getScreenCol() * gamePanel.getTileSize()) &&
                noColisionWithTiles("left", leftX1, topY1, speed, tileSize);
    }

    public boolean noColisionRight(int leftX1, int topY1, int leftX2, int topY2, int speed, int tileSize) {
        return noPlayerCollision(leftX1 + speed, topY1, leftX2, topY2, tileSize) &&
                (leftX1 > 0 || leftX1 < gamePanel.getScreenCol() * gamePanel.getTileSize()) &&
                noColisionWithTiles("right", leftX1, topY1, speed, tileSize);
    }

}
