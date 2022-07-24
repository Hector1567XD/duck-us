package client.game.engine;

import client.game.engine.nodos.AbstractCamera;
import common.game.engine.Controller;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameController extends Controller {
    private AbstractCamera camera = null;

    public void setCamera(AbstractCamera camera) {
        this.camera = camera;
        this.addNode(camera);
    }

    public AbstractCamera getCamera() {
        return camera;
    }

    public void draw(GameContainer container, Graphics2D g2) {
        ArrayList<GameNode> nodes = this.getNodes().getList();

        synchronized (nodes) {
            for (GameNode node: nodes) {
                node._draw(container, g2);
            }
        }
    }
}
