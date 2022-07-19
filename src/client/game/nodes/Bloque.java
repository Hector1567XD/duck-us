package client.game.nodes;

import client.game.engine.GameContainer;
import client.game.engine.GameNode;
import client.game.engine.nodos.NodeCenterable;
import client.game.engine.nodos.NodeColladable;
import java.awt.Color;
import java.awt.Graphics2D;
/**
 *
 * @author hecto
 */
public class Bloque extends GameNode implements NodeCenterable, NodeColladable {

    public Bloque(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void created(GameContainer container) {
    }

    @Override
    public void update(GameContainer container) {}

    @Override
    public void draw(GameContainer container, Graphics2D g2) {
        int scale = container.getScale().getScale();
        int tileSize = container.getScale().getOriginalTileSize();
        g2.setColor(Color.WHITE);
        int alto = tileSize * scale;
        int ancho = tileSize * scale;
        int offSetX = this.getOffsetX() * scale;
        int offSetY = this.getOffsetY() * scale;
        
        g2.fillRect((x * scale) - offSetX, (y * scale) - offSetY, alto, ancho);
        g2.setColor(Color.red);
        g2.fillRect(x * scale, y * scale, 2 * scale, 2 * scale);
    }

    public int getOffsetX() {
        return 16;
    }

    public int getOffsetY() {
        return 16;
    }

    @Override
    public String getNodeTag() {
        return "Bloque";
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
