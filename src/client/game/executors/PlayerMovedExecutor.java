package client.game.executors;

import client.game.engine.GameContainer;
import client.game.engine.GameNetwork;
import client.game.nodes.OPlayer;
import common.networking.packets.*;


public class PlayerMovedExecutor{
    public static void execute(GameContainer container, PlayerMovedPacket packet) {
        GameNetwork network = container.getNetwork();
        // Recorremos todos nuestros jugadores
        for (OPlayer oPlayer: network.getPlayers()) {
            // Ubicamos cual es el jugador que se nos dijo se movio por medio de la ip
            if (oPlayer.getPlayerId() == packet.getPlayerId()) {
                // Actualizamos las coordenadas de dicho jugador
                oPlayer.setX(packet.getX());
                oPlayer.setY(packet.getY());
            }
        }
    }
}
