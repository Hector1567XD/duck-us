package server.game.executors;

import common.CommonConstants;
import common.networking.engine.Agent;
import common.networking.packets.PlayerKillPacket;
import common.networking.packets.PlayerKilledPacket;
import common.utils.NodeDistanceHelper;
import server.game.engine.ServerContainer;
import server.game.engine.ServerNetwork;
import server.game.nodes.SPlayer;

public class KillExecutor {
    public static void execute(ServerContainer container, PlayerKillPacket packet, Agent packetSender) {
        ServerNetwork network = container.getNetwork();
        // Buscando al perpertrador
        SPlayer asesino = KillExecutor.getJugadorByAgent(container, packetSender);
        if (asesino == null) { // :) No se encontro al jugador
            return;
        }

        // Buscando a la victima
        SPlayer victima = KillExecutor.getJugadorById(container, packet.getPlayerId());

        if (victima == null) { // :) No se encontro al jugador
            return;
        }
        // VALIDICIONES
        if (NodeDistanceHelper.getDistance(asesino, victima) > CommonConstants.DISTANCE_TO_KILL) {
            // Fuera de rango!, no matar!, este es un hacker >:V o tiene lag
            return;
        }      
        // VALIDICIONES
        /*if (asesino.isKiller()) {
            // El asesino no es impostor
            return;
        }*/
        
        victima.kill(container);
    }

    public static SPlayer getJugadorByAgent(ServerContainer container, Agent packetSender) {
        ServerNetwork network = container.getNetwork();
        SPlayer currentPlayer = null;
        for (SPlayer sOPlayer: network.getPlayersMap().values()) {
            if (sOPlayer.getAgent().equals(packetSender)) {
                currentPlayer = sOPlayer;
            }
        }
        return currentPlayer;
    }
    
    public static SPlayer getJugadorById(ServerContainer container, int playerId) {
        ServerNetwork network = container.getNetwork();
        SPlayer currentPlayer = null;
        for (SPlayer sOPlayer: network.getPlayersMap().values()) {
            if (sOPlayer.getPlayerId() == playerId) {
                currentPlayer = sOPlayer;
            }
        }
        return currentPlayer;
    }
}
