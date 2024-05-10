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

    public static boolean noCollisionNextStep(String direction, Player player1, Player player2, int speed) {
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

        // toDo: Bei Y < 0 und Y > 715 Kollision mit Border

        if (direction == "up") {
            if (topY1 - speed <= bottomY2 && topY1 - speed > topY2
                    && leftX1 <= rightX2 && rightX1 >= leftX2) {
                System.out.println("KOLLISION");
                return false;
            }
        } else if (direction == "down") {
            if (bottomY1 + speed >= topY2 && bottomY1 + speed < bottomY2
                    && leftX1 <= rightX2 && rightX1 > leftX2) {
                System.out.println("KOLLISION");
                return false;
            }
        } else if (direction == "left") {
            if (leftX1 - speed <= rightX2 && leftX1 - speed > leftX2
                    && topY1 < bottomY2 && bottomY1 > topY2) {
                System.out.println("KOLLISION");
                return false;
            }
        } else if (direction == "right") {
            if (rightX1 + speed >= leftX2 && leftX1 + speed < rightX2
                    && topY1 < bottomY2 && bottomY1 > topY2) {
                System.out.println("KOLLISION");
                return false;
            }
        }

//        switch (direction) {
//            case "up": {
//                if (topY1 + speed >= bottomY2 && topY1 + speed < topY2) {
//                    System.out.println("KOLLISION");
//                    return false;
//                }
//            }
//            case "down": {
//                if (bottomRow1 - speed <= topRow2) {
//                    System.out.println("KOLLISION");
//                    return false;
//                }
//            }
//            case "left": {
//                if (leftCol1 - speed <= rightCol2) {
//                    System.out.println("KOLLISION");
//                    return false;
//                }
//            }
//            case "right": {
//                if (rightCol1 + speed >= leftCol2) {
//                    System.out.println("KOLLISION");
//                    return false;
//                }
//            }
//        }

        return true;

    }
}
