package client.game.engine;

import client.game.executors.KilledExecutor;
import client.game.nodes.OPlayer;
import client.game.nodes.PingNode;
import common.game.engine.Container;
import common.game.engine.Network;
import common.networking.engine.Packet;
import client.networking.Client;
import client.utils.ErrorHelper;
import common.networking.PacketTypes;
import common.networking.packets.*;
import common.networking.packets.classes.PlayerJoined;
import java.io.IOException;
import java.util.ArrayList;
import server.game.executors.KillExecutor;

public class GameNetwork extends Network {
    private final Client client;
    private final ArrayList<OPlayer> players = new ArrayList<>();
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
        // TODO: Recibir Paquetes :)
        GameNetwork network = container.getNetwork();
        if (packet.getPackageType() == PacketTypes.PLAYER_JOINED_PACKET) {
            PlayerJoinedPacket joinedPacket = (PlayerJoinedPacket) packet;
            System.out.println("Ha entrado el jugador --> " + joinedPacket.getPlayerName());
            String playerName = joinedPacket.getPlayerName();
            int playerId = joinedPacket.getPlayerId();
            
            OPlayer newOPlayer = new OPlayer(playerId, playerName);
            container.getController().addNode(newOPlayer);
            players.add(newOPlayer);
        } else if (packet.getPackageType() == PacketTypes.GAME_INFORMATION) {
            GameInformationPacket gamePacket = (GameInformationPacket) packet;
            ArrayList<PlayerJoined> previouslyPlayers =  gamePacket.getPlayers();
            for (PlayerJoined previouslyPlayer: previouslyPlayers) {
                String playerName = previouslyPlayer.getPlayerName();
                int playerId = previouslyPlayer.getPlayerId();
            
                OPlayer newOPlayer = new OPlayer(playerId, playerName);
                newOPlayer.setX(previouslyPlayer.getX());
                newOPlayer.setY(previouslyPlayer.getY());
                newOPlayer.setIsDead(previouslyPlayer.isDead());

                container.getController().addNode(newOPlayer);
                players.add(newOPlayer);
            }
        }else if (packet.getPackageType() == PacketTypes.PLAYER_MOVED) {
            PlayerMovedPacket movedPacket = (PlayerMovedPacket) packet;
            for (OPlayer oPlayer: players) {
                if (oPlayer.getPlayerId() == movedPacket.getPlayerId()) {
                    oPlayer.setX(movedPacket.getX());
                    oPlayer.setY(movedPacket.getY());
                }
            }
        }else if (packet.getPackageType() == PacketTypes.SERVER_PONG) {
            PongPacket pongPacket = (PongPacket) packet;
            this.pingNode.onServerPong(container);
        }else if (packet.getPackageType() == PacketTypes.PLAYER_DISCONNECTED) {
            PlayerDisconnectedPacket playerDisconnected = (PlayerDisconnectedPacket) packet;
            this.pingNode.onServerPong(container);

            for (int i=0; i < players.size(); i++) {
                OPlayer oPlayer = players.get(i);
                if (oPlayer.getPlayerId() == playerDisconnected.getPlayerId()) {
                    this.players.remove(i);
                    oPlayer.remove();
                    i--;
                }else if (oPlayer.getPlayerId() == 0) {
                    // Si el player ID es 0, entonces soy yo el desconectado
                    ErrorHelper.showServerForceDisconnectError();
                }
            }
        }else if (packet.getPackageType() == PacketTypes.PLAYER_KILLED) {
            KilledExecutor.execute(container, (PlayerKilledPacket) packet);
        }
    }
}
