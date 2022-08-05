package client.game.nodes;

import client.game.engine.GameContainer;
import client.game.engine.GameNode;
import client.game.engine.nodos.NodeCenterable;
import java.awt.Color;
import java.awt.Graphics2D;

public class OPlayer extends GameNode implements NodeCenterable {
    private int velocity = 4;
    private int playerId;
    private String name;

    public OPlayer(int playerId, String name) {
        this.playerId = playerId;
        this.name = name;
    }

    @Override
    public void created(GameContainer container) {}

    @Override
    public void update(GameContainer container) {}

    @Override
    public void draw(GameContainer container, Graphics2D g2) {
        g2.setColor(Color.GRAY);
        int scale = container.getScale().getScale();
        int tileSize = container.getScale().getOriginalTileSize();
        g2.fillRect(drawX * scale - getOffsetX(), drawY * scale - getOffsetY(), tileSize * scale, tileSize * scale);
        g2.setColor(Color.WHITE);
        g2.drawString(this.name + "(" + this.playerId + ")", (drawX - 16)*scale, (drawY + 36)* scale);
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getOffsetX() {
        return 16;
    }

    public int getOffsetY() {
        return 16;
    }

    public int getNodeLevel() {
        return 100;
    }
}
