package common.game.engine.node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class NodeCollection {
    private HashMap<String, ArrayList<NodeI>> nodesByTag;
    private HashMap<Integer, NodeI> nodesByID;
    private HashMap<String, NodeI> nodesByName;
    private ArrayList<NodeI> nodes;
    private int nodeCounter = 0;

    public NodeCollection() {
        this.nodesByTag = new HashMap<>();
        this.nodesByID = new HashMap<>();
        this.nodesByName = new HashMap<>();
        this.nodes = new ArrayList<>();
    }

    public void add(NodeI node) {
        synchronized (nodes) {
            nodeCounter++;
            node.setNodeId(nodeCounter);

            // Añade el nodo a las distintas listas
            nodes.add(node);
            Collections.sort(nodes, NodeComparator.levelComparator);
            nodesByID.put(node.getNodeId(), node);

            if (node.getNodeName() != null) {
                this.nodesByName.put(node.getNodeName(), node);
            }

            if (node.getNodeTag() != null) {
                this.addNodeToTag(node, node.getNodeTag());
            }
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
            nodesTaggeds = new ArrayList<>();
            nodesTaggeds.add(node);
            nodesByTag.put(tag, nodesTaggeds);
        }
    }

    public void remove(NodeI node) {
        synchronized (nodes) {
            int nodeId = node.getNodeId();

            // Elimina el nodo de las distintas listas
            nodes.remove(node);
            Collections.sort(nodes, NodeComparator.levelComparator);
            nodesByID.remove(nodeId);

            if (node.getNodeName() != null) {
                this.nodesByName.remove(node.getNodeName());
            }

            if (node.getNodeTag() != null) {
                this.removeNodeFromTag(node, node.getNodeTag());
            }
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

    public <T extends NodeI> ArrayList<T> getList() {
        return (ArrayList<T>) nodes;
    }

    public <T extends NodeI> T get(int index) {
        return (T) nodes.get(index);
    }

    public <T extends NodeI> T find(int id) {
        return (T) nodesByID.get(id);
    }

    public <T extends NodeI> T findByName(String name) {
        return (T) nodesByName.get(name);
    }

    public <T extends NodeI> ArrayList<T> getListByTag(String nodeTag) {
        if (nodesByTag.containsKey(nodeTag)) {
            return (ArrayList<T>) nodesByTag.get(nodeTag);
        }

        return new ArrayList<>();
    }
}
