package duckus.game.engine;

import common.engine.Container;
import common.engine.Scale;
import duckus.game.engine.core.Input;
import duckus.game.engine.core.Window;
import java.awt.Graphics2D;

public class GameContainer extends Container {
    public final String gameName = "Duck US";

    // INSTANCIAS
    private Window window;
    private Input input;
    private float currentDeltaTime = 1;

    public GameContainer(Scale scale, GameNetwork network, GameController controller) {
        super(scale, network, controller);
        this.window = new Window(this);
        this.input = new Input(this);
    }

    public void start() {
        super.start();
        window.open();
    }

    public void update() {
        super.update();
        input.update();
    }

    public void render() {
        window.getPanel().repaint();
    }

    public void draw(Graphics2D g2) {
        getController().draw(this, g2);
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
