package client.game.nodes;

import common.networking.packets.PlayerLoginPacket;
import client.game.engine.GameContainer;
import client.game.engine.GameNode;
import client.game.engine.core.Input;
import client.game.engine.nodos.NodeCenterable;
import client.game.engine.nodos.NodeColladable;
import client.game.tiles.MapTilesManager;
import client.utils.game.collitions.CenterBorders;
import client.utils.game.collitions.CollideBox;
import client.utils.game.collitions.CollitionsUtils;
import common.CommonConstants;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player extends GameNode implements NodeCenterable, NodeColladable {
    private int velocity = 4;
 
    @Override
    public void created(GameContainer container) {
        this.x = 235;
        this.y = 200;
        container.getNetwork().sendPacket(new PlayerLoginPacket("Feredev"));
    }

    @Override
    public void update(GameContainer container) {
        Input input = container.getInput();
        boolean isWalking = input.isKey(KeyEvent.VK_W) || input.isKey(KeyEvent.VK_S) || input.isKey(KeyEvent.VK_A) || input.isKey(KeyEvent.VK_D);

        if (isWalking) {
            if (input.isKey(KeyEvent.VK_W)) {
                if (canMove(container, this.x, this.y - velocity)) {
                    y -= velocity;
                } else {
                    while (canMove(container, this.x, this.y - 1)) {
                        y -= 1;
                    }
                }
            }
            if (input.isKey(KeyEvent.VK_S)) {
                if (canMove(container, this.x, this.y + velocity)) {
                    y += velocity;
                } else {
                    while (canMove(container, this.x, this.y + 1)) {
                        y += 1;
                    }
                }
            }
            if (input.isKey(KeyEvent.VK_A)) {
                if (canMove(container, this.x - velocity, this.y)) {
                    x -= velocity;
                } else {
                    while (canMove(container, this.x - 1, this.y)) {
                        x -= 1;
                    }
                }
            }
            if (input.isKey(KeyEvent.VK_D)) {
                if (canMove(container, this.x + velocity, this.y)) {
                    x += velocity;
                } else {
                    while (canMove(container, this.x + 1, this.y)) {
                        x += 1;
                    }
                }
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
        
        MapNode mapNode = container.getController().getNodes().findByName("MapNode");
        if (mapNode == null) {
            return;
        }
        MapTilesManager mapTilesManager = mapNode.getMapa();
        int[][] arregloTilesets = mapTilesManager.getMapTileNum();

        for (int posX = 0; posX < container.getMaxMapCol(); posX++) {
            for (int posY = 0; posY < container.getMaxMapRow(); posY++) {
                int tile = arregloTilesets[posX][posY];
                if (tile == 1) {
                    int offsetBlock = CommonConstants.TILE_SIZE / 2;
                    int x1 = posX * CommonConstants.TILE_SIZE;
                    int y1 = posY * CommonConstants.TILE_SIZE;
                    int x2 = posX * CommonConstants.TILE_SIZE + offsetBlock*2;
                    int y2 = posY * CommonConstants.TILE_SIZE + offsetBlock*2;
                    g2.setColor(Color.YELLOW);
                    int deltaCamX = container.getController().getCamera().getDeltaCameraX();
                    int deltaCamY = container.getController().getCamera().getDeltaCameraY();
                    g2.drawLine(drawX, drawY, x1*scale + deltaCamX , y1*scale + deltaCamY);
                    g2.drawLine(drawX, drawY, x2*scale + deltaCamX, y2*scale + deltaCamY);
                }
            }
        }
    }

    public boolean isHipoteticPositionColliding(int x, int y, NodeColladable otherNode) {
        CollideBox selfBox = this.getPositionCollideBox(x, y);
        CollideBox otherBox = otherNode.getCollideBox();

        return selfBox.isCollidingWith(otherBox);
    }

    // to do (esto va en update)
    public boolean canMove(GameContainer container, int x, int y) {
        // COLISION CON BLOQUES
        ArrayList<Bloque> bloquesitos = container.getController().getNodes().getListByTag("Bloque");

        for (Bloque block : bloquesitos) {
            if (isHipoteticPositionColliding(x, y, block)) {
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
                    boolean isColliding 
                            = this.isCollidingWithTile(
                                x,
                                y,
                                posX * CommonConstants.TILE_SIZE + offsetBlock,
                                posY * CommonConstants.TILE_SIZE + offsetBlock,
                                offsetBlock
                            );

                    if (isColliding) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public boolean isCollidingWithTile(int x, int y, int oX, int oY, int d) {
        CollideBox selfBox = this.getPositionCollideBox(x,y);
        CollideBox positionBox = CollitionsUtils.createCenteredBox(oX, oY, new CenterBorders(d, d, d, d));
        return selfBox.isCollidingWith(positionBox);
    }

    /*public boolean isColliding(NodeColladable otherNode) {
        return this.isHipoteticPositionColliding(this.x, this.y, otherNode);
    }*/

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

    public CenterBorders getCenterBorders() {
        return new CenterBorders(16, 16, 16, 16);
    }

    @Override
    public CollideBox getCollideBox() {
        return this.getPositionCollideBox(this.x, this.y);
    }

    public CollideBox getPositionCollideBox(int posX, int posY) {
        CenterBorders centerBorders = this.getCenterBorders();
        return CollitionsUtils.createCenteredBox(posX, posY, centerBorders);
    }
}
