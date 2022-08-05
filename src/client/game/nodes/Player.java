package client.game.nodes;

import client.game.engine.nodos.SpriteNode;
import client.game.engine.nodos.SpriteableNode;
import common.networking.packets.PlayerLoginPacket;
import client.game.engine.GameContainer;
import client.game.engine.GameNode;
import client.game.engine.nodos.NodeKilleable;
import common.CommonConstants;
import common.networking.packets.PlayerKillPacket;
import common.networking.packets.PlayerMovePacket;
import common.utils.NodeDistanceHelper;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import client.game.engine.core.Input;
import client.game.engine.nodos.CollideNode;
import client.game.engine.nodos.NodeColladable;
import client.utils.ImageUtils;
import client.utils.game.collitions.CenterBorders;
import client.utils.game.collitions.CollideBox;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends GameNode implements SpriteableNode, NodeColladable, NodeKilleable {
    private int velocity = 4;
    private boolean misionOpen = false;
    private BufferedImage[] movingLeft;
    private BufferedImage[] movingRight;
    private BufferedImage[] staticDuckLeft;
    private BufferedImage[] staticDuckRight;
    private BufferedImage[] spriteDead;
    private SpriteNode sprite;
    private CollideNode collideNode;
    private int directionX = 1;
    private boolean impostor;
    private boolean alredyKill;
    private int timer;
    private boolean isDead;

    public Player() {
        // Sub nodo de colision
        this.collideNode = new CollideNode(this);
        this.collideNode.setShowCollitionsShape(false);// (Solo activar para debuggear)
        this.addNode(this.collideNode);
        // Sub nodo de sprites
        this.sprite = new SpriteNode(this);
        this.addNode(this.sprite);
        // Init Images
        this.initPlayerImages();
    }

    private void initPlayerImages() {
        try {
            BufferedImage[] spriteDead = {
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/dead/dead.png")),
            };
            this.spriteDead = spriteDead;

            BufferedImage[] staticSpriteLeft = {
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/walking/cuak7.png")),};
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
        alredyKill = false;
        timer = 10 * 60;

        impostor = true;
        if (impostor) {
            System.out.println("soy impostor :D");
        }
    }

    @Override
    public void update(GameContainer container) {
        Input input = container.getInput();
        boolean isWalking = input.isKey(KeyEvent.VK_W) || input.isKey(KeyEvent.VK_S) || input.isKey(KeyEvent.VK_A)
                || input.isKey(KeyEvent.VK_D);
        boolean canWalking = false;

        // LOGICA DE IMPOSTOR
        if (impostor) {
            if (alredyKill == true) {
                if (timer <= 0) {
                    alredyKill = false;
                    timer = 10 * 60;
                } else {
                    timer--;
                }
            }
            if (input.isKey(KeyEvent.VK_E)) {
                this.Kill(container);
            }
        }

        // IS WALKING
        if (isWalking && !misionOpen) {
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

        // P Mission abrir mision
        if (input.isKey(KeyEvent.VK_P)) {
            ArrayList<NodeOpenable> missions = container.getController().getNodes().getListByTag("mission");
    
            for (NodeOpenable mision : missions) {
                if (this.collideNode.isColliding(mision) && mision.isGanaste() == false) {
                    //System.out.println("si :)");
                    misionOpen = true;
                    mision.setMisionAbierta(true);
                }
            }
        }
    
        // Cerrar mision
        ArrayList<NodeOpenable> missions = container.getController().getNodes().getListByTag("mission");
        for (NodeOpenable mision : missions) {
            if (this.collideNode.isColliding(mision)) {
                mision.setIsCercaPlayer(true);
                if (input.isKey(KeyEvent.VK_X)) {
                    //System.out.println("no :)");  
                    misionOpen = false;
                    mision.setMisionAbierta(false);
                }
            }else{
                mision.setIsCercaPlayer(false);
            }
        }

        // ESTA CAMINANDO ?
        if (canWalking) {
            if (directionX == 1) {
                this.sprite.setSprite(movingRight);
            } else if (directionX == -1) {
                this.sprite.setSprite(movingLeft);
            }
            this.sprite.setSpeed(5);
        } else {
            if (directionX == 1) {
                this.sprite.setSprite(staticDuckRight);
            } else if (directionX == -1) {
                this.sprite.setSprite(staticDuckLeft);
            }
            this.sprite.setSpeed(-1);
        }

        if (isDead) {
            this.sprite.setSprite(spriteDead);
        }
    }

    @Override
    public void draw(GameContainer container, Graphics2D g2) {
    }

    @Override
    public String getNodeTag() {
        return "Player";
    }

    public void setIsDead(boolean dead) {
        isDead = dead;
    }

    public void Kill(GameContainer container) {
        ArrayList<OPlayer> listPlayers = container.getController().getNodes().getListByTag("Oplayer");
        for (OPlayer victima : listPlayers) {
            double killD = NodeDistanceHelper.getDistance(this, victima);
            if (killD < CommonConstants.DISTANCE_TO_KILL && alredyKill == false) {
                alredyKill = true;
                System.out.println("Muerto");
                container.getNetwork().sendPacket(new PlayerKillPacket(victima.getPlayerId()));
            }

        }
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

    public void setMisionOpen(boolean misionOpen) {
        this.misionOpen = misionOpen;
    }

    public int getNodeLevel() {
        return 150;
    }
}
