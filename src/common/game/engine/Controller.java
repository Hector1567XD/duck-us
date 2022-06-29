package common.game.engine;

import common.game.engine.node.Node;
import common.game.engine.node.NodeCollection;
import common.game.engine.node.NodeI;

public abstract class Controller {
    private NodeCollection nodes = new NodeCollection();

    public void update(Container container) {
        for (int i = 0; i < nodes.size(); i ++) {
            NodeI node = nodes.get(i);

            // Si el nodo no ha ejecutado su creacion, la ejecuta
            if (!node.isCreated()) { node._created(container); }

            // Ejecuta el actualizador del nodo
            node._update(container);

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

    public NodeCollection getNodes() {
        return nodes;
    }
}
