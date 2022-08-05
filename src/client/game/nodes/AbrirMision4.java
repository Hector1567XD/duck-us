/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client.game.nodes;

import client.game.engine.GameContainer;
import client.game.engine.GameNode;
import client.game.engine.nodos.NodeCenterable;
import client.game.engine.nodos.NodeColladable;
import client.utils.game.collitions.CenterBorders;
import client.utils.game.collitions.CollideBox;
import client.utils.game.collitions.CollitionsUtils;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author david_000
 */
public class AbrirMision4 extends GameNode implements NodeCenterable, NodeColladable, NodeOpenable{
    private boolean misionAbierta = false;
    Mision4 misionActual;
    private boolean ganaste;

    public AbrirMision4(int x, int y) {
        this.x = x;
        this.y = y;
        this.misionActual = new Mision4(this);
        this.addNode(misionActual);
    }

    @Override
    public boolean getMisionAbierta() {
        return misionAbierta;
    }

    @Override
    public void setMisionAbierta(boolean misionAbierta) {
        this.misionAbierta = misionAbierta;
    }
    
    @Override
    public boolean isGanaste() {
        return ganaste;
    }

    @Override
    public void setGanaste(boolean ganaste) {
        this.ganaste = ganaste;
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
    public CenterBorders getCenterBorders() {
        return new CenterBorders(16, 16, 16, 16);
    }
    
    @Override
    public CollideBox getCollideBox() {
        CenterBorders centerBorders = this.getCenterBorders();
        return CollitionsUtils.createCenteredBox(x, y, centerBorders);
    }
}
