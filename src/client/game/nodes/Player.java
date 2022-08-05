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

import java.awt.Color;
import java.awt.Font;
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
import javax.imageio.ImageIO;
import java.io.IOException;

public class Player extends GameNode implements SpriteableNode, NodeColladable, NodeKilleable {

    private int velocity = 4;
    private BufferedImage[] movingLeft;
    private BufferedImage[] movingRight;
    private BufferedImage[] staticDuckLeft;
    private BufferedImage[] staticDuckRight;
    private BufferedImage[] spriteDead;
    private BufferedImage[] spriteButton;
    private BufferedImage[] spriteDying;
    private BufferedImage[] spriteKillingLeft;
    private BufferedImage[] spriteKillingRight;
    private SpriteNode sprite;
    private SpriteNode sprite2;
    private CollideNode collideNode;
    private int directionX = 1;
    private boolean impostor;
    private boolean alredyKill;
    private int timer;
    private boolean isDead;
    // private boolean wasAnimated = false;
    private boolean isKilling = false;
    private boolean isDying = false;

    public Player() {
        // Sub nodo de colision
        this.collideNode = new CollideNode(this);
        this.collideNode.setShowCollitionsShape(false);// (Solo activar para debuggear)
        this.addNode(this.collideNode);
        // Sub nodo de sprites
        this.sprite = new SpriteNode(this);
        this.addNode(this.sprite);
        // Sub nodo de sprites2
        this.sprite2 = new SpriteNode(this, 410, 255, 100, 100);
        this.addNode(this.sprite2);
        // Init Images
        this.initPlayerImages();
    }

    private void initPlayerImages() {
        try {
            BufferedImage[] spriteDead = {
                    ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/dead/dead.png")),
            };
            this.spriteDead = spriteDead;

            BufferedImage[] spriteDying = {
                    ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/dying/die1.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/dying/die2.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/dying/die3.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/dying/die4.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/dying/die5.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/dying/die6.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/dying/die7.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/dying/die8.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/dying/die9.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/dying/die10.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/dying/die11.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/dying/die11.png")),
            };
            this.spriteDying = spriteDying;

            BufferedImage[] spriteKillingLeft = {
                    ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/killingLeft/killLeft1.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/killingLeft/killLeft2.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/killingLeft/killLeft3.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/killingLeft/killLeft4.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/killingLeft/killLeft5.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/killingLeft/killLeft5.png")),
                    
            };
            this.spriteKillingLeft = spriteKillingLeft;

            BufferedImage[] spriteKillingRight = {
                    ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/killingRight/killRight1.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/killingRight/killRight2.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/killingRight/killRight3.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/killingRight/killRight4.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/killingRight/killRight5.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/killingRight/killRight5.png")),
            
            };
            this.spriteKillingRight = spriteKillingRight;

            BufferedImage[] spriteButton = {
                    ImageIO.read(getClass().getResourceAsStream("/client/resources/game/buttons/killButton.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/resources/game/buttons/killButtonWait.png")),
                    ImageIO.read(getClass().getResourceAsStream("/client/resources/game/buttons/killButtonNo.png"))
            };
            this.spriteButton = spriteButton;

            BufferedImage[] staticSpriteLeft = {
                    ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/walking/cuak7.png")), };
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

        if (isDying) {
            this.sprite.setSprite(spriteDying);
            this.sprite.setSpeed(5);

            if (this.sprite.getIndex() >= 10) {
                isDying = false;
                this.sprite.setSpeed(0);
            }
        } else {
            if (!isDead) {
                if (isKilling) {
                    if (directionX == 1) {
                        this.sprite.setSprite(spriteKillingRight);
                        this.sprite.setSpeed(5);
                    } else if (directionX == -1) {
                        this.sprite.setSprite(spriteKillingLeft);
                        this.sprite.setSpeed(5);
                    }
                    if (this.sprite.getIndex() >= 4 + 1) {
                        isKilling = false;
                        this.sprite.setSpeed(0);
                    }
                } else {
                    if (impostor) {
                        this.sprite2.setSprite(spriteButton);
                        this.sprite2.setIndex(0);

                        if (alredyKill == true) {
                            if (timer <= 0) {
                                alredyKill = false;
                                timer = 10 * 60;
                            } else {
                                timer--;
                            }
                        } else if ((canKill(container) == true)) {
                            // System.out.println("Puedes matar D:");
                            this.sprite2.setIndex(2);
                        }
                        if (input.isKey(KeyEvent.VK_E)) {
                            this.Kill(container);
                        }
                    } else {
                        this.sprite2.setSprite(null);
                    }

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
                    } else {
                        if (directionX == 1) {
                            this.sprite.setSprite(staticDuckRight);
                        } else if (directionX == -1) {
                            this.sprite.setSprite(staticDuckLeft);
                        }
                        this.sprite.setSpeed(-1);
                    }
                }

            } else {
                this.sprite.setSprite(spriteDead);
                this.sprite2.setSprite(null);
            }
        }
    }

    @Override
    public void draw(GameContainer container, Graphics2D g2) {
        if (alredyKill == true) {
            this.sprite2.setIndex(2);
            g2.setColor(Color.white);
            g2.setFont(new Font("Arial", Font.BOLD, 30));
            g2.drawString("   " + timer, 410, 255);
        }
    }

    @Override
    public String getNodeTag() {
        return "Player";
    }

    public void setIsDead(boolean dead) {
        isDead = dead;

        if (isDead == true) {
            isDying = true;
        }
    }

    public void Kill(GameContainer container) {
        ArrayList<OPlayer> listPlayers = container.getController().getNodes().getListByTag("Oplayer");
        for (OPlayer victima : listPlayers) {
            double killD = NodeDistanceHelper.getDistance(this, victima);
            if (killD < CommonConstants.DISTANCE_TO_KILL && alredyKill == false) {
                isKilling = true;
                alredyKill = true;
                System.out.println("Muerto");
                container.getNetwork().sendPacket(new PlayerKillPacket(victima.getPlayerId()));
            }
        }
    }

    public boolean canKill(GameContainer container) {
        ArrayList<OPlayer> listPlayers = container.getController().getNodes().getListByTag("Oplayer");
        for (OPlayer victima : listPlayers) {
            double killD = NodeDistanceHelper.getDistance(this, victima);
            if (killD < CommonConstants.DISTANCE_TO_KILL && alredyKill == false) {
                return true;
            }
        }
        return false;
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
