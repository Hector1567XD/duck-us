package server.game.engine;
import common.game.engine.Container;
public class ServerContainer extends Container {

    public ServerContainer(int scaleSize, ServerNetwork network, ServerController controller) {
        super(scaleSize, network, controller);
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
