package client.game.engine.nodos;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import client.game.engine.GameContainer;
import client.game.engine.GameNode;

public class SpriteNode extends GameNode {
    int framesCounter = 0;
    private BufferedImage[] sprite = null;
    private int index = 0;
    private int speed = 0;
    private SpriteableNode spriteableParent;
    private boolean absolutePosition = false;
    private int width;
    private int height;

    public SpriteNode(SpriteableNode spriteableNode) {
        this.spriteableParent = spriteableNode;
    }

    public SpriteNode(SpriteableNode spriteableNode, int x, int y, int width, int height) {
        this.spriteableParent = spriteableNode;
        this.absolutePosition = true;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    private SpriteableNode getSpriteableParent() {
        return spriteableParent;
    }

    public void setSprite(BufferedImage[] sprite) {
        this.sprite = sprite;
    }

    public BufferedImage[] getSprite() {
        return sprite;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void update(GameContainer gc) {
        if (sprite == null) {
            return;
        }

        if (speed >= 0) {
            if (framesCounter >= speed) {
                framesCounter = 0;
                index++;
            }else{
                framesCounter++;
            }
        }else{
            framesCounter = 0;
        }

        if (index >= sprite.length) {
            index = 0;
        }
    }

    @Override
    public void draw(GameContainer gc, Graphics2D g2) {
        if (sprite == null) return;

        BufferedImage currentSprite = sprite[index];
        SpriteableNode parent = getSpriteableParent();
        int escala = gc.getScale().getScale();

        if (!absolutePosition) {
            // Dibuja las cordenadas del mapa del papa
            g2.drawImage(
                currentSprite,
                parent.getDrawX() - parent.getOffsetX() * escala,
                parent.getDrawY() - parent.getOffsetY() * escala,
                parent.getWidth() * escala,
                parent.getHeight() * escala,
                null
            );
            
        }else{
            // Dibuja en coordenadas absolutas (No en el mapa)
            g2.drawImage(
                currentSprite,
                x * escala,
                y * escala,
                width * escala,
                height * escala,
                null
            );
        }
    }

    @Override
    public void created(GameContainer container) {
        // TODO Auto-generated method stub
    }
}
