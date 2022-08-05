package client.game.executors;

import client.game.engine.GameContainer;
import client.game.engine.GameNetwork;
import common.networking.packets.*;


public class ServerPongExecutor{
    public static void execute(GameContainer container, PongPacket packet) {
        GameNetwork network = container.getNetwork();
        network.getPingNode().onServerPong(container);
    }
}
