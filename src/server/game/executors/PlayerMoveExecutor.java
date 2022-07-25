package server.game.executors;

import common.networking.engine.Agent;
import common.networking.packets.*;
import java.util.ArrayList;
import server.game.engine.ServerContainer;
import server.game.engine.ServerNetwork;
import server.game.nodes.SPlayer;

/**
 *
 * @author Ortiz pc
 */
public class PlayerMoveExecutor {
    public static void execute(ServerContainer container, PlayerMovePacket packet, Agent packetSender) {
        ServerNetwork network = container.getNetwork();
        
        // Buscamos al jugador en el servidor
        SPlayer currentPlayer = network.getPlayerByAgent(packetSender);
        if (currentPlayer == null) { // No se encontro al jugador, bai :)
            return;
        }

        // Actualizamos su X, Y con lo que nos dice el paquete
        currentPlayer.setX(packet.getX());
        currentPlayer.setY(packet.getY());
        
        // Enviamos las cordenadas del jugador actual al resto de jugadores
        PlayerMoveExecutor.sendMovedPacketToRestOfPlayers(network, currentPlayer);
    }
    
    
    private static void sendMovedPacketToRestOfPlayers(ServerNetwork network, SPlayer currentPlayer) {
        // Creamos un nuevo paquete de PlayerMovedPacket
        PlayerMovedPacket movedPacket = new PlayerMovedPacket(
                currentPlayer.getPlayerId(),
                currentPlayer.getX(),
                currentPlayer.getY()
        );
        // Enviamos este paquete a raimundo y too el mundo (menos al jugador que se movio ps)
        network.sendPacketToAllWithout(movedPacket, currentPlayer.getAgent());
    }
}
