package client.game.nodes;

import client.game.engine.GameContainer;
import client.game.engine.GameNode;
import client.game.engine.nodos.NodeCenterable;
import client.game.engine.nodos.NodeColladable;
import client.game.engine.nodos.SpriteNode;
import client.game.engine.nodos.SpriteableNode;
import client.utils.game.collitions.CenterBorders;
import client.utils.game.collitions.CollideBox;
import client.utils.game.collitions.CollitionsUtils;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class AbrirMision2 extends GameNode implements SpriteableNode, NodeCenterable, NodeColladable, NodeOpenable {
    private boolean misionAbierta = false;
    Mision2 misionActual;
    SpriteNode sprite;
    BufferedImage[] spriteMission;
    BufferedImage[] spriteMissionLigth;
    boolean isCercaPlayer = false;
    
    public void setIsCercaPlayer(boolean isCercaPlayer) {
        this.isCercaPlayer = isCercaPlayer;
    }
    
    private boolean ganaste = false;

    public AbrirMision2(int x, int y, Mision2 mision) {
        this.x = x;
        this.y = y;
        this.misionActual = mision;
        //this.addNode(misionActual);
        this.sprite = new SpriteNode(this);
        this.misionActual.setParentMision(this);
        this.addNode(sprite);
        this.initPlayerImages();
    }

    private void initPlayerImages() {
        try {
            BufferedImage[] spriteMission = {
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/missions/mission-1.png")),};
            BufferedImage[] spriteMissionLigth = {
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/missions/mission-1-light.png")),};

            this.spriteMission = spriteMission;
            this.spriteMissionLigth = spriteMissionLigth;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean getMisionAbierta() {
        return misionAbierta;
    }

    public void setMisionAbierta(boolean misionAbierta) {
        this.misionAbierta = misionAbierta;
    }

    public boolean isGanaste() {
        return ganaste;
    }

    public void setGanaste(boolean ganaste) {
        this.ganaste = ganaste;
    }
    
    
    

    @Override
    public void created(GameContainer container) {
    }

    @Override
    public void update(GameContainer container) {
        if (isCercaPlayer) {
            this.sprite.setSprite(spriteMissionLigth);
        }else{
            this.sprite.setSprite(spriteMission);
        }
    }

    @Override
    public void draw(GameContainer container, Graphics2D g2) {

        int scale = container.getScale().getScale();
        int tileSize = container.getScale().getOriginalTileSize();
        g2.setColor(Color.pink);
        int alto = tileSize * scale;
        int ancho = tileSize * scale;
        int offSetX = this.getOffsetX() * scale;
        int offSetY = this.getOffsetY() * scale;

        g2.fillOval((drawX) - offSetX, (drawY) - offSetY, alto, ancho);

        if (getMisionAbierta() == true) {
            this.misionActual.setAbrir(true);
            this.misionActual.draw(container, g2);
        }
        if (getMisionAbierta() == false) {
            this.misionActual.setAbrir(false);
        }
    }

    public int getOffsetX() {
        return 16;
    }

    public int getOffsetY() {
        return 16;
    }

    @Override
    public String getNodeTag() {
        return "mission";
    }

    @Override
    public CenterBorders getCenterBorders() {
        return new CenterBorders(16, 16, 16, 16);
    }
    
    @Override
    public CollideBox getCollideBox() {
        CenterBorders centerBorders = this.getCenterBorders();
        return CollitionsUtils.createCenteredBox(x, y, centerBorders);
    }

    @Override
    public int getWidth() {
        return 32;
    }

    @Override
    public int getHeight() {
        return 32;
    }
}
