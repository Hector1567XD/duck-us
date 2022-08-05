package client.game.engine;

import client.game.executors.*;
import client.game.nodes.OPlayer;
import client.game.nodes.PingNode;
import common.game.engine.Container;
import common.game.engine.Network;
import common.networking.engine.Packet;
import client.networking.Client;
import common.networking.PacketTypes;
import common.networking.packets.*;
import java.io.IOException;
import java.util.ArrayList;

public class GameNetwork extends Network {
    private final Client client;
    private final ArrayList<OPlayer> players = new ArrayList<>();
    private int selfPlayerId = 0;
    private PingNode pingNode;

    public GameNetwork(Client client) {
        this.client = client;
    }

    public void setPingNode(PingNode pingNode) {
        this.pingNode = pingNode;
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
        switch (packet.getPackageType()) {
            case PacketTypes.PLAYER_JOINED_PACKET -> PlayerJoinedExecutor.execute(container, (PlayerJoinedPacket) packet);
            case PacketTypes.GAME_INFORMATION -> GameInformationExecutor.execute(container, (GameInformationPacket) packet);
            case PacketTypes.PLAYER_MOVED -> PlayerMovedExecutor.execute(container, (PlayerMovedPacket) packet);
            case PacketTypes.SERVER_PONG -> ServerPongExecutor.execute(container, (PongPacket) packet);
            case PacketTypes.PLAYER_DISCONNECTED -> PlayerDisconnectedExecutor.execute(container, (PlayerDisconnectedPacket) packet);
            case PacketTypes.PLAYER_KILLED -> KilledExecutor.execute(container, (PlayerKilledPacket) packet);
            default -> {
            }
        }
    }

    public ArrayList<OPlayer> getPlayers() {
        return players;
    }

    public void addNewServerPlayer(GameContainer container, int playerId, String playerName) {
        OPlayer player = new OPlayer(playerId, playerName);
        this.addServerPlayer(container, player);
    }

    public void addServerPlayer(GameContainer container, OPlayer player) {
        container.getController().addNode(player);
        /*
            Sincronized para proteger el recurso de la lista de jugadores en el
            caso borde en el que un jugador aparezca al mismo tiempo que uno se elimine
            o el caso borde donde se agreguen 2 jugadores a la vez
        */
        synchronized (players) {
            players.add(player);
        }
    }
    
    public PingNode getPingNode() {
        return pingNode;
    }
    
    public void removeServerPlayerById(int playerToRemoveId) {
        /*
            Sincronized para proteger el recurso de la lista de jugadores en el
            caso borde en el que un jugador aparezca al mismo tiempo que uno se elimine
            o el caso borde donde se eliminen 2 jugadores a la vez
        */
        synchronized (players) {
            // Recorremos la lista de jugadores
            for (int i=0; i < players.size(); i++) {
                OPlayer oPlayer = players.get(i);
                // Si el ID del jugador desconectado es igual al ID de algno de los jugadores
                if (oPlayer.getPlayerId() == playerToRemoveId) {
                    // Eliminamos a ese jugador de la lista de jugadores
                    this.players.remove(i);
                    oPlayer.remove();
                    i--;
                }
            }
        }
    }

    // Obtener y establecer mi propio player ID segun el servidor
    public int getSelfPlayerId() {
        return selfPlayerId;
    }

    public void setSelfPlayerId(int selfPlayerId) {
        this.selfPlayerId = selfPlayerId;
    }
}
