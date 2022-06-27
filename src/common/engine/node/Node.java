package common.engine.node;

import common.engine.Container;
import java.util.ArrayList;

public abstract class Node implements NodeI {

    protected int x = 0, y = 0;
    private boolean removed = false;
    private boolean created = false;
    private ArrayList<NodeI> childNodes = new ArrayList();
    private int nodeId = 0;

    @Override
    public void addNode(NodeI node) {
        childNodes.add(node);
    }

    @Override
    public void _created(Container container) {
        created = true;
        created(container);
        for (NodeI node: childNodes) {
            node._created(container);
        }
    }

    @Override
    public void _update(Container container) {
        update(container);
        for (int i = 0; i < childNodes.size(); i ++) {
            NodeI node = childNodes.get(i);

            // Si el nodo no ha ejecutado su creacion, la ejecuta
            if (!node.isCreated()) { node._created(container); }

            // Ejecuta el actualizador del nodo
            node._update(container);

            // Si el nodo hijo fue eliminado, lo elimina de la lista
            if (node.isRemoved()) {
                childNodes.remove(i);
                i--;
            }
        }
    }

    // GETTERS
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public boolean isRemoved() {
        return removed;
    }
    public void remove() {
        removed = true;
    }
    public boolean isCreated() {
        return created;
    }

    // CONTROL
    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public int getNodeId() {
        return nodeId;
    }

    public String getNodeTag() {
        return null;
    }
}
