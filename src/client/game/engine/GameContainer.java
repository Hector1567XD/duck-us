package client.game.engine;

import common.game.engine.Container;
import common.game.engine.Scale;
import client.game.engine.core.Input;
import client.game.engine.core.Window;
import java.awt.Graphics2D;

public class GameContainer extends Container {
    public final String gameName = "Duck US";

    // INSTANCIAS
    private Window window;
    private Input input;

    public GameContainer(Scale scale, GameNetwork network, GameController controller) {
        super(scale, network, controller);
        this.window = new Window(this);
        this.input = new Input(this, this.window);
    }

    @Override
    public void start() {
        super.start();
        window.open();
    }

    @Override
    public void update() {
        super.update();
        input.update();
    }

    @Override
    public void render() {
        window.getPanel().repaint();
    }

    public void draw(Graphics2D g2) {
        GameController controller = getController();
        controller.draw(this, g2);
    }

    // Getters

    public Window getWindow() {
        return window;
    }

    public Input getInput() {
        return input;
    }

    @Override
    public GameController getController() {
        return (GameController) super.getController(); 
    }

    @Override
    public GameNetwork getNetwork() {
        return (GameNetwork) super.getNetwork(); 
    }
}
