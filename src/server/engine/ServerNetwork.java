package server.engine;

import common.engine.Container;
import common.engine.Network;
import common.engine.networking.Agent;
import common.engine.networking.Packet;
import common.engine.networking.PacketReader;
import java.io.IOException;
import server.engine.networking.Server;

public class ServerNetwork extends Network {
    private Server server;

    public ServerNetwork(PacketReader reader) {
        this.server = new Server(this, reader);
    }
 
    @Override
    public void start() {
        server.start();
    }

    public void sendPacket(Packet packet, Agent agent) {
        try {
            server.sendPacket(packet, agent);
        } catch (IOException ex) {
            // TODO: Si era paquete critico cerrar el servidor o revertir.
            System.out.println("Ocurrio un error al enviar paquete");
        }
    }

    @Override
    public void packetArrived(Container container, Packet packet) {
        ServerContainer serverContainer = (ServerContainer) container;
        // TODO: Recibir Paquetes :)
    }
}
