package common.engine;

import common.engine.node.Node;
import common.engine.node.NodeCollection;
import common.engine.node.NodeI;
import java.util.ArrayList;

public abstract class Controller {
    private NodeCollection nodes = new NodeCollection();

    public void update(Container gc) {
        for (int i = 0; i < nodes.size(); i ++) {
            NodeI node = nodes.getByIndex(i);

            // Si el nodo no ha ejecutado su creacion, la ejecuta
            if (!node.isCreated()) { node._created(gc); }

            // Ejecuta el actualizador del nodo
            node._update(gc);

            // Si el nodo hijo fue eliminado, lo elimina de la lista
            if (node.isRemoved()) {
                nodes.remove(node);
                i--;
            }
        }
    }

    public void addNode(Node node) {
        nodes.add(node);
    }
    
    public ArrayList<NodeI> getNodeList() {
        return nodes.getNodeList();
    }
}
