package server.engine;

import common.engine.Container;
import common.engine.Scale;

public class ServerContainer extends Container {

    public ServerContainer(Scale scale, ServerNetwork network, ServerController controller) {
        super(scale, network, controller);
    }

    @Override
    public void render() {
        // Do nothing, im the server xP
    }
}
