package client.game.engine;

import common.game.engine.Container;
import common.game.engine.Network;
import common.networking.Packet;
import client.networking.Client;
import java.io.IOException;

public class GameNetwork extends Network {
    private final Client client;

    public GameNetwork(Client client) {
        this.client = client;
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
