package common.engine.node;

import java.util.ArrayList;
import java.util.HashMap;

public class NodeCollection {
    private HashMap<String, ArrayList<Node>> nodesByTag;
    private HashMap<Integer, Node> nodesByID;
    private ArrayList<Node> nodes;
    private int nodeCounter = 0;

    public void add(Node node) {
        nodeCounter++;
        node.setNodeId(nodeCounter);
        
        // Añade el nodo a las distintas listas
        nodes.add(node);
        nodesByID.put(node.getNodeId(), node);

        if (node.getNodeTag() != null) {
            this.addNodeToTag(node, node.getNodeTag());
        }
    }

    private void addNodeToTag(Node node, String tag) {
        ArrayList<Node> nodesTaggeds;
        if (nodesByTag.containsKey(tag)) {
            // Si ya existe la lista de etiquetas, añade el nodo
            nodesTaggeds = nodesByTag.get(tag);
            nodesTaggeds.add(node);
        }else{
            // Si no existe la lista de etiquetas, creala y añade al nodo
            nodesTaggeds = new ArrayList<Node>();
            nodesTaggeds.add(node);
            nodesByTag.put(tag, nodesTaggeds);
        }
    }

    public void remove(Node node) {
        int nodeId = node.getNodeId();
        
        // Elimina el nodo de las distintas listas
        nodes.remove(node);
        nodesByID.remove(nodeId);

        if (node.getNodeTag() != null) {
            this.removeNodeFromTag(node, node.getNodeTag());
        }
    }

    private void removeNodeFromTag(Node node, String tag) {
        if (nodesByTag.containsKey(tag)) {
            ArrayList<Node> nodesTaggeds = nodesByTag.get(tag);
            nodesTaggeds.remove(node);
        }
    }

    public int size() {
        return nodes.size();
    }

    public Node getByIndex(int index) {
        return nodes.get(index);
    }
}
