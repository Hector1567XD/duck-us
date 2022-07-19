package client.game.engine.nodos;

import common.game.engine.node.NodeI;

public interface NodeColladable extends NodeI {
    public int getTopCenter();
    public int getLeftCenter();
    public int getRightCenter();
    public int getBottomCenter();
}
