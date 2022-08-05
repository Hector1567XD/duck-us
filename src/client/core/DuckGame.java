package client.core;

import client.Constants;
import client.game.engine.GameContainer;
import client.game.engine.GameController;
import client.game.engine.GameNetwork;
import client.game.nodes.MapEscuelaNode;
import client.game.nodes.Player;
import client.networking.Client;
import client.game.nodes.Camera;
import client.game.nodes.Bloque;
import client.game.nodes.MapEscuelaUpsideNode;
import client.game.nodes.MapLobbyNode;
import common.networking.engine.socket.SocketPublisher;
import client.game.nodes.PingNode;
import client.game.nodes.AbrirMision1;
import client.game.nodes.AbrirMision2;
import client.game.nodes.AbrirMision3;
import client.game.nodes.AbrirMision4;
import client.game.nodes.Mision;
import client.game.nodes.Mision2;
import client.game.nodes.Mision3;
import client.game.nodes.Mision4;

public class DuckGame {
    public static void start(SocketPublisher publisher, Client client, String nombre) {
        // APP GAME BUILDING
            GameController controller = new GameController();
            GameNetwork network = new GameNetwork(client);
            GameContainer container = new GameContainer(Constants.SCALE, network, controller);

        // GAME
        //Creando Nodos
            Player player = new Player(nombre);
            PingNode pingNode = new PingNode();

        // Agregando Nodos
            controller.addNode(new MapEscuelaNode(container), "MapNode");
            controller.addNode(new MapLobbyNode(container), "LobbyNode");
            /*controller.addNode(new Bloque(100,300));
            controller.addNode(new Bloque(50,50));
            controller.addNode(new Bloque(300,300));*/
/*
                    this.x = 4032;
        this.y = 1440;
            */
            controller.addNode(player, "Player");
            controller.addNode(new MapEscuelaUpsideNode(container), "MapNodeUpside");
            Mision mision = new Mision();
            Mision2 mision2 = new Mision2();
            Mision3 mision3 = new Mision3();
            Mision4 mision4 = new Mision4();
            controller.addNode(new AbrirMision1(4032+32,1440+32, mision));
            controller.addNode(new AbrirMision2(4032-32,1440+32, mision2));
            controller.addNode(new AbrirMision3(4032+32,1440-32, mision3));
            controller.addNode(new AbrirMision4(4032-32,1440-32, mision4));
            controller.addNode(mision);
            controller.addNode(mision2);
            controller.addNode(mision3);
            controller.addNode(mision4);

        // ACOPLANDO NODOS
            network.setPingNode(pingNode);
            controller.setCamera(new Camera(container)); // LA CAMARA SIEMPRE DEBE IR DESPUES DEL JUGADOR
        // BEGIN
            publisher.subscribe(network);
            container.start();

        }
    }  
