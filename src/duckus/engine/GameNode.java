package duckus.engine;

import common.engine.node.*;
import common.engine.Container;
import java.awt.Graphics2D;
import java.util.ArrayList;

public abstract class GameNode extends Node {

    public abstract void draw(Container container, Graphics2D g2);

    public void _draw(Container container, Graphics2D g2) {
       draw(container, g2);

       ArrayList<NodeI> childNodes = getChildsNodeList();

       for (NodeI node: childNodes) {
           GameNode gameNode = (GameNode) node;
           gameNode._draw(container, g2);
       }
    }
}
