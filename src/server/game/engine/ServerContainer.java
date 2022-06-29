package server.game.engine;

import common.game.engine.Container;
import common.game.engine.Scale;

public class ServerContainer extends Container {

    public ServerContainer(Scale scale, ServerNetwork network, ServerController controller) {
        super(scale, network, controller);
    }

    @Override
    public void render() {
        // Do nothing, im the server xP
    }

    @Override
    public ServerController getController() {
        return (ServerController) super.getController(); 
    }

    @Override
    public ServerNetwork getNetwork() {
        return (ServerNetwork) super.getNetwork(); 
    }
}
