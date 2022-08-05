/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

/**
 *
 * @author david_000
 */
public class AbrirMision3 extends GameNode implements SpriteableNode, NodeCenterable, NodeColladable, NodeOpenable{
    private boolean misionAbierta = false;
    Mision3 misionActual;
    private boolean ganaste = false;
    SpriteNode sprite;
    BufferedImage[] spriteMission;
    BufferedImage[] spriteMissionLigth;
    boolean isCercaPlayer = false;

    
    public AbrirMision3(int x, int y, Mision3 mision) {
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
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/missions/mission3.png")),};
            BufferedImage[] spriteMissionLigth = {
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/missions/mission3-light.png")),};

            this.spriteMission = spriteMission;
            this.spriteMissionLigth = spriteMissionLigth;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean getMisionAbierta() {
        return misionAbierta;
    }

    @Override
    public void setMisionAbierta(boolean misionAbierta) {
        this.misionAbierta = misionAbierta;
    }

    @Override
    public boolean isGanaste() {
        return ganaste;
    }

    @Override
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

        if (getMisionAbierta() == true) {
            this.misionActual.setAbrir(true);
            this.misionActual.draw(container, g2);
        }
        if (getMisionAbierta() == false) {
            this.misionActual.setAbrir(false);
        }
    }

    public int getOffsetX() {
        return 64;
    }

    public int getOffsetY() {
        return 64;
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
        return 64;
    }

    @Override
    public int getHeight() {
        return 88;
    }

    @Override
    public void setIsCercaPlayer(boolean isCercaPlayer) {
        this.isCercaPlayer = isCercaPlayer;
    }
}
