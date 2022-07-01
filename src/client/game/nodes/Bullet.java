package client.game.nodes;

import client.game.engine.GameContainer;
import client.game.engine.GameNode;
import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet extends GameNode {
    @Override
    public void created(GameContainer container) {
        //
    }

    @Override
    public void update(GameContainer container) {
        //
    }

    @Override
    public void draw(GameContainer container, Graphics2D g2) {
        int scale = container.getScale().getScale();
        g2.setColor(Color.yellow);
        g2.fillOval(x * scale, y * scale, 8 * scale, 8 * scale);
    }
}
