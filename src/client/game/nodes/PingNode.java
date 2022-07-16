package client.game.nodes;

import client.game.engine.GameContainer;
import client.game.engine.GameNetwork;
import client.game.engine.GameNode;
import common.networking.packets.PingPacket;
import java.awt.Graphics2D;

public class PingNode extends GameNode {  
    
    int framesFromLastPong = 0;
    
    @Override
    public void created(GameContainer container) {}

    @Override
    public void update(GameContainer container) {
        framesFromLastPong++;
    }

    @Override
    public void draw(GameContainer container, Graphics2D g2) {}

    public void onServerPong(GameContainer container) {
        GameNetwork network = container.getNetwork();
        // Limpiamos desde el ultimo ping
        framesFromLastPong = 0;
        // Enviamos pong
        network.sendPacket(new PingPacket());
    }
}
