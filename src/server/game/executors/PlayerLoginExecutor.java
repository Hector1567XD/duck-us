package server.game.executors;

import common.networking.engine.Agent;
import common.networking.packets.GameInformationPacket;
import common.networking.packets.PlayerJoinedPacket;
import common.networking.packets.PlayerLoginPacket;
import common.networking.packets.classes.PlayerJoined;
import java.util.ArrayList;
import server.game.engine.ServerContainer;
import server.game.engine.ServerNetwork;
import server.game.nodes.SPlayer;

/**
 *
 * @author Ortiz pc
 */
public class PlayerLoginExecutor {
    public static void execute(ServerContainer container, PlayerLoginPacket packet, Agent packetSender) {
        ServerNetwork network = container.getNetwork();
        System.out.println("Ha entrado un jugador a la partida :) --> " + packet.getPlayerName());

        // Creamos un nuevo nodo del jugador y lo agregamos al servidor
        SPlayer newSPlayer = network.addNewPlayer(container, packetSender, packet.getPlayerName());

        // Obtenemos la lista de jugadores que han entrado previamente al servidor (cosa que ira enviado en GameInformationPacket)
        ArrayList<PlayerJoined> previouslyJoineds = PlayerLoginExecutor.getPlayerPriviouslyJoinedList(network, packetSender);

        // Obtenemos el playerId del jugador que acaba de entrar al servidor
        int selfPlayerId = newSPlayer.getPlayerId();
        
        // Creamos un nuevo paquete "GameInformation" donde estara tu ID de jugador y la informacion de los jugadores previos
        GameInformationPacket gameInformationPacket = new GameInformationPacket(previouslyJoineds, selfPlayerId);
        // Notificamos al nuevo jugador de la informacion previa de la partida antes de que este entrara con "GameInformationPacket"
        network.sendPacket(gameInformationPacket, packetSender);

        // Notificamos al resto de jugadores que entro X jugador, pero excluimos al jugador que acaba de entrar de este paquete
        network.sendPacketToAllWithout(
            new PlayerJoinedPacket(newSPlayer.getPlayerId(), newSPlayer.getName()),
            packetSender
        );
    }

    // Gracias a este metodo obtenemos la lista de jugadores que han entrado previamente al servidor
    public static ArrayList<PlayerJoined> getPlayerPriviouslyJoinedList(ServerNetwork network, Agent packetSender) {
        ArrayList<PlayerJoined> previouslyJoineds = new ArrayList<>();
        for (SPlayer oSPlayer: network.getPlayersMap().values()) {
            /* Si el agente del jugador que estamos recorriendo justo ahora NO
               es igual al paquete del que acaba de entrar, entonces agregamos
               al jugador que estamos recorriendo a la lista de jugadores que entraron
               previamente
            */
            if (!oSPlayer.getAgent().equals(packetSender)) {
                previouslyJoineds.add(
                    new PlayerJoined(
                        oSPlayer.getPlayerId(),
                        oSPlayer.getName(),
                        oSPlayer.getX(),
                        oSPlayer.getY(),
                        oSPlayer.isDead()
                    )
                );
            }
        }
        return previouslyJoineds;
    }
}
