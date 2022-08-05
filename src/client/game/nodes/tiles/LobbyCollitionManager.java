package client.game.nodes.tiles;

import client.game.nodes.MapEscuelaNode;

/**
 *
 * @author david_000
 */
public class LobbyCollitionManager extends MapCollitionManagerParent {
    public LobbyCollitionManager(int offsetCols, int offsetRows) {
        super(offsetCols, offsetRows + 3);
    }

    @Override
    public int getWorldCols() {
        return 34;
    }

    @Override
    public int getWorldRows() {
        return 35;
    }

    public String getCollitionMapFileName() {
        return "/client/resources/game/maps/lobby/lobby";
    }
}
