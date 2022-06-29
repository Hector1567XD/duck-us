package server.networking;

import common.networking.PacketReader;
import common.networking.socket.Socket;
import common.networking.socket.SocketPublisher;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server extends Socket {
    public Server(SocketPublisher publisher, PacketReader reader) {
        super(publisher, reader);
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
