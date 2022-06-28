package duckus.engine.networking;

import common.engine.Network;
import common.engine.networking.Agent;
import common.engine.networking.Packet;
import common.engine.networking.PacketReader;
import common.engine.networking.PacketWriter;
import common.engine.networking.Socket;
import java.io.IOException;
import java.net.InetAddress;
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

    public void start(String ipAddress, int serverPort) {
        try {
            InetAddress inetIpAddress = InetAddress.getByName(ipAddress);
            this.server = new Agent(inetIpAddress, serverPort);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        super.start();
    }

    public void sendPacket(Packet packet) throws IOException {
        super.sendPacket(packet, server);
    }
}
