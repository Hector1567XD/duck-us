package server.engine;

import common.PacketTypes;
import common.engine.Container;
import common.engine.Network;
import common.networking.Agent;
import common.networking.Packet;
import common.networking.PacketReader;
import common.packets.PlayerJoinedPacket;
import common.packets.PlayerLoginPacket;
import java.io.IOException;
import server.engine.networking.Server;

public class ServerNetwork extends Network {
    private Server server;
    private int port;

    public ServerNetwork(PacketReader reader, int port) {
        this.port = port;
        this.server = new Server(this, reader);
    }

    @Override
    public void start() {
        server.start(port);
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
        packetArrived((ServerContainer) container, packet);
    }

    public void packetArrived(ServerContainer container, Packet packet) {
        ServerNetwork network = (ServerNetwork) container.getNetwork(); // TODO, hacer un casteo dentro de los containers

        if (packet.getPackageType() == PacketTypes.PLAYER_LOGIN) {
            PlayerLoginPacket playerLogin = (PlayerLoginPacket) packet;
            network.sendPacket(new PlayerJoinedPacket(25, playerLogin.getPlayerName()), packet.getSender());
        }
    }
}
