package client.game.nodes;

import common.networking.packets.PlayerLoginPacket;
import client.game.engine.GameContainer;
import client.game.engine.GameNode;
import client.game.engine.core.Input;
import client.game.engine.nodos.CollideNode;
import client.game.engine.nodos.NodeCenterable;
import client.game.engine.nodos.NodeColladable;
import client.utils.game.collitions.CenterBorders;
import client.utils.game.collitions.CollideBox;
import client.utils.game.collitions.CollitionsUtils;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Player extends GameNode implements NodeCenterable, NodeColladable {
    private int velocity = 4;
    private CollideNode collideNode;

    @Override
    public void created(GameContainer container) {
        this.x = 235;
        this.y = 200;
        container.getNetwork().sendPacket(new PlayerLoginPacket("Feredev"));
        this.collideNode = new CollideNode(this);
        //this.collideNode.setShowCollitionsShape(true);// (Solo activar para debuggear)
        this.addNode(this.collideNode);
    }

    @Override
    public void update(GameContainer container) {
        Input input = container.getInput();
        boolean isWalking = input.isKey(KeyEvent.VK_W) || input.isKey(KeyEvent.VK_S) || input.isKey(KeyEvent.VK_A) || input.isKey(KeyEvent.VK_D);

        if (isWalking) {
            if (input.isKey(KeyEvent.VK_W)) {
                if (this.collideNode.canMove(container, this.x, this.y - velocity)) {
                    y -= velocity;
                } else {
                    while (this.collideNode.canMove(container, this.x, this.y - 1)) {
                        y -= 1;
                    }
                }
            }
            if (input.isKey(KeyEvent.VK_S)) {
                if (this.collideNode.canMove(container, this.x, this.y + velocity)) {
                    y += velocity;
                } else {
                    while (this.collideNode.canMove(container, this.x, this.y + 1)) {
                        y += 1;
                    }
                }
            }
            if (input.isKey(KeyEvent.VK_A)) {
                if (this.collideNode.canMove(container, this.x - velocity, this.y)) {
                    x -= velocity;
                } else {
                    while (this.collideNode.canMove(container, this.x - 1, this.y)) {
                        x -= 1;
                    }
                }
            }
            if (input.isKey(KeyEvent.VK_D)) {
                if (this.collideNode.canMove(container, this.x + velocity, this.y)) {
                    x += velocity;
                } else {
                    while (this.collideNode.canMove(container, this.x + 1, this.y)) {
                        x += 1;
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

        g2.fillRect(drawX - offSetX, drawY - offSetY, alto, ancho);
        g2.setColor(Color.red);
        g2.fillRect(drawX, drawY, 2 * scale, 2 * scale);
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

    public CenterBorders getCenterBorders() {
        return new CenterBorders(16, 16, 16, 16);
    }        
            
    @Override
    public CollideBox getCollideBox() {
        return this.collideNode.getPositionCollideBox(this.x, this.y);
    }
}
