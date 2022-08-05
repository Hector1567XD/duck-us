package client.game.engine.nodos;

import client.Constants;
import client.game.engine.GameContainer;
import client.game.engine.GameNode;
import client.game.nodes.Bloque;
import client.game.nodes.MapEscuelaNode;
import client.game.nodes.MapNodeParent;
import client.game.nodes.tiles.Map1CollitionManager;
import client.game.nodes.tiles.MapCollitionManagerParent;
import client.game.utils.CollideBoxCamDrawer;
import client.utils.game.collitions.CenterBorders;
import client.utils.game.collitions.CollideBox;
import client.utils.game.collitions.CollitionsUtils;
import common.CommonConstants;
import common.utils.NodeCollectionUtils;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class CollideNode extends GameNode {
    private NodeColladable parent;
    private boolean showCollitionsShape;

    public CollideNode(NodeColladable parent) {
        this.parent = parent;
        this.showCollitionsShape = Constants.SHOW_COLLISION_SHAPE && Constants.SHOW_COLLISION_NODE_COLLISION_SHAPE;
    }

    public boolean isShowCollitionsShape() {
        return showCollitionsShape;
    }

    public void setShowCollitionsShape(boolean showCollitionsShape) {
        this.showCollitionsShape = showCollitionsShape;
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
        if (showCollitionsShape) {
            //CenterBorders centerBorders = this.parent.getCenterBorders();
            g2.setColor(Color.ORANGE);
            CollideBox collideBox = this.getPositionCollideBox(this.x, this.y);
            CollideBoxCamDrawer.drawCollideBox(container, g2, collideBox);
        }
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
        ArrayList<MapNodeParent> mapNodes = container.getController().getNodes().getListByTag("MapNode");
        boolean canMoveInMapNode = true;
        for (MapNodeParent mapNode: mapNodes) {
            canMoveInMapNode = this.canMoveInMapNode(mapNode, x, y);
            if (canMoveInMapNode == false) return false;
        }

        return canMoveInMapNode;
    }

    public boolean canMoveInMapNode(MapNodeParent mapNode, int x, int y) {
        if (mapNode == null) {
            return true;
        }

        MapCollitionManagerParent mapTilesManager = mapNode.getCollideMap();
        if (mapTilesManager == null) {
            return true;
        }
        int[][] arregloTilesets = mapTilesManager.getMapTileNum();

        for (int posX = 0; posX < mapTilesManager.getWorldCols() + mapTilesManager.getOffsetCols(); posX++) {
            for (int posY = 0; posY < mapTilesManager.getWorldRows() + mapTilesManager.getOffsetRows(); posY++) {
                int tile = arregloTilesets[posX][posY];
                if (tile == 1) {
                    int offsetBlock = CommonConstants.TILE_SIZE / 2;
                    CollideBox tileCollideBox = mapTilesManager.getCollideBoxByTile(posX, posY, offsetBlock);
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
