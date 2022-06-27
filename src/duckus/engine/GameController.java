package duckus.engine;

import common.engine.Controller;
import common.engine.node.NodeI;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameController extends Controller {
    public void draw(GameContainer container, Graphics2D g2) {
        ArrayList<NodeI> nodes = (ArrayList<NodeI>) this.getNodeList();

        for (NodeI node: nodes) {
            GameNode gameNode = (GameNode) node;
            gameNode._draw(container, g2);
        }
    }
}
