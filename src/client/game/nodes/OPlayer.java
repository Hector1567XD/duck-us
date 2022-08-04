package client.game.nodes;

import client.game.engine.GameContainer;
import client.game.engine.GameNode;
import client.game.engine.nodos.NodeCenterable;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class OPlayer extends GameNode implements NodeCenterable {
    private int velocity = 4;
    private int playerId;
    private String name;
    private boolean emergencyRun;
    private int time;
  
    
    
    

    public OPlayer(int playerId, String name) {
        this.playerId = playerId;
        this.name = name;
    }

    @Override
    public void created(GameContainer container) {
    
        
        time = 10*120;
    }

    
    
    
    @Override
    public void update(GameContainer container) {
      
        if (emergencyRun==true){
            if (time<=0) {
                 emergencyRun = false;
                 time = 10*120;
            }else {
                 time--; 
                }  
        }
    
    }


    
    @Override
    public void draw(GameContainer container, Graphics2D g2) {
        g2.setColor(Color.GRAY);
        int scale = container.getScale().getScale();
        int tileSize = container.getScale().getOriginalTileSize();
        g2.fillRect(drawX - getOffsetX()*scale, drawY - getOffsetY()*scale, tileSize * scale, tileSize * scale);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font( "Arial", Font.BOLD, 12*scale ));
        g2.drawString(this.name + "(" + this.playerId + ")", drawX - 8*scale, drawY + 40 * scale);
    }

    public int getPlayerId() {
        return playerId;
    }

    public boolean isEmergencyRun() {
        return emergencyRun;
    }

    public void setEmergencyRun(boolean emergencyRun) {
        this.emergencyRun = emergencyRun;
    }
    

    public int getOffsetX() {
        return 16;
    }

    public int getOffsetY() {
        return 16;
    }
}
