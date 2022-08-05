package common.game.engine.node;

import common.game.engine.Container;

public interface NodeI {
    // GESTION DE NODOS HIJOS
    public void addNode(NodeI node);

    // EVENTOS INTERNOS
    public void _created(Container container);
    public void _update(Container container);
    public void _removed(Container container);

    // PROPIEDADES
        // COORDENADAS
        public int getX();
        public void setX(int x);
        public int getY();
        public void setY(int y);
        // ELIMINACION DE NODO
        public boolean isRemoved();
        public void remove();
        // CREACION DE NODO
        public boolean isCreated();
        // PROPIEDADES DE CONTROL
        public void setNodeId(int nodeId);
        public int getNodeId();
        public String getNodeTag();
        public void setNodeName(String nodeName);
        public String getNodeName();
        // PRIORIDAD DEL NODO
        public int getNodeLevel();
}
