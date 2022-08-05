package client.game.nodes.tiles;

import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Map1MapDrawer implements MapDrawerParentInterface {
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

    public Map1MapDrawer(int tileSize) {
        this.tileSize = tileSize;
        loadMapImageLayers();
    }

    private void loadMapImageLayers() {
        try {
            this.image = new BufferedImage[10];
            //this.image[0] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/maps/mapa1/Capa 0.png"));
            this.image[0] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/maps/mapa1/Capa 1.png"));
            this.image[1] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/maps/mapa1/Capa 2.png"));
            this.image[2] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/maps/mapa1/Capa 3.png"));
            this.image[3] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/maps/mapa1/Capa 4.png"));
            this.image[4] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/maps/mapa1/Capa 5.png"));
            this.image[5] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/maps/mapa1/Capa 6.png"));
            this.image[6] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/maps/mapa1/Capa 7.png"));
            this.image[7] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/maps/mapa1/Capa 8.png"));
            this.image[8] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/maps/mapa1/Capa 9.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2, int x, int y) {
        for (int i = 0; i <= 9; i++) {
            if (this.image[i] != null) {
                g2.drawImage(this.image[i], x, y, getTileSize()*getWorldCols(), getTileSize()*getWorldRows(), null);
            }
        }
    }
}
