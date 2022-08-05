package client.game.nodes;

import client.game.nodes.classes.Sound;
import client.game.engine.nodos.SpriteNode;
import client.game.engine.nodos.SpriteableNode;
import common.networking.packets.PlayerLoginPacket;
import client.game.engine.GameContainer;
import client.game.engine.GameNode;
import client.game.engine.core.Input;
import client.game.engine.nodos.NodeKilleable;
import common.CommonConstants;
import common.networking.packets.PlayerKillPacket;
import common.networking.packets.PlayerMovePacket;
import common.utils.NodeDistanceHelper;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import client.game.engine.nodos.CollideNode;
import client.game.engine.nodos.NodeColladable;
import client.game.nodes.classes.DuckImages;
import client.utils.game.collitions.CenterBorders;
import client.utils.game.collitions.CollideBox;
import java.awt.Graphics2D;

public class Player extends GameNode implements SpriteableNode, NodeColladable, NodeKilleable {

    private int velocity = 5;
    private boolean misionOpen = false;
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

    Sound sound = new Sound();
    private int soundCounter = 0;
    private int soundStep = 0;
    private int soundAcumulatorMax = 20;

    private String gameName;

    public Player(String nombre) {
        // Sub nodo de colision
        this.collideNode = new CollideNode(this);
        //this.collideNode.setShowCollitionsShape(false);// (Solo activar para debuggear)
        this.collideNode.setShowCollitionsShape(false);// (Solo activar para debuggear)
        this.addNode(this.collideNode);
        // Sub nodo de sprites
        this.sprite = new SpriteNode(this);
        this.addNode(this.sprite);
        // Sub nodo de sprites2
        this.sprite2 = new SpriteNode(this, 410, 255, 100, 100);
        this.addNode(this.sprite2);
        // Init Images
        this.gameName = nombre;
    }

    @Override
    public void created(GameContainer container) {
        this.x = 4032;
        this.y = 1440;
        /*this.y = 3200;
        this.x = 322;*/
 /*this.x = 455;
        this.y = 322;*/
        /*this.x = 235;
        this.y = 200;*/
        alredyKill = false;
        timer = 10 * 60;
        impostor = true;
        if (impostor) {
            this.soundGO(3);
            System.out.println("soy impostor :D");
        } else {
            this.soundGO(9);
        }
        container.getNetwork().sendPacket(new PlayerLoginPacket(gameName));
    }

    @Override
    public void update(GameContainer container) {
        Input input = container.getInput();
        boolean isWalking = input.isKey(KeyEvent.VK_W) || input.isKey(KeyEvent.VK_S) || input.isKey(KeyEvent.VK_A)
                || input.isKey(KeyEvent.VK_D);
        boolean canWalking = false;

        if (input.isKeyDown(KeyEvent.VK_H)) {
            this.soundGO(3);
        }

        if (isDying) {
            this.sprite.setSprite(DuckImages.spriteDying);
            this.sprite.setSpeed(5);
            this.soundGO(6);
            if (this.sprite.getIndex() >= 10) {
                isDying = false;
                this.sprite.setSpeed(0);
            }
        }else{
            if (!isDead) {
                if (isKilling) {
                    if (directionX == 1) {
                        this.sprite.setSprite(DuckImages.spriteKillingRight);
                        this.sprite.setSpeed(5);
                    } else if (directionX == -1) {
                        this.sprite.setSprite(DuckImages.spriteKillingLeft);
                        this.sprite.setSpeed(5);
                    }
                    if (this.sprite.getIndex() >= 4 + 1) {
                        isKilling = false;
                        this.sprite.setSpeed(0);
                    }
                } else {
                    // LOGICA DE IMPOSTOR
                    if (impostor) {
                        this.sprite2.setSprite(DuckImages.spriteButton);
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

                    if ((isWalking && !misionOpen)) {
                        soundCounter++;
                        if (soundCounter >= soundAcumulatorMax + 9) {
                            sonidoPisada(soundStep);
                        }
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
                            this.sprite.setSprite(DuckImages.movingRight);
                        } else if (directionX == -1) {
                            this.sprite.setSprite(DuckImages.movingLeft);
                        }
                        this.sprite.setSpeed(4);
                        //this.soundGO(6);
                    } else {
                        if (directionX == 1) {
                            this.sprite.setSprite(DuckImages.staticDuckRight);
                        } else if (directionX == -1) {
                            this.sprite.setSprite(DuckImages.staticDuckLeft);
                        }
                        this.sprite.setSpeed(-1);
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
                        } else {
                            mision.setIsCercaPlayer(false);
                        }
                    }
                }

            } else {
                this.sprite.setSprite(DuckImages.spriteDead);
                this.sprite2.setSprite(null);
            }
        }
    }

    @Override
    public void draw(GameContainer container, Graphics2D g2) {
        if (alredyKill == true) {
            int scale = container.getScale().getScale();
            this.sprite2.setIndex(2);
            g2.setColor(Color.white);
            g2.setFont(new Font("Arial", Font.BOLD, 30));
            g2.drawString("   " + timer, 410*scale, 255*scale);
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
                this.soundGO(6);
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

    public void soundGO(int i) {
        sound.setFile(i);
        sound.play();
    }

    public void sonidoPisada(int i) {
        sound.setFile(i);
        sound.play();
        soundStep++;
        if (soundStep > 2) {
            soundStep = 0;
        }
        if (soundStep == 0) {
            soundAcumulatorMax = 7 + 5;
        } else if (soundStep == 1) {//asdsad
            soundAcumulatorMax = 15;
        } else if (soundStep == 2) {
            soundAcumulatorMax = 14;
        }
        soundCounter = 0;
    }

    public void setMisionOpen(boolean misionOpen) {
        this.misionOpen = misionOpen;
    }

    public int getNodeLevel() {
        return 150;
    }
}
