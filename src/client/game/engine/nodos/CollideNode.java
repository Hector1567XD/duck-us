package client.game.engine.nodos;

import client.game.engine.GameContainer;
import client.game.engine.GameNode;
import client.game.nodes.Bloque;
import client.game.nodes.MapNode;
import client.game.tiles.MapTilesManager;
import client.utils.game.collitions.CenterBorders;
import client.utils.game.collitions.CollideBox;
import client.utils.game.collitions.CollitionsUtils;
import common.CommonConstants;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class CollideNode extends GameNode {
    private NodeColladable parent;
    private boolean showCollitionsShape;

    public boolean isShowCollitionsShape() {
        return showCollitionsShape;
    }

    public void setShowCollitionsShape(boolean showCollitionsShape) {
        this.showCollitionsShape = showCollitionsShape;
    }

    public CollideNode(NodeColladable parent) {
        this.parent = parent;
    }

    @Override
    public void created(GameContainer container) {}

    @Override
    public void update(GameContainer container) {
        this.x = this.parent.getX();
        this.y = this.parent.getY();
    }

    @Override
    public void draw(GameContainer container, Graphics2D g2) {
        CenterBorders centerBorders = this.parent.getCenterBorders();
        g2.setColor(Color.ORANGE);
        int x1 = drawX - centerBorders.getLeftCenter();
        int x2 = drawX + centerBorders.getRightCenter() - x1;
        int y1 = drawY - centerBorders.getTopCenter();
        int y2 = drawY + centerBorders.getBottomCenter() - y1;
        g2.fillRect(x1, y1, x2, y2);

        // COLISION CON COLLIDE MAPS
        MapNode mapNode = container.getController().getNodes().findByName("MapNode");
        if (mapNode == null) {
            return;
        }

        MapTilesManager mapTilesManager = mapNode.getMapa();
        int[][] arregloTilesets = mapTilesManager.getMapTileNum();
        AbstractCamera camera = container.getController().getCamera();
        if (camera == null) return;

        for (int posX = 0; posX < container.getMaxMapCol(); posX++) {
            for (int posY = 0; posY < container.getMaxMapRow(); posY++) {
                int tile = arregloTilesets[posX][posY];
                if (tile == 1) {
                    int offsetBlock = CommonConstants.TILE_SIZE / 2;
                    CollideBox tileCollideBox = this.getCollideBoxByTile(posX, posY, offsetBlock);

                    int tx1 = tileCollideBox.getX1() + camera.getDeltaCameraX();
                    int ty1 = tileCollideBox.getY1() + camera.getDeltaCameraY();
                    int tx2 = tileCollideBox.getX2() - tileCollideBox.getX1();
                    int ty2 = tileCollideBox.getY2() - tileCollideBox.getY1();
                    g2.setColor(Color.red);
                    g2.fillRect(tx1, ty1, tx2, ty2);
                }
            }
        }

    }

    private void drawCollideBox(Graphics2D g2, CollideBox collideBox) {
        //
    }
    
    public CollideBox getCollideBoxByTile(int posX, int posY, int offsetBlock) {
        int tilePosX = posX * CommonConstants.TILE_SIZE + offsetBlock;
        int tilePosY = posY * CommonConstants.TILE_SIZE + offsetBlock;
        
        CollideBox tileCollideBox = CollitionsUtils.createCenteredBox(
            tilePosX,
            tilePosY,
            new CenterBorders(offsetBlock, offsetBlock, offsetBlock, offsetBlock)
        );
        return tileCollideBox;
    }

    public boolean isColliding(NodeColladable otherNode) {
        return this.isHipoteticPositionColliding(this.x, this.y, otherNode);
    }

    public boolean isHipoteticPositionColliding(int x, int y, NodeColladable otherNode) {
        CollideBox selfBox = this.getPositionCollideBox(x, y);
        CollideBox otherBox = otherNode.getCollideBox();

        return selfBox.isCollidingWith(otherBox);
    }

    public boolean canMove(GameContainer container, int x, int y) {
        // COLISION CON BLOQUES
        ArrayList<Bloque> bloquesitos = container.getController().getNodes().getListByTag("Bloque");
        for (Bloque block : bloquesitos) {
            if (isHipoteticPositionColliding(x, y, block)) {
                return false;
            }
        }

        // COLISION CON COLLIDE MAPS
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
                    CollideBox tileCollideBox = this.getCollideBoxByTile(posX, posY, offsetBlock);
                    if (this.isHipoteticPositionCollidingWithCollideBox(x, y, tileCollideBox)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public boolean isHipoteticPositionCollidingWithCollideBox(int x, int y, CollideBox collideBox) {
        CollideBox selfBox = this.getPositionCollideBox(x,y);
        return selfBox.isCollidingWith(collideBox);
    }

    public CollideBox getPositionCollideBox(int posX, int posY) {
        CenterBorders centerBorders = this.parent.getCenterBorders();
        return CollitionsUtils.createCenteredBox(posX, posY, centerBorders);
    }
}
