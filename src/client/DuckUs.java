package client;

import client.game.engine.GameContainer;
import client.game.engine.GameNetwork;
import client.game.engine.GameController;
import common.networking.DuckPacketReader;
import common.game.engine.Scale;
import common.networking.engine.socket.SocketPublisher;
import client.networking.Client;
import client.game.nodes.Player;
import common.networking.engine.Agent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DuckUs {
    public static void main(String[] args) {
        // APP BUILDING
        SocketPublisher publisher = new SocketPublisher();
        GameController controller = new GameController();

        Client client = new Client(publisher, new DuckPacketReader());
        client.start("localhost", 1331);

        GameNetwork network = new GameNetwork(client);

        Scale scale = new Scale(32, 1);
        GameContainer container = new GameContainer(scale, network, controller);

        // GAME

        Player player = new Player();
        controller.addNode(player);

        // BEGIN
        publisher.subscribe(network);
        container.start();
    }
}
