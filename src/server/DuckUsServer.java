package server;

import common.networking.DuckPacketReader;
import common.networking.engine.socket.SocketPublisher;
import server.game.engine.ServerContainer;
import server.game.engine.ServerController;
import server.game.engine.ServerNetwork;
import server.game.nodes.PongNode;
import server.networking.Server;

public class DuckUsServer {

    public static void main(String[] args) {
        // APP BUILDING
            // SOCKET Y EL SOCKET PUBLISHER
            SocketPublisher publisher = new SocketPublisher();
            Server server = new Server(publisher, new DuckPacketReader());
            // GAME CONTEXT
            ServerController controller = new ServerController();
            ServerNetwork network = new ServerNetwork(server);
            ServerContainer container = new ServerContainer(Constants.SCALE, network, controller);
            
        // GAME
            PongNode pongNode = new PongNode();
            controller.addNode(pongNode);
            network.setPongNode(pongNode);
            
        // BEGIN
            server.start(1331);
            publisher.subscribe(network);
            container.start();
    }

}
