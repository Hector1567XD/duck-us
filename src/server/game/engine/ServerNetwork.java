package server.game.engine;

import common.networking.PacketTypes;
import common.game.engine.Container;
import common.game.engine.Network;
import common.networking.engine.Agent;
import common.networking.engine.Packet;
import common.networking.packets.*;
import common.networking.packets.classes.PlayerJoined;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
        ServerNetwork network = container.getNetwork(); // TODO, hacer un casteo dentro de los containers
        switch (packet.getPackageType()) {
            case PacketTypes.PLAYER_LOGIN_PACKET -> {
                PlayerLoginPacket loginPacket = (PlayerLoginPacket) packet;
                System.out.println("Ha entrado un jugador a la partida :) --> " + loginPacket.getPlayerName());
                lastPlayerId++;
                SPlayer newSPlayer = new SPlayer(packetSender, lastPlayerId, loginPacket.getPlayerName());
                container.getController().addNode(newSPlayer);
                this.players.put(packetSender, newSPlayer);
                this.pongNode.addPlayer(newSPlayer, container);
                // Notificamos al X jugador de la existencia de los jugadores que entraron previamente
                ArrayList<PlayerJoined> previouslyJoineds = new ArrayList<>();
                for (SPlayer oSPlayer: this.players.values()) {
                    if (!oSPlayer.getAgent().equals(packetSender)) {
                        previouslyJoineds.add(
                            new PlayerJoined(
                                oSPlayer.getPlayerId(),
                                oSPlayer.getName(),
                                oSPlayer.getX(),
                                oSPlayer.getY()
                            )
                        );
                    }
                }
                this.sendPacket(new GameInformationPacket(previouslyJoineds), packetSender);
                // LUEGO de enviar la informacion de los jugadores anteriores añadimos al jugador al arraylist de jugadores
                // Notificamos al resto de jugadores que entro X jugador
                this.sendPacketToAllWithout(
                        new PlayerJoinedPacket(newSPlayer.getPlayerId(), newSPlayer.getName()),
                        packetSender
                );
            }
            case PacketTypes.PLAYER_MOVE ->                 {
                    PlayerMovePacket movePacket = (PlayerMovePacket) packet;
                    SPlayer currentPlayer = this.players.get(packetSender);
                    if (currentPlayer == null) { // :) No se encontro al jugador
                        return;
                    }
                    currentPlayer.setX(movePacket.getX());
                    currentPlayer.setY(movePacket.getY());
                    PlayerMovedPacket movedPacket = new PlayerMovedPacket(
                            currentPlayer.getPlayerId(),
                            currentPlayer.getX(),
                            currentPlayer.getY()
                    );      this.sendPacketToAllWithout(movedPacket, packetSender);
                }
            case PacketTypes.PLAYER_PING ->                 {
                    SPlayer currentPlayer = this.players.get(packetSender);
                    if (currentPlayer == null) { // :) No se encontro al jugador
                        return;
                    }
                    this.pongNode.onPlayerPing(currentPlayer, container);
                }
            default -> {
            }
        }
    }

    public void disconnectPlayer(ServerContainer container, SPlayer player) {
        ServerNetwork network = container.getNetwork();
        // Le enviamos un paquete de desconexion a todos menos al jugador
        network.sendPacketToAllWithout(new PlayerDisconnectedPacket(player.getPlayerId()), player.getAgent());
        // Al jugador le enviamos un paquete de deconexion tambien pero con id = 0, cuando id = 0 entonces significa que eres tu el desconectado
        network.sendPacket(new PlayerDisconnectedPacket(0), player.getAgent());
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
}
/*
    TODO: 
    - Tener una lista mas inteligentes de clientes [Agent --> SPlayer] [temporalmente no]
      - IDs de clientes
      - Nodo SPlayer
    - Reenvio a todos los clientes menos X cliente
    - Si entras de ultimo al servidor te avise del resto de jugadores
*/