package client.utils.game.collitions;

public class CenterBorders {
    private int topCenter;
    private int bottomCenter;
    private int leftCenter;
    private int rightCenter;

    public CenterBorders(int topCenter, int bottomCenter, int leftCenter, int rightCenter) {
        this.topCenter = topCenter;
        this.bottomCenter = bottomCenter;
        this.leftCenter = leftCenter;
        this.rightCenter = rightCenter;
    }

    public int getTopCenter() {
        return topCenter;
    }

    public void setTopCenter(int topCenter) {
        this.topCenter = topCenter;
    }

    public int getBottomCenter() {
        return bottomCenter;
    }

    public void setBottomCenter(int bottomCenter) {
        this.bottomCenter = bottomCenter;
    }

    public int getLeftCenter() {
        return leftCenter;
    }

    public void setLeftCenter(int leftCenter) {
        this.leftCenter = leftCenter;
    }

    public int getRightCenter() {
        return rightCenter;
    }

    public void setRightCenter(int rightCenter) {
        this.rightCenter = rightCenter;
    }
}
