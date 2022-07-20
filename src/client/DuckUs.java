package client;

import client.game.engine.GameContainer;
import client.game.engine.GameNetwork;
import client.game.engine.GameController;
import client.game.nodes.Camera;
import common.networking.DuckPacketReader;
import common.networking.engine.socket.SocketPublisher;
import client.networking.Client;
import client.game.nodes.MapNode;
import client.game.nodes.Player;
import client.game.nodes.Triangulito;


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
        
        controller.addNode(player, "Player");
        // LA CAMARA SIEMPRE DEBE IR DESPUES DEL JUGADOR
        controller.setCamera(new Camera(container));
        
        
        
        controller.addNode(new Triangulito());
        
        
        
        // BEGIN
        publisher.subscribe(network);
        container.start();
    }
}
