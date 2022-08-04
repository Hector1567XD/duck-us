package client.game.nodes.tiles;

import client.game.nodes.MapEscuelaNode;

/**
 *
 * @author david_000
 */
public class Map1CollitionManager extends MapCollitionManagerParent {
    public Map1CollitionManager(int offsetCols, int offsetRows) {
        super(offsetCols, offsetRows);
    }

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
