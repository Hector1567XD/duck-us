package client.game.nodes;

import client.game.engine.nodos.NodeColladable;
import common.game.engine.node.NodeI;

public interface NodeOpenable extends NodeColladable {
    public boolean getMisionAbierta();
    public void setMisionAbierta(boolean misionAbierta);
    
    // Nuevo metodo de la interface :3
    public void setIsCercaPlayer(boolean isCercaPlayer);
    public boolean isGanaste();
    public void setGanaste(boolean ganaste);
}