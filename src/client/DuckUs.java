package client;

import client.game.engine.GameContainer;
import client.game.engine.GameNetwork;
import client.game.engine.GameController;
import client.game.nodes.Bloque;
import common.networking.DuckPacketReader;
import common.networking.engine.socket.SocketPublisher;
import client.networking.Client;
import client.game.nodes.MapNode;
import client.game.nodes.Player;
import client.game.nodes.Prueba;


public class DuckUs {
    public static void main(String[] args) {
        // APP BUILDING
        SocketPublisher publisher = new SocketPublisher();
        GameController controller = new GameController();
        

        Client client = new Client(publisher, new DuckPacketReader());
        client.start("localhost", 1331);

        GameNetwork network = new GameNetwork(client);

        GameContainer container = new GameContainer(Constants.SCALE, network, controller);

        // GAME

        Player player = new Player();
        MapNode mapa = new MapNode(container);
        controller.addNode(mapa);
        controller.addNode(player);
        controller.addNode(new Bloque(100,300));
        controller.addNode(new Bloque(50,50));
        controller.addNode(new Bloque(300,300));
        

        controller.addNode(new Prueba());

        // BEGIN
        publisher.subscribe(network);
        container.start();
    }
}
