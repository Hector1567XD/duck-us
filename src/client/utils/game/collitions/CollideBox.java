package client.utils.game.collitions;

public class CollideBox {
    private int x1;
    private int x2;
    private int y1;
    private int y2;

    public CollideBox(int x1, int x2, int y1, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    // METHODS

    public boolean isCollidingWith(CollideBox box2) {
        CollideBox box1 = this;

        if ((box1.getX2() >= box2.getX1()) && (box1.getX1() <= box2.getX2()) && (box1.getY2() >= box2.getY1()) && (box1.getY1() <= box2.getY2())) {
            return true;
        }

        return false;
    }

    // GETTERS / SETTERS

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    @Override
    public String toString() {
        return "CollideBox{" + "x1=" + x1 + ", x2=" + x2 + ", y1=" + y1 + ", y2=" + y2 + '}';
    }
}
