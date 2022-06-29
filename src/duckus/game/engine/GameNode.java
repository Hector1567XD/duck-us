package duckus.game.engine;

import common.engine.Container;
import common.engine.node.*;
import java.awt.Graphics2D;
import java.util.ArrayList;

public abstract class GameNode extends Node {

    @Override
    public void created(Container container) {
        created((GameContainer) container);
    }

    @Override
    public void update(Container container) {
        update((GameContainer) container);
    }

    public abstract void created(GameContainer container);
    public abstract void update(GameContainer container);

    public void _draw(GameContainer container, Graphics2D g2) {
       draw(container, g2);

       ArrayList<NodeI> childNodes = getChildsNodeList();

       for (NodeI node: childNodes) {
           GameNode gameNode = (GameNode) node;
           gameNode._draw(container, g2);
       }
    }

    public abstract void draw(GameContainer container, Graphics2D g2);
}
