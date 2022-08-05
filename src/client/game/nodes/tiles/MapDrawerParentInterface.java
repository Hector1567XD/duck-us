package client.game.nodes.tiles;

import java.awt.Graphics2D;

public interface MapDrawerParentInterface {
    public void draw(Graphics2D g2, int x, int y);
    public int getWorldCols();
    public int getWorldRows();
}
