package server.game.nodes;

import common.networking.packets.PongPacket;
import java.util.ArrayList;
import server.game.engine.ServerContainer;
import server.game.engine.ServerNetwork;
import server.game.engine.ServerNode;
import server.game.nodes.classes.PlayerPong;

public class PongNode extends ServerNode {
    private ArrayList<PlayerPong> players = new ArrayList<PlayerPong>();
    
    @Override
    public void created(ServerContainer container) {
       
    }

    @Override
    public void update(ServerContainer container) {
        for (PlayerPong playerPong: this.players) {
            playerPong.addFrameFromLastPing();
        }
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
    
    // Eliminar jugador de PongNode

}
