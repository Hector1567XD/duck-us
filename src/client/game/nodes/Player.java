package client.game.nodes;

import common.networking.packets.PlayerLoginPacket;
import client.game.engine.GameContainer;
import client.game.engine.GameNode;
import client.game.engine.core.Input;
import client.game.engine.nodos.NodeCenterable;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Player extends GameNode implements NodeCenterable, SpriteableNode {
    private int velocity = 4;
    private BufferedImage[] movingLeft;
    private BufferedImage[] movingRight;
    private SpriteNode sprite;

    public Player() {
        this.sprite = new SpriteNode(this);
        this.addNode(this.sprite);
        this.initPlayerImages();
    }

    private void initPlayerImages() {
        try {
            BufferedImage[] movingLeft = { 
                ImageIO.read(getClass().getResourceAsStream("/client/game/nodes/images/pato walk 1.png")),
                ImageIO.read(getClass().getResourceAsStream("/client/game/nodes/images/pato walk 2.png")), 
                ImageIO.read(getClass().getResourceAsStream("/client/game/nodes/images/pato walk 3.png")),
                ImageIO.read(getClass().getResourceAsStream("/client/game/nodes/images/pato walk 4.png")), 
                ImageIO.read(getClass().getResourceAsStream("/client/game/nodes/images/pato walk 5.png")), 
                ImageIO.read(getClass().getResourceAsStream("/client/game/nodes/images/pato walk 6.png")), 
                ImageIO.read(getClass().getResourceAsStream("/client/game/nodes/images/pato walk 7.png"))
            };
            this.movingLeft = movingLeft;
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void created(GameContainer container) {
        container.getNetwork().sendPacket(new PlayerLoginPacket("Feredev"));
    }

    @Override
    public void update(GameContainer container) {
        Input input = container.getInput();
        boolean isWalking = input.isKey(KeyEvent.VK_W) || input.isKey(KeyEvent.VK_S) || input.isKey(KeyEvent.VK_A)
                || input.isKey(KeyEvent.VK_D);

        if (isWalking) {
            if (input.isKey(KeyEvent.VK_W)) {
                y -= velocity;
                this.sprite.setSpeed(5);
            }
            if (input.isKey(KeyEvent.VK_S)) {
                y += velocity;
                this.sprite.setSpeed(5);
            }
            if (input.isKey(KeyEvent.VK_A)) {
                this.sprite.setSprite(movingLeft);
                this.sprite.setSpeed(5);
                x -= velocity;
            }
            if (input.isKey(KeyEvent.VK_D)) {
                x += velocity;
                this.sprite.setSpeed(5);
            } else {
                this.sprite.setSpeed(-1);
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
    }

    @Override
    public String getNodeTag() {
        return "Player";
    }

    public int getOffsetX() {
        return 42;
    }

    public int getOffsetY() {
        return 35;
    }

    public int getWidth() {
        return 84;
    }

    public int getHeight() {
        return 71;
    }

}
