package server.game.nodes;

import java.util.ArrayList;
import server.game.engine.ServerContainer;
import server.game.engine.ServerNode;
import server.game.nodes.classes.PlayerPong;

public class PongNode extends ServerNode {
    private ArrayList<PlayerPong> players = new ArrayList<PlayerPong>();
    
    @Override
    public void created(ServerContainer container) {
       
    }

    @Override
    public void update(ServerContainer container) {
       
    }

    // Agregar jugador a PongNode
    public void addPlayer(SPlayer player) {
        this.players.add(new PlayerPong(player));
    }

    // Eliminar jugador de PongNode

}
