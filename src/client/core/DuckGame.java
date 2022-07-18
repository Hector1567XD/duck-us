
package client.core;

import client.Constants;
import client.game.engine.GameContainer;
import client.game.engine.GameController;
import client.game.engine.GameNetwork;
import client.game.nodes.MapNode;
import client.game.nodes.Player;
import client.networking.Client;
import common.networking.engine.socket.SocketPublisher;

public class DuckGame {
    public static void start(SocketPublisher publisher, Client client) {
        // APP GAME BUILDING
        GameController controller = new GameController();
        GameNetwork network = new GameNetwork(client);
        GameContainer container = new GameContainer(Constants.SCALE, network, controller);
        // GAME
        Player player = new Player();
        MapNode mapa = new MapNode(container);
        controller.addNode(mapa);
        controller.addNode(player);
        // BEGIN
        publisher.subscribe(network);
        container.start();
    }
}
