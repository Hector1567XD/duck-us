package server.engine.networking;

import common.engine.Network;
import common.engine.networking.PacketReader;
import common.engine.networking.Socket;

public class Server extends Socket {
    public Server(Network network, PacketReader reader) {
        super(network, reader);
    }
}
