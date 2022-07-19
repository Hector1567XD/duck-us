package client.game.nodes;
import client.game.engine.GameContainer;
import client.game.engine.nodos.AbstractCamera;
import java.awt.Color;
import java.awt.Graphics2D;

public class Camera extends AbstractCamera {

    public Camera(GameContainer container) {
        super(container, 0, 0);
    }

    @Override
    public void created(GameContainer container) {
        screenX = (container.getScale().getTileSize() * container.getMaxMapCol() )/2;
        screenY = (container.getScale().getTileSize() * container.getMaxMapRow() )/2;
    }

    @Override
    public void update(GameContainer container) {
        // Camara fija bb :3
        Player player = container.getController().getNodes().findByName("Player");
        this.x = player.getX();
        this.y = player.getY();
    }

    @Override
    public void draw(GameContainer container, Graphics2D g2) {
        int scale = container.getScale().getScale();
        g2.setColor(Color.orange);
        g2.fillRect(screenX, screenY, 2 * scale, 2 * scale);
    }
}
