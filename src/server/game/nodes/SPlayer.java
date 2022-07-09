package server.game.nodes;

import common.networking.engine.Agent;
import server.game.engine.ServerContainer;
import server.game.engine.ServerNode;

public class SPlayer extends ServerNode {
    private int velocity = 4;
    private int playerId;
    private Agent agent;
    private String name;

    public SPlayer(Agent agent, int playerId, String name) {
        this.agent = agent;
        this.playerId = playerId;
        this.name = name;
    }

    @Override
    public void created(ServerContainer container) {
        
    }

    @Override
    public void update(ServerContainer container) {
        
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
    }

    public Agent getAgent() {
        return agent;
    }
}

