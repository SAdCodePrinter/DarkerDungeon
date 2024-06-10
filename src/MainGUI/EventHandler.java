package MainGUI;

import entity.Entity;
import java.awt.*;

public class EventHandler {
    GamePanel gamePanel;
    Rectangle eventRect;
    int rectDefaultX, rectDefaultY;

    public EventHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        eventRect = new Rectangle();
        eventRect.x = 4;
        eventRect.y = 3;
        eventRect.width = 2;
        eventRect.height = 2;
        rectDefaultX = eventRect.x;
        rectDefaultY = eventRect.y;
    }

    public void checkEvent(Entity entity, String direction) {
        if (hit(4, 3, entity, direction)) {
            damagePit(entity);
        }
    }

    private void damagePit(Entity entity) {
        System.out.println("AUA");
        entity.life -= 1;
    }

    public boolean hit(int eventCol, int eventRow, Entity entity, String reqDirection) {
        boolean hit = false;

        eventRect.x = eventCol * gamePanel.getTileSize();
        eventRect.y = eventRow * gamePanel.getTileSize();

        if (entity.getRect().intersects(eventRect)) {
            if (entity.direction.contentEquals(reqDirection)) {
                hit = true;
            }
        }

        eventRect.x = rectDefaultX;
        eventRect.y = rectDefaultY;

        return hit;
    }

    public void drawEvent(Graphics2D g) {
        int eventRectX = rectDefaultX * gamePanel.getTileSize();
        int eventRectY = rectDefaultY * gamePanel.getTileSize();
        int eventRectWidth = eventRect.width;
        int eventRectHeight = eventRect.height;

        g.setColor(Color.RED);
        g.drawRect(eventRectX, eventRectY, eventRectWidth, eventRectHeight);
    }
}
