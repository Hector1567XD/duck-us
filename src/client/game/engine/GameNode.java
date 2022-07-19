package client.game.engine;

import client.game.engine.nodos.AbstractCamera;
import common.game.engine.node.NodeI;
import common.game.engine.node.Node;
import common.game.engine.Container;
import java.awt.Graphics2D;
import java.util.ArrayList;

public abstract class GameNode extends Node {
    // ESTAS 2 PROPIEDADES SOLO SE DEBEN USAR EN EL DRAW
    protected int drawX = 0; // Posicion X del nodo TENIENDO EN CUENTA LA CAMARA
    protected int drawY = 0; // Posicion Y del nodo TENIENDO EN CUENTA LA CAMARA

    @Override
    public void created(Container container) {
        created((GameContainer) container);
    }

    public int getDrawX() {
        return drawX;
    }

    public int getDrawY() {
        return drawY;
    }

    @Override
    public void update(Container container) {
        update((GameContainer) container);
    }

    public abstract void created(GameContainer container);
    public abstract void update(GameContainer container);

    public void _draw(GameContainer container, Graphics2D g2) {
       // Calculamos la posicion mapX y mapY respecto a la camara
       this.drawX = this.x;
       this.drawY = this.y;
       // Obtenemos la camera (si es que existe)
       AbstractCamera camera = container.getController().getCamera();
       if (camera != null) {
            this.drawX = this.x + camera.getDeltaCameraX();
            this.drawY = this.y + camera.getDeltaCameraY();
       }
       // Ejecutamos mi propio DRAW
       draw(container, g2);
       // Ejecutamos el DRAW de nuestros nodos hijos
       ArrayList<NodeI> childNodes = getChildsNodeList();
       for (NodeI node: childNodes) {
           GameNode gameNode = (GameNode) node;
           gameNode._draw(container, g2);
       }
    }

    public abstract void draw(GameContainer container, Graphics2D g2);
}
