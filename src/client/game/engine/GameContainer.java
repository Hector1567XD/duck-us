package client.game.engine;

import common.game.engine.Container;
import client.game.engine.core.Input;
import client.game.engine.core.Window;
import java.awt.Graphics2D;

public class GameContainer extends Container {
    public final String gameName = "Duck US";

    // INSTANCIAS
    private Window window;
    private Input input;

    // Tamaño del mapa
    private int maxMapCol = 16;
    private int maxMapRow = 12;

    public GameContainer(int scaleSize, GameNetwork network, GameController controller) {
        super(scaleSize, network, controller);
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

    public int getMaxMapCol() {
        return maxMapCol;
    }

    public int getMaxMapRow() {
        return maxMapRow;
    }
}
