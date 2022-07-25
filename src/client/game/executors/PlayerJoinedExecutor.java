package client.game.executors;

import client.game.engine.GameContainer;
import client.game.engine.GameNetwork;
import common.networking.packets.*;


public class PlayerJoinedExecutor{
    public static void execute(GameContainer container, PlayerJoinedPacket packet) {
        GameNetwork network = container.getNetwork();
        System.out.println("Ha entrado el jugador --> " + packet.getPlayerName());
        
        // Obtenemos los datos del nuevo jugador del paquete
        String playerName = packet.getPlayerName();
        int playerId = packet.getPlayerId();
        
        // Agregamos al nuevo jugador
        network.addNewServerPlayer(container, playerId, playerName);
    }
}
