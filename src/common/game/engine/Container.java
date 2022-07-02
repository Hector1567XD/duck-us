package common.game.engine;

import common.CommonConstants;
import common.game.engine.core.Runner;

public abstract class Container {
    private Runner runner;
    private Scale scale;
    private Network network;
    private Controller controller;

    public Container(int scaleSize, Network network, Controller controller) {
        this.runner = new Runner(this);
        this.network = network;
        this.controller = controller;
        this.scale = new Scale(CommonConstants.TILE_SIZE, scaleSize);
    }

    public void start() {
        runner.start();
    }

    public void update() {
        network.update(this);
        controller.update(this);
    }

    public abstract void render();

    public Runner getRunner() {
        return runner;
    }

    public Network getNetwork() {
        return network;
    }

    public Controller getController() {
        return controller;
    }

    public Scale getScale() {
        return scale;
    }
}
