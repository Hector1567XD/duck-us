package server.game.nodes;

import common.CommonConstants;
import common.networking.packets.PlayerDisconnectedPacket;
import common.networking.packets.PongPacket;
import java.util.ArrayList;
import server.Constants;
import server.game.engine.ServerContainer;
import server.game.engine.ServerNetwork;
import server.game.engine.ServerNode;
import server.game.nodes.classes.PlayerPong;

public class PongNode extends ServerNode {
    private final ArrayList<PlayerPong> players = new ArrayList<PlayerPong>();

    @Override
    public void created(ServerContainer container) {
       
    }

    @Override
    public void update(ServerContainer container) {
        ArrayList<PlayerPong> playersToBeDisconnected = new ArrayList<PlayerPong>();
        for (PlayerPong playerPong: this.players) {
            playerPong.addFrameFromLastPing();
            if (playerPong.getFramesFromLastPing() > CommonConstants.FRAMES_PER_SECOND*Constants.PING_EXPECTED_TIMEOUT) {
                playersToBeDisconnected.add(playerPong);
            }
        }
        this.disconnectPlayersPong(container, playersToBeDisconnected);
    }
 
    private void disconnectPlayersPong(ServerContainer container, ArrayList<PlayerPong> playersToBeDisconnected) {
        ServerNetwork network = container.getNetwork();
        for (PlayerPong playerPong: playersToBeDisconnected) {
            this.disconnectPlayer(playerPong);
        }
    }
    
    // Eliminar jugador de PongNode
    private void disconnectPlayer(PlayerPong playerPong) {
        this.players.remove(playerPong);
        playerPong.getPlayer().remove();
    }

    // Agregar jugador a PongNode
    public void addPlayer(SPlayer player, ServerContainer container) {
        PlayerPong playerPong = new PlayerPong(player);
        this.players.add(playerPong);
        // Enviamos pong
        container.getNetwork().sendPacket(new PongPacket(), playerPong.getPlayer().getAgent());
    }

    public void onPlayerPing(SPlayer player, ServerContainer container) {
        ServerNetwork network = container.getNetwork();
        PlayerPong currentPlayerPong = null;

        for (PlayerPong playerPong: this.players) {
            if (playerPong.getPlayer().getPlayerId() == player.getPlayerId()) {
                currentPlayerPong = playerPong;
            }
        }

        if (currentPlayerPong == null) {
            // :) No se encontro al jugador (Hacker, no deberias llegar aqui)
            return;
        }
        // Limpiamos desde el ultimo ping
        currentPlayerPong.clearFramesFromLastPing();
        // Enviamos pong
        network.sendPacket(new PongPacket(), currentPlayerPong.getPlayer().getAgent());
    }

    
    @Override
    public String getNodeTag() {
        return "PongNode";
    }
}
