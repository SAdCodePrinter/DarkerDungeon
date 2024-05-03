package MainGUI;

import entity.Player;

public class CollisionHandler {

    public static boolean isColliding(Player player1, Player player2) {
        // Überprüfen, ob sich die Spieler in horizontaler Richtung überlappen
        boolean overlapX = player1.getX() < player2.getX() + player2.getWidth() &&
                player1.getX() + player1.getWidth() > player2.getX();

        // Überprüfen, ob sich die Spieler in vertikaler Richtung überlappen
        boolean overlapY = player1.getY() < player2.getY() + player2.getHeight() &&
                player1.getY() + player1.getHeight() > player2.getY();

        // Es liegt eine Kollision vor, wenn sich die Spieler sowohl horizontal als auch vertikal überlappen
        return overlapX && overlapY;
    }

    public static boolean collisionNextStep(String direction, Player player1, Player player2) {
        //toDO: Collision einbringen

        // Punkte
        int leftX1 = player1.getX();
        int rightX1 = player1.getX() + player1.getWidth();
        int topY1 = player1.getY();
        int bottomY1 = player1.getY() + player1.getHeight();

        int leftX2 = player2.getX();
        int rightX2 = player2.getX() + player2.getWidth();
        int topY2 = player2.getY();
        int bottomY2 = player2.getY() + player2.getHeight();

        // Cols, runter. Rows, seitwärts
        int leftCol1 = leftX1 / player1.getHeight();
        int leftCol1 = leftX1 / player1.getHeight();


        switch (direction) {
            case "up":


        }

        return true;
    }
}
