package server.game.engine;

import common.networking.PacketTypes;
import common.game.engine.Container;
import common.game.engine.Network;
import common.networking.engine.Agent;
import common.networking.engine.Packet;
import common.networking.packets.*;
import java.io.IOException;
import java.util.HashMap;
import server.game.executors.*;
import server.game.nodes.PongNode;
import server.game.nodes.SPlayer;
import server.networking.Server;

public class ServerNetwork extends Network {
    private final Server server;
    private final HashMap<Agent, SPlayer> players;
    private int lastPlayerId = 0; // <- ID del ukltimo jugador []
    private PongNode pongNode;

    public ServerNetwork(Server server) {
        this.server = server;
        this.players = new HashMap<>();
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
        packetArrived(container, packet, packet.getSender());
    }

    public void packetArrived(ServerContainer container, Packet packet, Agent packetSender) {
        switch (packet.getPackageType()) {
            case PacketTypes.PLAYER_LOGIN_PACKET -> PlayerLoginExecutor.execute(container, (PlayerLoginPacket) packet, packetSender);
            case PacketTypes.PLAYER_MOVE -> PlayerMoveExecutor.execute(container, (PlayerMovePacket) packet, packetSender);
            case PacketTypes.PLAYER_PING -> PlayerPingExecutor.execute(container, (PingPacket) packet, packetSender);
            case PacketTypes.KILL_PACKET -> KillExecutor.execute(container, (PlayerKillPacket) packet, packetSender);
            default -> {}
        }
    }

    public void disconnectPlayer(ServerContainer container, SPlayer player) {
        ServerNetwork network = container.getNetwork();
        // Le enviamos un paquete de desconexion a TODOS
        network.sendPacketToAll(new PlayerDisconnectedPacket(player.getPlayerId()));
        this.players.remove(player.getAgent());
    }

    public void sendPacketToAll(Packet packet) {
        for (Agent client: this.players.keySet()) {
            this.sendPacket(packet, client);
        }
    }

    public void sendPacketToAllWithout(Packet packet, Agent bannedClient) {
        for (Agent client: this.players.keySet()) {
            if (!client.equals(bannedClient)) {
                this.sendPacket(packet, client);
            }
        }
    }

    public void setPongNode(PongNode pongNode) {
        this.pongNode = pongNode;
    }

    // METHODS

    public SPlayer addNewPlayer(ServerContainer container, Agent playerAgent, String playerName) {
        // Creamos el NODO del nuevo jugador
        lastPlayerId++;
        SPlayer newSPlayer = new SPlayer(playerAgent, lastPlayerId, playerName);
        // Agregamos el nodo del nuevo jugador al ciclo de vida del servdor
        container.getController().addNode(newSPlayer);
        // Agregamos al nodo del nuevo jugador a la lista [hashmap] de jugadores del servidor
        this.players.put(playerAgent, newSPlayer);
        // Agregamos al nodo del nuevo jugador al pongNode para que se encargue de gestionar su ping y su pong
        this.pongNode.addPlayer(newSPlayer, container);
        // Retornamos el nodo del nuevo jugador para que el invocador de este metodo pueda usarlo
        return newSPlayer;
    }

    public HashMap<Agent, SPlayer> getPlayersMap() {
        return players;
    }

    public SPlayer getPlayerByAgent(Agent packetSender) {
        return this.players.get(packetSender);
    }

    public PongNode getPongNode() {
        return pongNode;
    }
}
