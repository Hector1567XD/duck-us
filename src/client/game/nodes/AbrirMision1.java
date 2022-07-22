package client.game.nodes;

import client.game.engine.GameContainer;
import client.game.engine.GameNode;
import client.game.engine.nodos.NodeCenterable;
import client.game.engine.nodos.NodeColladable;
import java.awt.Color;
import java.awt.Graphics2D;

public class AbrirMision1 extends GameNode implements NodeCenterable, NodeColladable {
    private boolean misionAbierta = false;
    Mision misionActual;
    
    public AbrirMision1(int x, int y, Mision mision) {
        this.x = x;
        this.y = y;
        this.misionActual = new Mision(this);
        this.misionActual = misionActual;
        this.addNode(misionActual);
    }

    public boolean getMisionAbierta() {
        return misionAbierta;
    }

    public void setMisionAbierta(boolean misionAbierta) {
        this.misionAbierta = misionAbierta;
    }

    @Override
    public void created(GameContainer container) {
    }

    @Override
    public void update(GameContainer container) {
       
           
    }

    @Override
    public void draw(GameContainer container, Graphics2D g2) {

        int scale = container.getScale().getScale();
        int tileSize = container.getScale().getOriginalTileSize();
        g2.setColor(Color.green);
        int alto = tileSize * scale;
        int ancho = tileSize * scale;
        int offSetX = this.getOffsetX() * scale;
        int offSetY = this.getOffsetY() * scale;

        g2.fillOval((drawX) - offSetX, (drawY) - offSetY, alto, ancho);

        if (getMisionAbierta() == true) {
            this.misionActual.setAbrir(true);
            this.misionActual.draw(container, g2);
        }
        if (getMisionAbierta() == false) {
            this.misionActual.setAbrir(false);
        }
    }

    public int getOffsetX() {
        return 16;
    }

    public int getOffsetY() {
        return 16;
    }

    @Override
    public String getNodeTag() {
        return "mission";
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
