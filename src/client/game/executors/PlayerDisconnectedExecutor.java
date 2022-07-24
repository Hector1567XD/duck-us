package client.game.executors;

import client.game.engine.GameContainer;
import client.game.engine.GameNetwork;
import client.utils.ErrorHelper;
import common.networking.packets.*;

public class PlayerDisconnectedExecutor {
    public static void execute(GameContainer container, PlayerDisconnectedPacket packet) {
        GameNetwork network = container.getNetwork();
        // Obtenemos el ID a eliminar
        int playerIdToRemove = packet.getPlayerId();
        if (playerIdToRemove == 0 || playerIdToRemove == network.getSelfPlayerId()) {
            // Si el player ID es 0, entonces soy yo el desconectado
            ErrorHelper.showServerForceDisconnectError();
        }else{
            // Si no, entonces es uno de los jugadores del servidor
            network.removeServerPlayerById(playerIdToRemove);
        }
    }
}
