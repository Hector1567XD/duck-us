package client.game.engine.nodos;

import client.utils.game.collitions.CenterBorders;
import client.utils.game.collitions.CollideBox;
import common.game.engine.node.NodeI;

public interface NodeColladable extends NodeI {
    public CollideBox getCollideBox();
    public CenterBorders getCenterBorders();
}
