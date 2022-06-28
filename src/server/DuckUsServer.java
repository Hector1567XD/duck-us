package server;

import common.DuckPacketReader;
import common.engine.Scale;
import server.engine.ServerContainer;
import server.engine.ServerController;
import server.engine.ServerNetwork;
import server.game.SPlayer;

public class DuckUsServer {

    public static void main(String[] args) {
        ServerController controller = new ServerController();
        ServerNetwork network = new ServerNetwork(new DuckPacketReader());
        Scale scale = new Scale(32, 1);
        ServerContainer container = new ServerContainer(scale, network, controller);

        SPlayer splayer = new SPlayer();
        controller.addNode(splayer);

        container.start();
    }

}
