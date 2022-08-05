package client.game.nodes;

import client.game.engine.GameContainer;
import client.game.engine.GameNode;
import client.game.nodes.tiles.MapCollitionManagerParent;
import client.game.nodes.tiles.MapDrawerParentInterface;
import common.utils.NodeCollectionUtils;

import java.awt.Graphics2D;

public abstract class MapNodeParent extends GameNode {
    MapCollitionManagerParent mapCollitions;
    MapDrawerParentInterface mapDrawer;

    @Override
    public void created(GameContainer container) {
        this.x = this.x + this.getOffsetCols() * container.getScale().getOriginalTileSize();
        this.y = this.y + this.getOffsetRows() * container.getScale().getOriginalTileSize();
    }

    @Override
    public void update(GameContainer container) {
        //NodeCollectionUtils.print(container.getController().getNodes());
    }

    @Override
    public void draw(GameContainer container, Graphics2D g2) {
        if (mapDrawer != null) {
            mapDrawer.draw(g2, drawX, drawY);
        }
        if (mapCollitions != null) {
            mapCollitions.draw(container, g2);
        }
    }

    public MapCollitionManagerParent getCollideMap() {
        return mapCollitions;
    }

    public String getNodeTag() {
        return "MapNode";
    }

    public abstract int getOffsetCols();

    public abstract int getOffsetRows();
}
