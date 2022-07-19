package client.game.engine.nodos;

import client.game.engine.GameContainer;
import client.game.engine.GameNode;

public abstract class AbstractCamera extends GameNode {
    protected int screenX;
    protected int screenY;
    private GameContainer container;

    public AbstractCamera(GameContainer container, int screenX, int screenY) {
        this.screenX = screenX;
        this.screenY = screenY;
        this.container = container;
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }
    
    public int getDeltaCameraX() {
        return this.getScreenX() - this.getX()*container.getScale().getScale();
    }

    public int getDeltaCameraY() {
        return this.getScreenY() - this.getY()*container.getScale().getScale();
    }
}
