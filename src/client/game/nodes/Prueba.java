package client.game.nodes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import client.game.engine.GameContainer;
import client.game.engine.GameNode;
import client.game.engine.core.Input;
import client.game.engine.nodos.NodeCenterable;
import client.game.engine.nodos.NodeColladable;

public class Prueba extends GameNode implements NodeCenterable, NodeColladable{

    private int velocity = 4;

    @Override
    public void created(GameContainer container) {
        this.x = 100;
        this.y = 300;
    }

    @Override
    public void update(GameContainer container) {
        Input input = container.getInput();
        boolean isWalking = input.isKey(KeyEvent.VK_UP) || input.isKey(KeyEvent.VK_DOWN) || input.isKey(KeyEvent.VK_LEFT) || input.isKey(KeyEvent.VK_RIGHT);

        if (isWalking) {
            if (input.isKey(KeyEvent.VK_UP)) {
                y -= velocity;
            }
            if (input.isKey(KeyEvent.VK_DOWN)) {
                y += velocity;
            }
            if (input.isKey(KeyEvent.VK_LEFT)) {
                x -= velocity;
            }
            if (input.isKey(KeyEvent.VK_RIGHT)) {
                x += velocity;
            }
        }
    }

    @Override
    public void draw(GameContainer container, Graphics2D g2) {
        int escala = container.getScale().getScale();
        g2.setColor(Color.blue);
		g2.fillOval(x*escala, y*escala,32*escala,32*escala);
	}

    public boolean isCollaiding(NodeColladable otherNode) {
        if ((this.x + this.getRightCenter() >= otherNode.getX() - otherNode.getLeftCenter()) && (this.x - this.getRightCenter() <= otherNode.getX() + otherNode.getLeftCenter()) && (this.y + this.getBottomCenter() >= otherNode.getY() - otherNode.getTopCenter()) && (this.y - this.getBottomCenter() <= otherNode.getY() + otherNode.getBottomCenter()))
            return true;
    
        return false;
    }

    @Override
    public String getNodeTag() {
        return "Prueba";
    }

    @Override
    public int getTopCenter() {
        return 16;
    }

    @Override
    public int getLeftCenter() {
        return 16;
    }

    @Override
    public int getRightCenter() {
        return 16;
    }

    @Override
    public int getBottomCenter() {
        return 16;
    }

    @Override
    public int getOffsetX() {
        return 16;
    }

    @Override
    public int getOffsetY() {
        return 16;
    }

    
    
}