package client.game.nodes;

import java.awt.*;
import java.awt.event.*;
import common.packets.PlayerLoginPacket;
import client.game.engine.GameContainer;
import client.game.engine.GameNode;
import client.game.engine.core.Input;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Prueba extends GameNode {

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


    
    
}