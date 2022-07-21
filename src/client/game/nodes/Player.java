package client.game.nodes;

import common.networking.packets.PlayerLoginPacket;
import client.game.engine.GameContainer;
import client.game.engine.GameNetwork;
import client.game.engine.GameNode;
import client.game.engine.core.Input;
import client.game.engine.nodos.NodeCenterable;
import client.game.engine.nodos.NodeKilleable;
import common.CommonConstants;
import common.networking.packets.PlayerKillPacket;
import common.networking.packets.PlayerMovePacket;
import common.utils.NodeDistanceHelper;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player extends GameNode implements NodeCenterable, NodeKilleable {
    private int velocity = 4;
    private int screenX;
    private int screenY;
    private boolean impostor;
    private boolean alredyKill;
    private int timer;
    private boolean isDead;
    
    @Override
    public void created(GameContainer container) {
        screenX = (container.getScale().getTileSize() * container.getMaxMapCol() )/2;
        screenY = (container.getScale().getTileSize() * container.getMaxMapRow() )/2; 
        GameNetwork network = container.getNetwork();
        network.sendPacket(new PlayerLoginPacket("Feredev"));
        alredyKill = false;
        timer = 10*60;
        
        
        impostor = true;
        if (impostor) {
            System.out.println("soy impostor :D");
        }
        
    }

    @Override
    public void update(GameContainer container) {  
        Input input = container.getInput();
        boolean isWalking = input.isKey(KeyEvent.VK_W) || input.isKey(KeyEvent.VK_S) || input.isKey(KeyEvent.VK_A) || input.isKey(KeyEvent.VK_D);

        if (impostor) {
          if (alredyKill==true){
           if (timer<=0) {
                    alredyKill = false;
                    timer = 10*60;
             }else {
                   timer--; 
              }   
          }
          
            if (input.isKey(KeyEvent.VK_E)) {
               this.Kill(container);
            }
        }
   
        if (isWalking) {
            if (input.isKey(KeyEvent.VK_W)) {
                y -= velocity;
            }
            if (input.isKey(KeyEvent.VK_S)) {
                y += velocity;
            }
            if (input.isKey(KeyEvent.VK_A)) {
                x -= velocity;
            }
            if (input.isKey(KeyEvent.VK_D)) {
                x += velocity;
            }
            container.getNetwork().sendPacket(new PlayerMovePacket(this.x, this.y));
        }
    }

    @Override
    public void draw(GameContainer container, Graphics2D g2) {
        g2.setColor(Color.GRAY);
        int scale = container.getScale().getScale();
        int tileSize = container.getScale().getOriginalTileSize();
      
        g2.setColor(Color.gray);
        int alto = tileSize * scale;
        int ancho = tileSize * scale;
        int offSetX = this.getOffsetX() * scale;
        int offSetY = this.getOffsetY() * scale;
        
        g2.fillRect(drawX - offSetX, drawY - offSetY, alto, ancho);
        g2.setColor(Color.red);
        g2.fillRect(drawX, drawY, 2 * scale, 2 * scale);
        
        g2.setColor(Color.WHITE);
        g2.drawString(timer+":", drawX + 4*scale - offSetX, drawY - offSetY + 40 * scale);
        
    }

    @Override
    public String getNodeTag() {
        return "Player";
    }

    public void setIsDead(boolean dead){
        isDead = dead;
    }
    
    public void Kill(GameContainer container) {
         ArrayList<OPlayer> listPlayers = container.getController().getNodes().getListByTag("Oplayer");
                 for (OPlayer victima : listPlayers){
                   double killD = NodeDistanceHelper.getDistance(this, victima);
                   if (NodeDistanceHelper.getDistance(this, victima)<CommonConstants.DISTANCE_TO_KILL && alredyKill==false) {
                      alredyKill = true;
                      System.out.println("Muerto");
                      container.getNetwork().sendPacket(new PlayerKillPacket(victima.getPlayerId()));
                    }

                 }
    }
    
    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public int getOffsetX() {
        return 16;
    }

    public int getOffsetY() {
        return 16;
    }
}
