package client.game.nodes;

import client.game.engine.GameContainer;
import client.game.nodes.tiles.LobbyMapDrawer;
import client.game.nodes.tiles.Map1MapDrawer;
import client.game.nodes.tiles.Map1CollitionManager;

public class MapLobbyNode extends MapNodeParent {
    public MapLobbyNode(GameContainer container){
       this.mapCollitions = new Map1CollitionManager(getOffsetCols(), getOffsetRows());
       this.mapDrawer = new LobbyMapDrawer(container.getScale().getTileSize());
    }

    public int getOffsetCols() {
        return 0;
    }

    public int getOffsetRows() {
        return 100;
    }
}
