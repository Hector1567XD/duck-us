package common.engine;

import common.engine.core.Runner;

public abstract class Container {
    private Runner runner;
    private Scale scale;
    private Network network;
    private Controller controller;

    public Container(Scale scale, Network network, Controller controller) {
        this.runner = new Runner(this);
        this.network = network;
        this.controller = controller;
        this.scale = scale;
    }

    public void start(String ipAddress) {
        runner.start();
        network.start();
    }

    public void update() {
        network.update(this);
        controller.update(this);
    }

    public void render() {
        // Do nothing :)
    }

    public Runner getRunner() {
        return runner;
    }

    public Network getNetwork() {
        return network;
    }

    public Controller getController() {
        return controller;
    }
}
