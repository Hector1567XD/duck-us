package client.core;

import client.Constants;
import client.game.engine.GameContainer;
import client.game.engine.GameController;
import client.game.engine.GameNetwork;
import client.game.nodes.MapEscuelaNode;
import client.game.nodes.Player;
import client.networking.Client;
import client.game.nodes.Camera;
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
import client.game.nodes.classes.DuckImages;

public class DuckGame {
    public static void start(SocketPublisher publisher, Client client, String nombre) {
        // APP GAME BUILDING
            GameController controller = new GameController();
            GameNetwork network = new GameNetwork(client);
            GameContainer container = new GameContainer(Constants.SCALE, network, controller);
            DuckImages.load();
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

            // ORCADOS
            Mision mision = new Mision();
            controller.addNode(new AbrirMision1(38*32,37*32, mision));
            controller.addNode(mision);

            Mision mision_b = new Mision();
            controller.addNode(new AbrirMision1(64*32,42*32, mision_b));
            controller.addNode(mision_b);

            Mision mision_c = new Mision();
            controller.addNode(new AbrirMision1(156*32,46*32, mision_c));
            controller.addNode(mision_c);

            // TYPERACERS
            Mision2 mision2 = new Mision2();
            controller.addNode(new AbrirMision2(122*32,53*32, mision2));
            controller.addNode(mision2);

            Mision2 mision2_b = new Mision2();
            controller.addNode(new AbrirMision2(169*32,66*32, mision2_b));
            controller.addNode(mision2_b);

            // OSCILOSCOPIOSs
            Mision3 mision3 = new Mision3();
            controller.addNode(new AbrirMision3(109*32,37*32, mision3));
            controller.addNode(mision3);

            Mision3 mision3_b = new Mision3();
            controller.addNode(new AbrirMision3(71*32,61*32, mision3_b));
            controller.addNode(mision3_b);

            Mision3 mision3_c = new Mision3();
            controller.addNode(new AbrirMision3(120*32,22*32, mision3_c));
            controller.addNode(mision3_c);

            // CARETELERAS
            Mision4 mision4 = new Mision4();
            controller.addNode(new AbrirMision4(131*32,60*32, mision4));
            controller.addNode(mision4);

            Mision4 mision4_b = new Mision4();
            controller.addNode(new AbrirMision4(87*32,36*32, mision4_b));
            controller.addNode(mision4_b);

        // ACOPLANDO NODOS
            network.setPingNode(pingNode);
            controller.setCamera(new Camera(container)); // LA CAMARA SIEMPRE DEBE IR DESPUES DEL JUGADOR
        // BEGIN
            publisher.subscribe(network);
            container.start();
        }
    }
