package client;

import client.game.engine.GameContainer;
import client.game.engine.GameNetwork;
import client.game.engine.GameController;
import client.game.nodes.Cuadrado;
import client.game.nodes.Jugador;
import common.DuckPacketReader;
import common.game.engine.Scale;
import common.networking.socket.SocketPublisher;
import client.networking.Client;
import client.game.nodes.Player;

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

        /*Player player = new Player();
        controller.addNode(player);*/
        
        // BEGIN
        publisher.subscribe(network);
        container.start();
        System.out.println("Juego iniciado");

        container.getController().addNode(new Jugador());
        //container.getController().addNode(new Cuadrado());
    }
}
