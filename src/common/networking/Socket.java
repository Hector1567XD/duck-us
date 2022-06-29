package common.networking;

import common.engine.Network;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public abstract class Socket extends Thread {
    private DatagramSocket socket;
    private PacketWriter writer;
    private PacketReader reader;
    private Network network;

    public Socket(Network network, PacketReader reader) {
        this.writer = new PacketWriter();
        this.reader = reader;
        this.network = network;
    }
    
    public void setSocket(DatagramSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {
            byte[] data = new byte[256];
            System.out.println("Running socket on " + socket.getLocalAddress() + ":" + socket.getLocalPort());
            DatagramPacket datagramPacket = new DatagramPacket(data, data.length);
            
            try {
                socket.receive(datagramPacket);
            } catch (IOException e) {
                System.out.println("Error de I/O al recibir un paquete");
                e.printStackTrace();
            }

            System.out.println("Recibiendo paquete de (" + datagramPacket.getLength() + " bytes).");

            Packet packet = this.reader.read(datagramPacket);

            Agent agent = new Agent(datagramPacket.getAddress(), datagramPacket.getPort());
            packet.setSender(agent);

            network.appendPacket(packet);
        }
    }

    public void sendPacket(Packet packet, Agent receiver) throws IOException {
        packet.setReceiver(receiver);
        DatagramPacket datagramPacket = this.writer.write(packet, receiver);

        System.out.println("Enviando paquete tipo " + packet.getPackageType() + " (" + datagramPacket.getLength() + " bytes). hacia " + receiver.getIpAddress().getHostAddress() + ":" + receiver.getPort());

        socket.send(datagramPacket);
    }
}
