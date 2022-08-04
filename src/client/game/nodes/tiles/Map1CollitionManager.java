package client.game.nodes.tiles;

import client.game.nodes.MapNode;

/**
 *
 * @author david_000
 */
public class Map1CollitionManager extends MapCollitionManagerParent {
    @Override
    public int getWorldCols() {
        return 190;
    }

    @Override
    public int getWorldRows() {
        return 90;
    }

    public String getCollitionMapFileName() {
        return "/client/resources/game/maps/mapa1/map1";
    }
}
