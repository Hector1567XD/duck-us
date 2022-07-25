package server.game.executors;

import common.networking.engine.Agent;
import common.networking.packets.*;
import java.util.ArrayList;
import server.game.engine.ServerContainer;
import server.game.engine.ServerNetwork;
import server.game.nodes.PongNode;
import server.game.nodes.SPlayer;

/**
 *
 * @author Ortiz pc
 */
public class PlayerPingExecutor {
    public static void execute(ServerContainer container, PingPacket packet, Agent packetSender) {
        ServerNetwork network = container.getNetwork();
        // Buscamos al jugador
        SPlayer currentPlayer = network.getPlayersMap().get(packetSender);
        if (currentPlayer == null) { // :) No se encontro al jugador
            return;
        }
        // Le decimos al pongNode que recibimos un playerPing de este jugador
        PongNode pongNode = network.getPongNode();
        pongNode.onPlayerPing(currentPlayer, container);
    }
}
