package client.utils.game.collitions;

import java.awt.Graphics2D;

public class CollitionsUtils {
    public static boolean isPositionColliding(int x, int y, CollideBox box) {
        CollideBox singlePositionBox = new CollideBox(x,x,y,y);
        return singlePositionBox.isCollidingWith(box);
    }

    public static boolean isBoxColliding(CollideBox box1, CollideBox box2) {
        return box1.isCollidingWith(box2);
    }

    public static CollideBox createCenteredBox(int x, int y, CenterBorders borders) {
        int x1 = x - borders.getLeftCenter();
        int x2 = x + borders.getRightCenter();
        int y1 = y - borders.getTopCenter();
        int y2 = y + borders.getBottomCenter();
        return new CollideBox(x1, x2, y1, y2);
    }

    public static void drawCollideBox(Graphics2D g2, CollideBox collideBox, int scale) {
        CollitionsUtils.drawCollideBox(g2, collideBox, scale, 0, 0);
    }

    public static void drawCollideBox(Graphics2D g2, CollideBox collideBox, int scale, int deltaCameraX, int deltaCameraY) {
        int x1 = collideBox.getX1()*scale + deltaCameraX;
        int y1 = collideBox.getY1()*scale + deltaCameraY;
        int x2 = collideBox.getX2()*scale - collideBox.getX1()*scale;
        int y2 = collideBox.getY2()*scale - collideBox.getY1()*scale;
        g2.fillRect(x1, y1, x2, y2);
    }
}
