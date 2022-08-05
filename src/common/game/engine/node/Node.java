package common.game.engine.node;

import common.game.engine.Container;
import java.util.ArrayList;

public abstract class Node implements NodeI {

    protected int x = 0, y = 0;
    private boolean removed = false;
    private boolean created = false;
    private ArrayList<NodeI> childNodes = new ArrayList();
    private int nodeId = 0;
    private String nodeName;
    
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
    public void _removed(Container container) {
        removed(container);
        for (NodeI node: childNodes) {
            node._removed(container);
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
                node._removed(container);
                i--;
            }
        }
    }

    // EVENTOS EXTERNOS
    public abstract void created(Container container);
    public abstract void update(Container container);
    public void removed(Container container) {
        // Optional method
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

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }
    
    public String getNodeName() {
        return nodeName;
    }

    public ArrayList<NodeI> getChildsNodeList() {
        return childNodes;
    }

    @Override
    public String toString() {
        return "Node{" + "id=" + nodeId + ", x=" + x + ", y=" + y + ", tag=" + getNodeTag() + ", name=" + getNodeName() + ", removed=" + removed + ", childs=" + childNodes + '}';
    }

    public int getNodeLevel() {
        return 0;
    }
}
