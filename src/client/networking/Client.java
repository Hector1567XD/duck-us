package client.networking;

import common.game.engine.Network;
import common.networking.engine.Agent;
import common.networking.engine.Packet;
import common.networking.engine.PacketReader;
import common.networking.engine.PacketWriter;
import common.networking.engine.socket.Socket;
import common.networking.engine.socket.SocketPublisher;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client extends Socket {
    private Agent server;

    public Client(SocketPublisher publisher, PacketReader reader) {
        super(publisher, reader);
    }

    public void start() {
        throw new UnsupportedOperationException("Im not a Server."); 
    }

    private DatagramSocket createSocket(String ipAddress, int serverPort) {
        try {
            this.server = new Agent(InetAddress.getByName(ipAddress), serverPort);
            DatagramSocket socket = new DatagramSocket();
            return socket;
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void start(String ipAddress, int serverPort) {
        DatagramSocket socket = createSocket(ipAddress, serverPort);
        super.start(socket);
    }

    public void sendPacket(Packet packet) throws IOException {
        super.sendPacket(packet, server);
    }
}
