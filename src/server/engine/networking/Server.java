package server.engine.networking;

import common.engine.Network;
import common.engine.networking.PacketReader;
import common.engine.networking.Socket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server extends Socket {
    public Server(Network network, PacketReader reader) {
        super(network, reader);
    }

    // Para el servidor, cuando usas un puerto especifico
    public void start(int port) {
        try {
            this.setSocket(new DatagramSocket(port));
        } catch (SocketException e) {
            e.printStackTrace();
        }
        super.start();
    }
}
