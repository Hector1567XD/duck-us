package client.game.nodes;

import common.packets.PlayerLoginPacket;
import client.game.engine.GameContainer;
import client.game.engine.GameController;
import client.game.engine.GameNode;
import client.game.engine.core.Input;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Player extends GameNode {
    private int velocity = 4;

    @Override
    public void created(GameContainer container) {
        //container.getNetwork().sendPacket(new PlayerLoginPacket("Feredev"));
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
        
        if (input.isKeyDown(KeyEvent.VK_P)) {
            disparar(container);
        }
    }

    private void disparar(GameContainer container) {
            int x1 = this.x;
            int y1 = this.y;
            int x2 = container.getInput().getMouseX();
            int y2 = container.getInput().getMouseY();
            int angulo = (int)(Math.atan((y2-y1)/(x2-x1)));
            GameController controller = container.getController();
            container.getController();
            controller.addNode(new Bullet(this,angulo)); //Le pasamos como atributo a bullet la misma clase donde nos ubicamos osea player
    }

    @Override
    public void draw(GameContainer container, Graphics2D g2) {
        int scale = container.getScale().getScale();
        int tileSize = container.getScale().getOriginalTileSize();
        g2.fillRect(x * scale, y * scale, tileSize * scale, tileSize * scale);
    }
}
