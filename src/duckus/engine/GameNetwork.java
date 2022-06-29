package duckus.engine;

import common.engine.Container;
import common.engine.Network;
import common.networking.Packet;
import common.networking.PacketReader;
import duckus.engine.networking.Client;
import java.io.IOException;

public class GameNetwork extends Network {
    private Client client;
    private String ipAddress;
    private int port;

    public GameNetwork(PacketReader reader, String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.client = new Client(this, reader);
    }
 
    @Override
    public void start() {
        client.start(ipAddress, port);
    }

    public void sendPacket(Packet packet) {
        try {
            client.sendPacket(packet);
        } catch (IOException ex) {
            // TODO: Si era paquete critico cerrar el juego o revertir.
            System.out.println("Ocurrio un error al enviar paquete");
        }
    }

    @Override
    public void packetArrived(Container container, Packet packet) {
        packetArrived((GameContainer) container, packet);
    }

    public void packetArrived(GameContainer container, Packet packet) {
        // TODO: Recibir Paquetes :)
    }
}
