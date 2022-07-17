package client;

import client.game.engine.GameContainer;
import client.game.engine.GameNetwork;
import client.game.engine.GameController;
import client.game.nodes.PingNode;
import common.networking.DuckPacketReader;
import common.networking.engine.socket.SocketPublisher;
import client.networking.Client;
import client.game.nodes.Player;

public class DuckUs {
    public static void main(String[] args) {
        // APP BUILDING
            // SOCKET Y EL SOCKET PUBLISHER
            SocketPublisher publisher = new SocketPublisher();
            Client client = new Client(publisher, new DuckPacketReader());
            // GAME CONTEXT
            GameController controller = new GameController();
            GameNetwork network = new GameNetwork(client);
            GameContainer container = new GameContainer(Constants.SCALE, network, controller);

        // AGREGANDO NODOS AL JUEGO
            Player player = new Player();
            controller.addNode(player);
            PingNode pingNode = new PingNode();
            controller.addNode(pingNode);
            network.setPingNode(pingNode);

        // EJECUSION
            client.start("localhost", 1331); //<-- Conectandose al servidor
            publisher.subscribe(network); // <-- Subscribiendo el NETWORK a los paquetes del socket
            container.start(); // <-- Ejecutando el juego en si
    }
}
