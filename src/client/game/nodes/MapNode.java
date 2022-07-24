package client.game.nodes;

import client.game.engine.GameContainer;
import client.game.engine.GameNode;
import client.game.tiles.MapTilesManager;

import java.awt.Graphics2D;

public class MapNode extends GameNode {
    MapTilesManager mapa;
    
    public MapNode(GameContainer container){
       this.mapa = new MapTilesManager(this, container);
    }
    
    @Override
    public void created(GameContainer container) {

    }

    @Override
    public void update(GameContainer container) {
    }

    @Override
    public void draw(GameContainer container, Graphics2D g2) {
        mapa.draw(g2);
    }

    public MapTilesManager getMapa() {
        return mapa;
    }

    public String getTagName() {
        return "MapNode";    
    }
}
