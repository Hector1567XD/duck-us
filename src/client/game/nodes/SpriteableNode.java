package client.game.nodes;

import java.awt.image.BufferedImage;
import common.game.engine.node.NodeI;

public interface SpriteableNode extends NodeI {
    public int getWidth();
    public int getHeight();
    public int getOffsetX();
    public int getOffsetY();
}
