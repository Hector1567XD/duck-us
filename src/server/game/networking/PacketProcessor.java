package server.game.networking;

import common.networking.PacketTypes;
import common.networking.engine.Agent;
import common.networking.engine.Packet;
import common.networking.packets.GameInformationPacket;
import common.networking.packets.PlayerJoinedPacket;
import common.networking.packets.PlayerLoginPacket;
import common.networking.packets.PlayerMovePacket;
import common.networking.packets.PlayerMovedPacket;
import common.networking.packets.classes.PlayerJoined;
import java.util.ArrayList;
import server.game.engine.ServerContainer;
import server.game.engine.ServerNetwork;
import server.game.nodes.SPlayer;

public class PacketProcessor {
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
}
