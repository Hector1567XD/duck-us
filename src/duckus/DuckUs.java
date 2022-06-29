package duckus;

import duckus.game.engine.GameContainer;
import duckus.game.engine.GameNetwork;
import duckus.game.engine.GameController;
import common.DuckPacketReader;
import common.engine.Scale;
import common.networking.socket.SocketPublisher;
import duckus.networking.Client;
import duckus.game.nodes.Player;

public class DuckUs {
    public static void main(String[] args) {
        // APP BUILDING
        SocketPublisher publisher = new SocketPublisher();
        GameController controller = new GameController();

        Client client = new Client(publisher, new DuckPacketReader());
        client.start("localhost", 1331);

        GameNetwork network = new GameNetwork(client);

        Scale scale = new Scale(32, 2);
        GameContainer container = new GameContainer(scale, network, controller);

        // GAME

        Player player = new Player();
        controller.addNode(player);
        
        // BEGIN
        publisher.subscribe(network);
        container.start();
    }
}
