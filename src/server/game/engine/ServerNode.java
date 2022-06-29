package server.game.engine;

import common.engine.Container;
import common.engine.node.*;

public abstract class ServerNode extends Node {

    @Override
    public void created(Container container) {
        created((ServerContainer) container);
    }

    @Override
    public void update(Container container) {
        update((ServerContainer) container);
    }

    public abstract void created(ServerContainer container);
    public abstract void update(ServerContainer container);
}
