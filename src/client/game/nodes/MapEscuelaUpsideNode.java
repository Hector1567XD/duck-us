package client.game.nodes;

import client.game.engine.GameContainer;
import client.game.nodes.tiles.LobbyMapDrawer;
import client.game.nodes.tiles.Map1MapDrawer;
import client.game.nodes.tiles.Map1CollitionManager;
import client.game.nodes.tiles.Map1MapUpsideDrawer;

public class MapEscuelaUpsideNode extends MapNodeParent {
    public MapEscuelaUpsideNode(GameContainer container){
       this.mapCollitions = null;
       this.mapDrawer = new Map1MapUpsideDrawer(container.getScale().getTileSize());
    }

    public int getOffsetCols() {
        return 0;
    }

    public int getOffsetRows() {
        return 0;
    }
    
    public int getNodeLevel() {
        return 400;
    }
}
