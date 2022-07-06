package server.game.engine;

import common.networking.PacketTypes;
import common.game.engine.Container;
import common.game.engine.Network;
import common.networking.engine.Agent;
import common.networking.engine.Packet;
import common.networking.engine.PacketReader;
import common.networking.packets.PlayerJoinedPacket;
import common.networking.packets.PlayerLoginPacket;
import java.io.IOException;
import server.networking.Server;

public class ServerNetwork extends Network {
    private final Server server;

    public ServerNetwork(Server server) {
        this.server = server;
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
        ServerNetwork network = container.getNetwork(); // TODO, hacer un casteo dentro de los containers

        if (packet.getPackageType() == PacketTypes.PLAYER_LOGIN) {
            PlayerLoginPacket playerLogin = (PlayerLoginPacket) packet;
            network.sendPacket(new PlayerJoinedPacket(25, playerLogin.getPlayerName()), packet.getSender());
        }
    }
}
