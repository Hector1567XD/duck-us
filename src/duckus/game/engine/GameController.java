package duckus.game.engine;

import common.engine.Controller;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameController extends Controller {
    public void draw(GameContainer container, Graphics2D g2) {
        ArrayList<GameNode> nodes = this.getNodes().getList();

        for (GameNode node: nodes) {
            node._draw(container, g2);
        }
    }
}
