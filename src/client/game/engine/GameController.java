package client.game.engine;

import common.game.engine.Controller;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameController extends Controller {
    public void draw(GameContainer container, Graphics2D g2) {
        ArrayList<GameNode> nodes = this.getNodes().getList();//Obtiene un arrayList de GameNode y ejecuta su metodo draw

        for (GameNode node: nodes) {
            node._draw(container, g2);
        }
    }
}
