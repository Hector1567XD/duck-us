package server.engine;

import common.engine.Container;
import common.engine.node.*;

public abstract class ServerNode extends Node {

    @Override
    public void created(Container container) {
        ServerContainer serverContainer = (ServerContainer) container;
        created(serverContainer);
    }

    @Override
    public void update(Container container) {
        ServerContainer serverContainer = (ServerContainer) container;
        update(serverContainer);
    }

    public abstract void created(ServerContainer container);
    public abstract void update(ServerContainer container);
}
