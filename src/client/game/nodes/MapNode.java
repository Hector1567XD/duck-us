package client.game.nodes;

import client.game.engine.GameContainer;
import client.game.engine.GameNode;
import client.game.nodes.tiles.Map1MapDrawer;
import client.game.nodes.tiles.Map1CollitionManager;
import client.game.nodes.tiles.MapCollitionManagerParent;
import client.game.nodes.tiles.MapDrawerParentInterface;

import java.awt.Graphics2D;

public class MapNode extends GameNode {
    MapCollitionManagerParent mapCollitions;
    MapDrawerParentInterface mapDrawer;

    public MapNode(GameContainer container){
       this.mapCollitions = new Map1CollitionManager();
       this.mapDrawer = new Map1MapDrawer(container.getScale().getTileSize());
    }

    @Override
    public void created(GameContainer container) {

    }

    @Override
    public void update(GameContainer container) {
    }

    @Override
    public void draw(GameContainer container, Graphics2D g2) {
        mapDrawer.draw(g2, drawX, drawY);
        mapCollitions.draw(container, g2);
    }

    public MapCollitionManagerParent getCollideMap() {
        return mapCollitions;
    }

    public String getTagName() {
        return "MapNode";
    }
}
