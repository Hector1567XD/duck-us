package client.game.nodes;

import common.networking.packets.PlayerLoginPacket;
import client.game.engine.GameContainer;
import client.game.engine.GameNode;
import client.game.engine.core.Input;
import client.game.engine.nodos.NodeCenterable;
import client.game.engine.nodos.NodeColladable;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player extends GameNode implements NodeCenterable, NodeColladable {
    private int velocity = 4;
    


    @Override
    public void created(GameContainer container) {
        container.getNetwork().sendPacket(new PlayerLoginPacket("Feredev"));
    }

    @Override
    public void update(GameContainer container) {
        Input input = container.getInput();
        boolean isWalking = input.isKey(KeyEvent.VK_W) || input.isKey(KeyEvent.VK_S) || input.isKey(KeyEvent.VK_A) || input.isKey(KeyEvent.VK_D);

        if (isWalking) {
            if (input.isKey(KeyEvent.VK_W)) {
                if (canMove(container, this.x, this.y - velocity)) {
                    y -= velocity;    
                }else{
                    while(canMove(container, this.x, this.y - 1)) {
                        y-=1;
                    }
                }
            }
            if (input.isKey(KeyEvent.VK_S)) {
                if (canMove(container, this.x, this.y + velocity)) {
                    y += velocity;    
                }else{
                    while(canMove(container, this.x, this.y + 1)) {
                        y+=1;
                    }
                }
            }
            if (input.isKey(KeyEvent.VK_A)) {
                if (canMove(container, this.x - velocity, this.y)) {
                    x -= velocity;    
                }else{
                    while(canMove(container, this.x - 1, this.y)) {
                        x-=1;
                    }
                }
            }
            if (input.isKey(KeyEvent.VK_D)) {
                if (canMove(container, this.x + velocity, this.y)) {
                    x += velocity;    
                }else{
                    while(canMove(container, this.x + 1, this.y)) {
                        x+=1;
                    }
                }
            }
        }
    }

    @Override
    public void draw(GameContainer container, Graphics2D g2) {
        int scale = container.getScale().getScale();
        int tileSize = container.getScale().getOriginalTileSize();
        g2.setColor(Color.gray);
        int alto = tileSize * scale;
        int ancho = tileSize * scale;
        int offSetX = this.getOffsetX() * scale;
        int offSetY = this.getOffsetY() * scale;

        g2.fillRect((x * scale) - offSetX, (y * scale) - offSetY, alto, ancho);
        g2.setColor(Color.red);
        g2.fillRect(x * scale, y * scale, 2 * scale, 2 * scale);

        ArrayList<Bloque> bloquesitos = container.getController().getNodes().getListByTag("Bloque");
        for (Bloque i : bloquesitos) {
            if (i != null) {
                if (this.isCollaiding(i)) {
                    g2.setColor(Color.blue);
                    g2.fillRect((x * scale) - offSetX, (y * scale) - offSetY, alto, ancho);
                }
            }
        }
    }

    public boolean isPositionCollaiding(NodeColladable otherNode, int x, int y) {
        if ((x + this.getRightCenter() >= otherNode.getX() - otherNode.getLeftCenter())
                && (x - this.getRightCenter() <= otherNode.getX() + otherNode.getLeftCenter())
                && (y + this.getBottomCenter() >= otherNode.getY() - otherNode.getTopCenter())
                && (y - this.getBottomCenter() <= otherNode.getY() + otherNode.getBottomCenter()))
            return true;

        return false;
    }

    // to do (esto va en update)
    public boolean canMove(GameContainer container, int x, int y) {
        ArrayList<Bloque> bloquesitos = container.getController().getNodes().getListByTag("Bloque");

        for (Bloque i : bloquesitos) {
            if (isPositionCollaiding(i, x, y)) {
                return false;
            }
        }
        return true;
    }

    public boolean isCollaiding(NodeColladable otherNode) {
        return isPositionCollaiding(otherNode, x, y);
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

    public int getTopCenter() {
        return 16;
    }

    public int getLeftCenter() {
        return 16;
    }

    public int getRightCenter() {
        return 16;
    }

    public int getBottomCenter() {
        return 16;
    }
}
