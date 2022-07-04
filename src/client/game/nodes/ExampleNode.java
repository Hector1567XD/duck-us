package client.game.nodes;

import client.game.engine.GameContainer;
import client.game.engine.GameNode;
import java.awt.Color;
import java.awt.Graphics2D;

public class ExampleNode extends GameNode {
    public ExampleNode(int x, int y) {
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
        int scale = container.getScale().getScale();
        int tileSize = container.getScale().getOriginalTileSize();
        g2.setColor(Color.green);
        g2.fillRect(x * scale, y * scale, tileSize * scale, tileSize * scale);
    }
    
    @Override
    public String getNodeTag() {
        return "ExampleNode";
    }
}
