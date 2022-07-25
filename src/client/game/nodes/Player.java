package client.game.nodes;

import client.game.engine.nodos.SpriteableNode;
import common.networking.packets.PlayerLoginPacket;
import client.game.engine.GameContainer;
import client.game.engine.GameNode;
import client.game.engine.core.Input;
import common.networking.packets.PlayerMovePacket;
import client.game.engine.nodos.CollideNode;
import client.game.engine.nodos.NodeCenterable;
import client.game.engine.nodos.NodeColladable;
import client.utils.ImageUtils;
import client.utils.game.collitions.CenterBorders;
import client.utils.game.collitions.CollideBox;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Player extends GameNode implements NodeCenterable, SpriteableNode, NodeColladable {
    private int velocity = 4;
    private BufferedImage[] movingLeft;
    private BufferedImage[] movingRight;
    private BufferedImage[] staticDuckLeft;
    private BufferedImage[] staticDuckRight;
    private SpriteNode sprite;
    private CollideNode collideNode;
    private int directionX = 1;

    public Player() {
        // Sub nodo de colision
        this.collideNode = new CollideNode(this);
        this.collideNode.setShowCollitionsShape(true);// (Solo activar para debuggear)
        this.addNode(this.collideNode);
        // Sub nodo de sprites
        this.sprite = new SpriteNode(this);
        this.addNode(this.sprite);
        // Init Images
        this.initPlayerImages();
    }

    private void initPlayerImages() {
        try {
            BufferedImage[] staticSpriteLeft = {
                    ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/walking/cuak7.png")),
            };
            this.staticDuckLeft = staticSpriteLeft;
            this.staticDuckRight = ImageUtils.flipXImageArray(this.staticDuckLeft);

            BufferedImage[] movingLeft = {
                    ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/walking/cuak1.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/walking/cuak2.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/walking/cuak3.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/walking/cuak4.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/walking/cuak5.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/walking/cuak6.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/walking/cuak7.png"))
            };
            this.movingLeft = movingLeft;
            this.movingRight = ImageUtils.flipXImageArray(this.movingLeft);
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

        boolean canWalking = false;
        
        if (isWalking) {
            if (input.isKey(KeyEvent.VK_W)) {
                if (this.collideNode.canMove(container, this.x, this.y - velocity)) {
                    canWalking = true;
                    y -= velocity;
                } else {
                    while (this.collideNode.canMove(container, this.x, this.y - 1)) {
                        canWalking = true;
                        y -= 1;
                    }
                }
            }
            if (input.isKey(KeyEvent.VK_S)) {
                if (this.collideNode.canMove(container, this.x, this.y + velocity)) {
                    y += velocity;
                    canWalking = true;
                } else {
                    while (this.collideNode.canMove(container, this.x, this.y + 1)) {
                        y += 1;
                        canWalking = true;
                    }
                }
            }
            if (input.isKey(KeyEvent.VK_A)) {
                if (this.collideNode.canMove(container, this.x - velocity, this.y)) {
                    this.directionX = -1;
                    canWalking = true;
                    x -= velocity;
                } else {
                    while (this.collideNode.canMove(container, this.x - 1, this.y)) {
                        x -= 1;
                        this.directionX = -1;
                        canWalking = true;
                    }
                }
            }
            if (input.isKey(KeyEvent.VK_D)) {
                if (this.collideNode.canMove(container, this.x + velocity, this.y)) {
                    x += velocity;
                    this.directionX = 1;
                    canWalking = true;
                } else {
                    while (this.collideNode.canMove(container, this.x + 1, this.y)) {
                        x += 1;
                        this.directionX = 1;
                        canWalking = true;
                    }
                }
            }
            container.getNetwork().sendPacket(new PlayerMovePacket(this.x, this.y));
        }

        if (canWalking) {
            if (directionX == 1) {
                this.sprite.setSprite(movingRight);
            } else if (directionX == -1) {
                this.sprite.setSprite(movingLeft);
            }
            this.sprite.setSpeed(5);
        }else{
            if (directionX == 1) {
                this.sprite.setSprite(staticDuckRight);
            } else if (directionX == -1) {
                this.sprite.setSprite(staticDuckLeft);
            }
            this.sprite.setSpeed(-1);
        }
    }

    @Override
    public void draw(GameContainer container, Graphics2D g2) {}

    @Override
    public String getNodeTag() {
        return "Player";
    }

    public int getOffsetX() {
        return 25;
    }

    public int getOffsetY() {
        return 25;
    }

    public int getWidth() {
        return 50;
    }

    public int getHeight() {
        return 50;
    }

    public CenterBorders getCenterBorders() {
        return new CenterBorders(20, 24, 20, 20);
    }        
            
    @Override
    public CollideBox getCollideBox() {
        return this.collideNode.getPositionCollideBox(this.x, this.y);
    }
}
