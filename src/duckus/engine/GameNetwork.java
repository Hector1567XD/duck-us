package duckus.engine;

import common.engine.Container;
import common.engine.Network;
import common.engine.networking.Packet;
import common.engine.networking.PacketReader;
import duckus.engine.networking.Client;
import java.io.IOException;

public class GameNetwork extends Network {
    private Client client;

    public GameNetwork(PacketReader reader) {
        this.client = new Client(this, reader);
    }
 
    @Override
    public void start() {
        client.start();
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
        GameContainer gameContainer = (GameContainer) container;
        // TODO: Recibir Paquetes :)
    }
}
