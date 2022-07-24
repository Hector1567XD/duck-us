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
    private BufferedImage[] StaticDuck;
    private SpriteNode sprite;

    public Player() {
        this.sprite = new SpriteNode(this);
        this.addNode(this.sprite);
        this.initPlayerImages();
    }

    private void initPlayerImages() {
        try {
            BufferedImage[] Static = {
                    ImageIO.read(getClass().getResourceAsStream("/client/game/nodes/images/cuak1.png")),
            };
            this.StaticDuck = Static;

            BufferedImage[] movingLeft = {
                    ImageIO.read(getClass().getResourceAsStream("/client/game/nodes/images/cuak1.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/game/nodes/images/cuak2.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/game/nodes/images/cuak3.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/game/nodes/images/cuak4.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/game/nodes/images/cuak5.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/game/nodes/images/cuak6.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/game/nodes/images/cuak7.png"))
            };
            this.movingLeft = movingLeft;

            BufferedImage[] movingRight = {
                    ImageIO.read(getClass().getResourceAsStream("/client/game/nodes/images/cuak1-right.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/game/nodes/images/cuak2-right.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/game/nodes/images/cuak3-right.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/game/nodes/images/cuak4-right.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/game/nodes/images/cuak5-right.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/game/nodes/images/cuak6-right.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/game/nodes/images/cuak7-right.png"))
            };
            this.movingRight = movingRight;
        } catch (IOException e) {
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
                this.sprite.setSprite(movingLeft);
                this.sprite.setSpeed(5);
                y -= velocity;
            }
            if (input.isKey(KeyEvent.VK_S)) {
                this.sprite.setSprite(movingLeft);
                this.sprite.setSpeed(5);
                y += velocity;
            }
            if (input.isKey(KeyEvent.VK_A)) {
                this.sprite.setSprite(movingLeft);
                this.sprite.setSpeed(5);
                x -= velocity;
            }
            if (input.isKey(KeyEvent.VK_D)) {
                this.sprite.setSprite(movingRight);
                this.sprite.setSpeed(5);
                x += velocity;
            }
        } else {
            this.sprite.setSprite(StaticDuck);
            this.sprite.setSpeed(-1);
        }
    }

    @Override
    public void draw(GameContainer container, Graphics2D g2) {
        /*
         * int scale = container.getScale().getScale();
         * int tileSize = container.getScale().getOriginalTileSize();
         * g2.setColor(Color.gray);
         * int alto = tileSize * scale;
         * int ancho = tileSize * scale;
         * int offSetX = this.getOffsetX() * scale;
         * int offSetY = this.getOffsetY() * scale;
         * 
         * g2.fillRect((x * scale) - offSetX, (y * scale) - offSetY, alto, ancho);
         * g2.setColor(Color.red);
         * g2.fillRect(x * scale, y * scale, 2 * scale, 2 * scale);
         */
    }

    @Override
    public String getNodeTag() {
        return "Player";
    }

    public int getOffsetX() {
        return 24;
    }

    public int getOffsetY() {
        return 24;
    }

    public int getWidth() {
        return 50;
    }

    public int getHeight() {
        return 50;
    }

}
