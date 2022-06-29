package common.networking.socket;

import common.networking.Agent;
import common.networking.Packet;
import common.networking.PacketReader;
import common.networking.PacketWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public abstract class Socket extends Thread {
    private DatagramSocket socket; // Socket UDP de Java
    private final PacketWriter writer; // Escritor de paquetes
    private final PacketReader reader; // Lector de paquetes
    private SocketPublisher publisher; // Notificador

    public Socket(SocketPublisher publisher, PacketReader reader) {
        this.writer = new PacketWriter();
        this.reader = reader;
        this.publisher = publisher;
    }

    public void start(DatagramSocket socket) {
        this.socket = socket;
        super.start();
        System.out.println("Running socket on " + socket.getLocalAddress() + ":" + socket.getLocalPort());
    }

    @Override
    public void run() {
        while (true) {
            byte[] data = new byte[256];
            DatagramPacket datagramPacket = new DatagramPacket(data, data.length);

            try {
                socket.receive(datagramPacket);
            } catch (IOException e) {
                System.out.println("Error de I/O al recibir un paquete");
                e.printStackTrace();
            }

            Packet packet = this.reader.read(datagramPacket);
            publisher.notify(SocketEvents.PACKET_RECEIVED_EVENT, packet);
        }
    }

    public void sendPacket(Packet packet, Agent receiver) throws IOException {
        
        DatagramPacket datagramPacket = this.writer.write(packet, receiver);

        socket.send(datagramPacket);
        publisher.notify(SocketEvents.PACKET_SENDED_EVENT, packet);
    }
}

