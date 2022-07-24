package client.game.nodes;

import client.game.engine.nodos.NodeColladable;
import common.game.engine.node.NodeI;

public interface NodeOpenable extends NodeColladable {
    public boolean getMisionAbierta();
    public void setMisionAbierta(boolean misionAbierta);

}