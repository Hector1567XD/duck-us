package client.game.nodes.tiles;

import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Map1MapUpsideDrawer implements MapDrawerParentInterface {
    private int tileSize;
    private BufferedImage[] image;
    private int mapXSize = 0;
    private int mapYSize = 0;

    public int getTileSize() {
        return tileSize;
    }

    @Override
    public int getWorldCols() {
        return 190;
    }

    @Override
    public int getWorldRows() {
        return 90;
    }

    public Map1MapUpsideDrawer(int tileSize) {
        this.tileSize = tileSize;
        loadMapImageLayers();
    }

    private void loadMapImageLayers() {
        try {
            this.image = new BufferedImage[10];
            //this.image[0] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/maps/mapa1/Capa 0.png"));
            this.image[0] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/maps/mapa1/Capa sobre el jugador.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2, int x, int y) {
        for (int i = 0; i <= 1; i++) {
            if (this.image[i] != null) {
                g2.drawImage(this.image[i], x, y, getTileSize()*getWorldCols(), getTileSize()*getWorldRows(), null);
            }
        }
    }
}
