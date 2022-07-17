package server.networking;

import common.networking.engine.PacketReader;
import common.networking.engine.socket.Socket;
import common.networking.engine.socket.SocketPublisher;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server extends Socket {
    public Server(SocketPublisher publisher, PacketReader reader) {
        super(publisher, reader);
    }

    // Para el servidor, cuando usas un puerto especifico
    public void start(int port) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        super.start(socket);
    }
}
