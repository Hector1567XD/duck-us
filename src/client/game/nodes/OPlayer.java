package client.game.nodes;

import client.game.engine.GameContainer;
import client.game.engine.GameNode;
import client.game.engine.nodos.NodeCenterable;
import client.game.engine.nodos.NodeKilleable;
import client.game.engine.nodos.SpriteNode;
import client.game.engine.nodos.SpriteableNode;
import client.game.nodes.classes.DuckImages;
import client.game.nodes.classes.Sound;
import client.utils.ImageUtils;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class OPlayer extends GameNode implements SpriteableNode, NodeCenterable, NodeKilleable {
    private int velocity = 4;
    private int playerId;
    private String name;
    private boolean isDead;
    private boolean soundDead;
    private SpriteNode sprite;
    int oldX = 0;
    int oldY = 0;
    Sound sound = new Sound();
    int notMoving = 0;
    int xDirection = 1;
    private boolean isKilling = false;
    private boolean isDying = false;
    
    public OPlayer(int playerId, String name) {
        this.x = 4032;
        this.y = 1440;
        this.playerId = playerId;
        this.name = name;
        // Sub nodo de sprites
        this.sprite = new SpriteNode(this);
        this.addNode(this.sprite);
    }

    @Override
    public void created(GameContainer container) {
        soundDead = false;
        this.sprite.setSprite(DuckImages.staticDuckRight);
    }

    @Override
    public void update(GameContainer container) {
        if (isDying) {
            this.sprite.setSprite(DuckImages.spriteDying);
            this.sprite.setSpeed(5);
            sound.setFile(6);
            sound.play();
            if (this.sprite.getIndex() >= 10) {
                isDying = false;
                this.sprite.setSpeed(0);
            }
        }else{
            if (oldX == x || oldY == y) {
                notMoving++;
            }

            if (oldX != x) {
                notMoving = 0;
                if (x < oldX) {
                    xDirection = -1;
                }else{
                    xDirection = 1;
                }
            } else if (oldY != y) {
                notMoving = 0;
            }

            if (notMoving >= 5) {
                // QUEDARSE QUIETO
                if (xDirection == 1) {
                    this.sprite.setSprite(DuckImages.staticDuckRight);
                }else{
                    this.sprite.setSprite(DuckImages.staticDuckLeft);
                }
            }else{
                // MOVERSE
                if (xDirection == 1) {
                    System.out.println("IS MOVING");
                    this.sprite.setSprite(DuckImages.movingRight);
                }else{
                    this.sprite.setSprite(DuckImages.movingLeft);
                }
                this.sprite.setSpeed(4);
            }

            if (isDead == true) {
                this.sprite.setSprite(DuckImages.spriteDead);
            }
        }

        oldX = x;
        oldY = y;
        
        if (isDead == true) {
            if (soundDead ==false){
               sound.setFile(10);
               sound.play();
               soundDead = true;
            }
        }
    }

    @Override
    public void draw(GameContainer container, Graphics2D g2) {
        g2.setColor(Color.GRAY);
        int scale = container.getScale().getScale();
        int tileSize = container.getScale().getOriginalTileSize();
        int offSetX = this.getOffsetX() * scale;
        int offSetY = this.getOffsetY() * scale;
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.CENTER_BASELINE, 11 * scale));        
        g2.drawString(this.name + "(" + this.playerId + ")", (drawX - 16* scale), (drawY + 36* scale));
    }

    public String getNodeTag() {
        return "Oplayer";
    }

    public int getPlayerId() {
        return playerId;
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

    public boolean isIsDead() {
        return isDead;
    }

    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
        if (isDead == true) {
            isDying = true;
        }
    }

    public int getNodeLevel() {
        return 100;
    }
}
