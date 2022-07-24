package client.game.executors;

import client.game.engine.GameContainer;
import client.game.engine.GameNetwork;
import client.game.nodes.OPlayer;
import common.networking.packets.*;
import common.networking.packets.classes.PlayerJoined;
import java.util.ArrayList;


public class GameInformationExecutor{
    public static void execute(GameContainer container, GameInformationPacket packet) {
        GameNetwork network = container.getNetwork();
        // Le decimos a nuestro GameNetwork  cual fue el PlayerId que nos asigno el servidor
        network.setSelfPlayerId(packet.getSelfPlayerId());
        // Obtenemos la informacion de todos los jugadores previos de la partida
        ArrayList<PlayerJoined> previouslyPlayers =  packet.getPlayers();
        // Recorremos el arreglo de jugadores previos de la partida
        for (PlayerJoined player: previouslyPlayers) {
            // Leemos los datos de un jugador previo
            String playerName = player.getPlayerName();
            int playerId = player.getPlayerId();
            int playerX = player.getX();
            int playerY = player.getY();
            // Creamos un nuevo nodo de OPlayer en base a los datos de este jugador previo
            OPlayer newOPlayer = new OPlayer(playerId, playerName);
            newOPlayer.setX(playerX);
            newOPlayer.setY(playerY);
            // Agregamos el nuevo jugador previo al servidor
            network.addServerPlayer(container, newOPlayer);
        }
    }
}
