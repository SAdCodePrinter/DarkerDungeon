package MainGUI;

import entity.Player;

import java.util.Objects;

public class CollisionHandler {
    public static boolean noCollisionNextStep(String direction, Player player1, Player player2, int speed) {

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
                System.out.println("KOLLISION");
                return false;
            }
        } else if (Objects.equals(direction, "down")) {
            if (bottomY1 + speed >= topY2 && bottomY1 + speed < bottomY2
                    && leftX1 <= rightX2 && rightX1 > leftX2) {
                System.out.println("KOLLISION");
                return false;
            }
        } else if (Objects.equals(direction, "left")) {
            if (leftX1 - speed <= rightX2 && leftX1 - speed > leftX2
                    && topY1 < bottomY2 && bottomY1 > topY2) {
                System.out.println("KOLLISION");
                return false;
            }
        } else if (Objects.equals(direction, "right")) {
            if (rightX1 + speed >= leftX2 && leftX1 + speed < rightX2
                    && topY1 < bottomY2 && bottomY1 > topY2) {
                System.out.println("KOLLISION");
                return false;
            }
        }

        return true;
    }
}
