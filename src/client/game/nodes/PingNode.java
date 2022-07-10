package client.game.nodes;

import client.game.engine.GameContainer;
import client.game.engine.GameNode;
import common.networking.packets.PingPacket;
import java.awt.Graphics2D;

public class PingNode extends GameNode {  
    @Override
    public void created(GameContainer container) {
        container.getNetwork().sendPacket(new PingPacket());
    }

    @Override
    public void update(GameContainer container) {}

    @Override
    public void draw(GameContainer container, Graphics2D g2) {}
}
