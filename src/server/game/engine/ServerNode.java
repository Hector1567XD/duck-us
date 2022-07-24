package server.game.engine;

import common.game.engine.node.Node;
import common.game.engine.Container;

public abstract class ServerNode extends Node {

    @Override
    public void created(Container container) {
        created((ServerContainer) container);
    }

    @Override
    public void update(Container container) {
        update((ServerContainer) container);
    }

    @Override
    public void removed(Container container) {
        removed((ServerContainer) container);
    }

    public abstract void created(ServerContainer container);
    public abstract void update(ServerContainer container);
    public void removed(ServerContainer container) {
        // Optional
    }

}
