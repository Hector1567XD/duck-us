package client.game.nodes;

import common.networking.packets.PlayerLoginPacket;
import client.game.engine.GameContainer;
import client.game.engine.GameNode;
import client.game.engine.core.Input;
import client.game.engine.nodos.NodeCenterable;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Player extends GameNode implements NodeCenterable {
    private int velocity = 4;
    private boolean impostor = false;
    private int screenX;
    private int screenY;

    @Override
    public void created(GameContainer container) {
        int random;
                
        Random rand = new Random();
        random = rand.nextInt(1); 
        if (random==1 || random==0) {
        System.out.println("soy impostor :D");
        impostor = true;
        }
        
        screenX = container.getWindow().getScreenWidth()/2 - container.getScale().getTileSize()/2;
        screenY = container.getWindow().getScreenHeight()/2 - container.getScale().getTileSize()/2; 



        container.getNetwork().sendPacket(new PlayerLoginPacket("Feredev"));
    }

    @Override
    public void update(GameContainer container) {
        Input input = container.getInput();
        boolean isWalking = input.isKey(KeyEvent.VK_W) || input.isKey(KeyEvent.VK_S) || input.isKey(KeyEvent.VK_A) || input.isKey(KeyEvent.VK_D);

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
        }
    }

    @Override
    public void draw(GameContainer container, Graphics2D g2) {
        int scale = container.getScale().getScale();
        int tileSize = container.getScale().getOriginalTileSize();
          // cosas que van en el tile set //
      /* int  worldX = worldcol * scale;
       int  worldY = worldrow * scale;
       int screenX = worldX - container.getController().getNodes().findByName("Player").worldX+container.getController().getNodes().findByName("Player").screenX;
       int screenY = worldY - container.getController().getNodes().findByName("Player").worldY +container.getController().getNodes().findByName("Player").screenY;  
        */


        g2.setColor(Color.gray);
        int alto = tileSize * scale;
        int ancho = tileSize * scale;
        int offSetX = this.getOffsetX() * scale;
        int offSetY = this.getOffsetY() * scale;
        
        g2.fillRect((x * scale) - offSetX, (y * scale) - offSetY, alto, ancho);
        g2.setColor(Color.red);
        g2.fillRect(x * scale, y * scale, 2 * scale, 2 * scale);
    }
    
    @Override
    public String getNodeTag() {
        return "Player";
    }

    public int getOffsetX() {
        return 16;
    }

    public int getOffsetY() {
        return 16;
    }
}
