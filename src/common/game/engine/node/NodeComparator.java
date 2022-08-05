package common.game.engine.node;
import java.util.Comparator;

public class NodeComparator {
    public static Comparator<NodeI> levelComparator = new Comparator<NodeI>() {
        // Method
        public int compare(NodeI n1, NodeI n2) {
            int n1Level = n1.getNodeLevel();
            int n2Level = n2.getNodeLevel();
            // For ascending order
            return n1Level - n2Level;
        }
    };
}
