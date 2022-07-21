package server.game.nodes;

import client.game.nodes.OPlayer;
import static common.networking.PacketTypes.PLAYER_KILLED;
import common.networking.engine.Agent;
import common.networking.engine.Packet;
import common.networking.packets.PlayerKillPacket;
import common.networking.packets.PlayerKilledPacket;
import java.util.ArrayList;
import server.game.engine.ServerContainer;
import server.game.engine.ServerNode;

public class SPlayer extends ServerNode {
    private int velocity = 4;
    private int playerId;
    private Agent agent;
    private String name;
    private boolean dead;

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
    
    @Override
    public void removed(ServerContainer container) {
        container.getNetwork().disconnectPlayer(container, this);
    }

    
   public String getNodeTag() {
        return "Splayer";
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

    public boolean isDead() {
        return dead;
    }

    public void kill(ServerContainer container) {
        this.dead = true;
        container.getNetwork().sendPacketToAllWithout(new PlayerKilledPacket(this.playerId), agent);
        container.getNetwork().sendPacket(new PlayerKilledPacket(0),agent);
    }
    
    public void killed(ServerContainer container) {

             ArrayList<OPlayer> listPlayers = container.getController().getNodes().getListByTag("Oplayer");
               for (OPlayer victima : listPlayers){
     
                   victima.setIsDead(true);
                }
            }
        
         

    public void revive() {
        this.dead = false;
    }
}

