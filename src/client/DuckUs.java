package client;

import client.game.engine.GameContainer;
import client.game.engine.GameNetwork;
import client.game.engine.GameController;
import client.game.nodes.Camera;
import client.game.nodes.AbrirMision1;
import client.game.nodes.AbrirVote;
import client.game.nodes.Bloque;
import client.game.nodes.PingNode;
import common.networking.DuckPacketReader;
import common.networking.engine.socket.SocketPublisher;
import client.networking.Client;
import client.game.nodes.MapNode;
import client.game.nodes.Mision;
import client.game.nodes.Player;
import client.game.nodes.Triangulito;
import client.game.nodes.VoteNode;

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
            //Creando Nodos
                Player player = new Player();
                MapNode mapa = new MapNode(container);
                PingNode pingNode = new PingNode();
            // Agregando Nodos
                controller.addNode(mapa);
                controller.addNode(player);
                controller.addNode(pingNode);
                network.setPingNode(pingNode);

        // GAME
        Mision mision1 = new Mision();
        VoteNode vote = new VoteNode();
        controller.addNode(mapa, "MapNode");
        controller.addNode(new Bloque(100,300));
        controller.addNode(new Bloque(50,50));
        
        controller.addNode(player, "Player");
        controller.addNode(new AbrirMision1(300,300,mision1));
        controller.addNode(new AbrirVote(250,250,vote));
        
        // LA CAMARA SIEMPRE DEBE IR DESPUES DEL JUGADOR
        controller.setCamera(new Camera(container));

        controller.addNode(new Triangulito());

        // BEGIN
        
        // EJECUSION
            client.start("localhost", 1331); //<-- Conectandose al servidor
            publisher.subscribe(network); // <-- Subscribiendo el NETWORK a los paquetes del socket
            container.start(); // <-- Ejecutando el juego en si
    }
}
