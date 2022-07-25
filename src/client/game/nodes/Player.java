package client.game.nodes;

import common.networking.packets.PlayerLoginPacket;
import client.game.engine.GameContainer;
import client.game.engine.GameNode;
import client.game.engine.core.Input;
import common.networking.packets.PlayerMovePacket;
import client.game.engine.nodos.CollideNode;
import client.game.engine.nodos.NodeCenterable;
import client.game.engine.nodos.NodeColladable;
import client.utils.game.collitions.CenterBorders;
import client.utils.game.collitions.CollideBox;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Player extends GameNode implements NodeCenterable, SpriteableNode, NodeColladable {
    private int velocity = 4;
    private BufferedImage[] movingLeft;
    private BufferedImage[] movingRight;
    private BufferedImage[] staticDuck;
    private SpriteNode sprite;
    private CollideNode collideNode;

    public Player() {
        // Sub nodo de sprites
        this.sprite = new SpriteNode(this);
        this.addNode(this.sprite);
        // Sub nodo de colision
        this.collideNode = new CollideNode(this);
        this.collideNode.setShowCollitionsShape(true);// (Solo activar para debuggear)
        this.addNode(this.collideNode);
        // Init Images
        this.initPlayerImages();
    }

    private void initPlayerImages() {
        try {
            BufferedImage[] staticSprite = {
                    ImageIO.read(getClass().getResourceAsStream("/client/game/nodes/images/cuak1.png")),
            };
            this.staticDuck = staticSprite;

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
        this.x = 235;
        this.y = 200;
        container.getNetwork().sendPacket(new PlayerLoginPacket("Feredev"));
    }

    @Override
    public void update(GameContainer container) {
        Input input = container.getInput();
        boolean isWalking = input.isKey(KeyEvent.VK_W) || input.isKey(KeyEvent.VK_S) || input.isKey(KeyEvent.VK_A)
                || input.isKey(KeyEvent.VK_D);

        if (isWalking) {
            if (input.isKey(KeyEvent.VK_W)) {
                if (this.collideNode.canMove(container, this.x, this.y - velocity)) {
                    this.sprite.setSprite(movingLeft);
                    this.sprite.setSpeed(5);
                    y -= velocity;
                } else {
                    while (this.collideNode.canMove(container, this.x, this.y - 1)) {
                        this.sprite.setSprite(movingLeft);
                        this.sprite.setSpeed(5);
                        y -= 1;
                    }
                }
            }
            if (input.isKey(KeyEvent.VK_S)) {
                if (this.collideNode.canMove(container, this.x, this.y + velocity)) {
                    y += velocity;
                    this.sprite.setSprite(movingLeft);
                    this.sprite.setSpeed(5);
                } else {
                    while (this.collideNode.canMove(container, this.x, this.y + 1)) {
                        y += 1;
                        this.sprite.setSprite(movingLeft);
                        this.sprite.setSpeed(5);
                    }
                }
            }
            if (input.isKey(KeyEvent.VK_A)) {
                if (this.collideNode.canMove(container, this.x - velocity, this.y)) {
                    this.sprite.setSprite(movingLeft);
                    this.sprite.setSpeed(5);
                    x -= velocity;
                } else {
                    while (this.collideNode.canMove(container, this.x - 1, this.y)) {
                        x -= 1;
                        this.sprite.setSprite(movingLeft);
                        this.sprite.setSpeed(5);
                    }
                }
            }
            if (input.isKey(KeyEvent.VK_D)) {
                if (this.collideNode.canMove(container, this.x + velocity, this.y)) {
                    x += velocity;
                    this.sprite.setSprite(movingRight);
                    this.sprite.setSpeed(5);
                } else {
                    while (this.collideNode.canMove(container, this.x + 1, this.y)) {
                        x += 1;
                        this.sprite.setSprite(movingRight);
                        this.sprite.setSpeed(5);
                    }
                }
            }
            container.getNetwork().sendPacket(new PlayerMovePacket(this.x, this.y));
        } else {
            this.sprite.setSprite(staticDuck);
            this.sprite.setSpeed(-1);
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

    public CenterBorders getCenterBorders() {
        return new CenterBorders(16, 16, 16, 16);
    }        
            
    @Override
    public CollideBox getCollideBox() {
        return this.collideNode.getPositionCollideBox(this.x, this.y);
    }
}
