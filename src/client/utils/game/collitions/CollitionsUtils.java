package client.utils.game.collitions;

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
}
