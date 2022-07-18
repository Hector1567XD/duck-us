package client;

import client.core.DuckGame;
import client.game.engine.GameContainer;
import client.game.engine.GameNetwork;
import client.game.engine.GameController;
import common.networking.DuckPacketReader;
import common.networking.engine.socket.SocketPublisher;
import client.networking.Client;
import client.game.nodes.MapNode;
import client.game.nodes.Player;

public class DuckUs {
    public static void main(String[] args) {
        // FORMULARIOS
            
        
        
        // NETWORKING
        SocketPublisher publisher = new SocketPublisher();
        Client client = DuckUs.createClient(publisher, "localhost", 1331);
        DuckGame.start(publisher, client);
    }

    private static Client createClient(SocketPublisher publisher, String ipAddress, int port) {
        Client client = new Client(publisher, new DuckPacketReader());
        client.start(ipAddress, port);
        return client;
    }
}
