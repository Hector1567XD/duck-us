package client.game.nodes;

import common.networking.packets.PlayerLoginPacket;
import client.game.engine.GameContainer;
import client.game.engine.GameNode;
import client.game.engine.core.Input;
import client.game.engine.nodos.NodeCenterable;
import client.game.engine.nodos.NodeColladable;
import client.game.tiles.MapTilesManager;
import common.CommonConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player extends GameNode implements NodeCenterable, NodeColladable {
    private int velocity = 4;
    private boolean misionOpen = false;

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
                || input.isKey(KeyEvent.VK_D) || input.isKey(KeyEvent.VK_X) || input.isKey(KeyEvent.VK_P);

        if ((isWalking) && (!misionOpen)) {
            if (input.isKey(KeyEvent.VK_W)) {
                if (cantMove(container, this.x, this.y - velocity)) {
                    y -= velocity;
                } else {
                    while (cantMove(container, this.x, this.y - 1)) {
                        y -= 1;
                    }
                }
            }
            if (input.isKey(KeyEvent.VK_S)) {
                if (cantMove(container, this.x, this.y + velocity)) {
                    y += velocity;
                } else {
                    while (cantMove(container, this.x, this.y + 1)) {
                        y += 1;
                    }
                }
            }
            if (input.isKey(KeyEvent.VK_A)) {
                if (cantMove(container, this.x - velocity, this.y)) {
                    x -= velocity;
                } else {
                    while (cantMove(container, this.x - 1, this.y)) {
                        x -= 1;
                    }
                }
            }
            if (input.isKey(KeyEvent.VK_D)) {
                if (cantMove(container, this.x + velocity, this.y)) {
                    x += velocity;
                }
            }
            
        }
        
        if (input.isKey(KeyEvent.VK_P)) {
                ArrayList<AbrirMision1> missions = container.getController().getNodes().getListByTag("mission");

                for (AbrirMision1 i : missions) {
                    if (isPositionCollaiding(i, x, y)) {
                        //System.out.println("si :)");
                        
                        misionOpen = true;
                        i.setMisionAbierta(true);
                        
                    }    
                }
            }
            if (input.isKey(KeyEvent.VK_X)) {
                ArrayList<AbrirMision1> missions = container.getController().getNodes().getListByTag("mission");
                for (AbrirMision1 i : missions) {
                    if (isPositionCollaiding(i, x, y)) {
                        //System.out.println("no :)");  
                        misionOpen = false;
                        i.setMisionAbierta(false);
                    }   
                }
            }
                ArrayList<AbrirMision1> missions = container.getController().getNodes().getListByTag("mission");
                for (AbrirMision1 i : missions) {  
                    if (i.getMisionAbierta()== false) {
                        misionOpen = false;
                    }
                }    
    }

    @Override
    public void draw(GameContainer container, Graphics2D g2) {
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
         ArrayList<AbrirMision1> missions = container.getController().getNodes().getListByTag("mission");
           for (AbrirMision1 i : missions) {
               if (isPositionCollaiding(i, x, y)) {
                   g2.setFont(new Font( "Arial", Font.BOLD, 20*scale ));
                   g2.drawString("PULSE P PARA ABRIR LA MISION", 50*scale, 320*scale);
               }
           }    
    }

    public boolean isPositionCollaiding(NodeColladable otherNode, int x, int y) {
        if ((x + this.getRightCenter() >= otherNode.getX() - otherNode.getLeftCenter())
                && (x - this.getRightCenter() <= otherNode.getX() + otherNode.getLeftCenter())
                && (y + this.getBottomCenter() >= otherNode.getY() - otherNode.getTopCenter())
                && (y - this.getBottomCenter() <= otherNode.getY() + otherNode.getBottomCenter())) {
            return true;
        }
        return false;
    }

    public boolean cantMove(GameContainer container, int x, int y) {
        ArrayList<Bloque> bloquesitos = container.getController().getNodes().getListByTag("Bloque");
        
        // COLISION CON BLOQUES
        for (Bloque i : bloquesitos) {
            if (isPositionCollaiding(i, x, y)) {
                return false;
            }
        }
        
        // COLISION CON TILESETS
        MapNode mapNode = container.getController().getNodes().findByName("MapNode");
        if (mapNode == null) {
            return true;
        }

        MapTilesManager mapTilesManager = mapNode.getMapa();
        int[][] arregloTilesets = mapTilesManager.getMapTileNum();

        for (int posX = 0; posX < container.getMaxMapCol(); posX++) {
            for (int posY = 0; posY < container.getMaxMapRow(); posY++) {
                int tile = arregloTilesets[posX][posY];
                if (tile == 1) {
                    int offsetBlock = CommonConstants.TILE_SIZE / 2;
                    boolean isColliding = this.isPositionCollaidingManual(x, y,
                            posX * CommonConstants.TILE_SIZE + offsetBlock,
                            posY * CommonConstants.TILE_SIZE + offsetBlock, offsetBlock);
                    if (isColliding) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isPositionCollaidingManual(int x, int y, int oX, int oY, int distanceToCenter) {
        if ((x + this.getRightCenter() >= oX - distanceToCenter)
                && (x - this.getRightCenter() <= oX + distanceToCenter)
                && (y + this.getBottomCenter() >= oY - distanceToCenter)
                && (y - this.getBottomCenter() <= oY + distanceToCenter)) {
            return true;
        }
        return false;
    }

    public boolean isCollaiding(NodeColladable otherNode) {
        return isPositionCollaiding(otherNode, x, y);
    }

    @Override
    public String getNodeTag() {
        return "Player";
    }

    public int getOffsetX() {
        return 16;
    }

    public int getOffsetY() {
        return 16;
    }

    public int getTopCenter() {
        return 16;
    }

    public int getLeftCenter() {
        return 16;
    }

    public int getRightCenter() {
        return 16;
    }

    public int getBottomCenter() {
        return 16;
    }
}
