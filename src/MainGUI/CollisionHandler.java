package MainGUI;

import entity.Player;
import entity.Player2;

public class CollisionHandler {

    public static boolean isColliding(Player player1, Player2 player2) {
        // Überprüfen, ob sich die Spieler in horizontaler Richtung überlappen
        boolean overlapX = player1.getX() < player2.getX() + player2.getWidth() &&
                player1.getX() + player1.getWidth() > player2.getX();

        // Überprüfen, ob sich die Spieler in vertikaler Richtung überlappen
        boolean overlapY = player1.getY() < player2.getY() + player2.getHeight() &&
                player1.getY() + player1.getHeight() > player2.getY();

        // Es liegt eine Kollision vor, wenn sich die Spieler sowohl horizontal als auch vertikal überlappen
        return overlapX && overlapY;
    }
}
