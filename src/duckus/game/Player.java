package duckus.game;

import duckus.engine.GameContainer;
import duckus.engine.GameNode;
import duckus.engine.core.Input;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Player extends GameNode {
    private int velocity = 4;

    @Override
    public void created(GameContainer container) {
        
    }

    @Override
    public void update(GameContainer container) {
        Input input = container.getInput();
        //int scale = container.getScale().getScale();
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
        int tileSize = container.getScale().getTileSize();
        g2.fillRect(x * scale, y * scale, tileSize, tileSize);
    }
}
