package common.game.engine;

public class Scale {
    private int originalTileSize = 32;
    private int scale = 2;
    private int tileSize;

    public Scale(int originalTileSize, int scale) {
        this.originalTileSize = originalTileSize;
        this.scale = scale;
        this.tileSize = originalTileSize*scale;
    }

    public Scale() {
        this.tileSize = originalTileSize*scale;
    }

    public int getOriginalTileSize() {
        return originalTileSize;
    }

    public int getScale() {
        return scale;
    }

    public int getTileSize() {
        return tileSize;
    }
}
