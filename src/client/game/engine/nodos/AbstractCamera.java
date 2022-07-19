package client.game.engine.nodos;

import client.game.engine.GameNode;

public abstract class AbstractCamera extends GameNode {
    protected int screenX;
    protected int screenY;

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }
    
    public int getDeltaCameraX() {
        return this.getScreenX() - this.getX();
    }

    public int getDeltaCameraY() {
        return this.getScreenY() - this.getY();
    }
}
