package duckus.engine.networking;

import common.engine.Network;
import common.networking.Agent;
import common.networking.Packet;
import common.networking.PacketReader;
import common.networking.PacketWriter;
import common.networking.Socket;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client extends Socket {
    private Agent server;
    private InetAddress ipAddress;
    private PacketWriter writer;
    private PacketReader reader;
    private Network network;

    public Client(Network network, PacketReader reader) {
        super(network, reader);
    }

    public void start() {
        throw new UnsupportedOperationException("Im not a Server."); 
    }

    public void start(String ipAddress, int serverPort) {
        try {
            this.server = new Agent(InetAddress.getByName(ipAddress), serverPort);
            this.setSocket(new DatagramSocket());
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        super.start();
    }

    public void sendPacket(Packet packet) throws IOException {
        super.sendPacket(packet, server);
    }
}
