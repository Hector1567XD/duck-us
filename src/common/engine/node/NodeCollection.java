package common.engine.node;

import java.util.ArrayList;
import java.util.HashMap;

public class NodeCollection {
    private HashMap<String, ArrayList<NodeI>> nodesByTag = new HashMap<String, ArrayList<NodeI>>();
    private HashMap<Integer, NodeI> nodesByID = new HashMap<Integer, NodeI>();
    private ArrayList<NodeI> nodes = new ArrayList<NodeI>();
    private int nodeCounter = 0;

    public void add(NodeI node) {
        nodeCounter++;
        node.setNodeId(nodeCounter);
        
        // Añade el nodo a las distintas listas
        nodes.add(node);
        nodesByID.put(node.getNodeId(), node);

        if (node.getNodeTag() != null) {
            this.addNodeToTag(node, node.getNodeTag());
        }
    }

    private void addNodeToTag(NodeI node, String tag) {
        ArrayList<NodeI> nodesTaggeds;
        if (nodesByTag.containsKey(tag)) {
            // Si ya existe la lista de etiquetas, añade el nodo
            nodesTaggeds = nodesByTag.get(tag);
            nodesTaggeds.add(node);
        }else{
            // Si no existe la lista de etiquetas, creala y añade al nodo
            nodesTaggeds = new ArrayList<NodeI>();
            nodesTaggeds.add(node);
            nodesByTag.put(tag, nodesTaggeds);
        }
    }

    public void remove(NodeI node) {
        int nodeId = node.getNodeId();
        
        // Elimina el nodo de las distintas listas
        nodes.remove(node);
        nodesByID.remove(nodeId);

        if (node.getNodeTag() != null) {
            this.removeNodeFromTag(node, node.getNodeTag());
        }
    }

    private void removeNodeFromTag(NodeI node, String tag) {
        if (nodesByTag.containsKey(tag)) {
            ArrayList<NodeI> nodesTaggeds = nodesByTag.get(tag);
            nodesTaggeds.remove(node);
        }
    }

    public int size() {
        return nodes.size();
    }

    public NodeI getByIndex(int index) {
        return nodes.get(index);
    }

    public ArrayList<NodeI> getNodeList() {
        return nodes;
    }
}
