
package client.core;

import client.Constants;
import client.game.engine.GameContainer;
import client.game.engine.GameController;
import client.game.engine.GameNetwork;
import client.game.nodes.MapNode;
import client.game.nodes.Player;
import client.networking.Client;
import client.game.nodes.Camera;
import client.game.nodes.Bloque;
import common.networking.engine.socket.SocketPublisher;
import client.game.nodes.PingNode;
import client.game.nodes.AbrirMision1;
import client.game.nodes.AbrirMision2;
import client.game.nodes.Mision;
import client.game.nodes.Mision2;

public class DuckGame {
    public static void start(SocketPublisher publisher, Client client) {
        // APP GAME BUILDING
            GameController controller = new GameController();
            GameNetwork network = new GameNetwork(client);
            GameContainer container = new GameContainer(Constants.SCALE, network, controller);

        // GAME
        //Creando Nodos
            Player player = new Player();
            MapNode mapa = new MapNode(container);
            PingNode pingNode = new PingNode();

            // Agregando Nodos
            controller.addNode(mapa, "MapNode");
            controller.addNode(new Bloque(100,300));
            controller.addNode(new Bloque(50,50));
            controller.addNode(new Bloque(300,300));

            controller.addNode(player, "Player");
            // ACOPLANDO NODOS
            network.setPingNode(pingNode);
            controller.setCamera(new Camera(container)); // LA CAMARA SIEMPRE DEBE IR DESPUES DEL JUGADOR
        // BEGIN
            publisher.subscribe(network);
            container.start();
    }
}
