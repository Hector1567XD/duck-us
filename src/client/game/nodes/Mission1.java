package client.game.nodes;

import client.game.engine.GameContainer;
import client.game.engine.GameNode;
import client.game.engine.nodos.NodeCenterable;
import client.game.engine.nodos.NodeColladable;
import java.awt.Color;
import java.awt.Graphics2D;

public class Mission1 extends GameNode implements NodeCenterable, NodeColladable {
  //  private boolean misionAbierta = false;

    public Mission1(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void created(GameContainer container) {
    }

    @Override
    public void update(GameContainer container) {
    }

    @Override
    public void draw(GameContainer container, Graphics2D g2) {
        g2.setColor(Color.white);
        int alto = x;
        int ancho = y;
        int offSetX = this.getOffsetX() ;
        int offSetY = this.getOffsetY() ;
    
        g2.fillRect((drawX) - offSetX, (drawY) - offSetY, alto, ancho);
    }

    public int getOffsetX() {
        return 16;
    }

    public int getOffsetY() {
        return 16;
    }

    @Override
    public int getTopCenter() {
        return 16;
    }

    @Override
    public int getLeftCenter() {
        return 16;
    }

    @Override
    public int getRightCenter() {
        return 16;
    }

    @Override
    public int getBottomCenter() {
        return 16;
    }
}
