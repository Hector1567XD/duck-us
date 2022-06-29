package server;

import common.DuckPacketReader;
import common.game.engine.Scale;
import common.networking.socket.SocketPublisher;
import server.game.engine.ServerContainer;
import server.game.engine.ServerController;
import server.game.engine.ServerNetwork;
import server.networking.Server;
import server.game.nodes.SPlayer;

public class DuckUsServer {

    public static void main(String[] args) {
        // APP BUILDING
        SocketPublisher publisher = new SocketPublisher();
        ServerController controller = new ServerController();

        Server server = new Server(publisher, new DuckPacketReader());
        server.start(1331);

        ServerNetwork network = new ServerNetwork(server);

        Scale scale = new Scale(32, 1);
        ServerContainer container = new ServerContainer(scale, network, controller);

        // GAME
  
        SPlayer splayer = new SPlayer();
        controller.addNode(splayer);

        // BEGIN
        publisher.subscribe(network);
        container.start();
    }

}
