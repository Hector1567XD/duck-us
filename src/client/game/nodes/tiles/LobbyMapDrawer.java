package client.game.nodes.tiles;

import client.game.engine.GameContainer;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import client.game.nodes.MapNode;
import java.awt.image.BufferedImage;

public class LobbyMapDrawer {
    GameContainer container;
    /*public final int worldWitdh;
    public final int worldHeight;*/
    private final int tileSize;
    private BufferedImage[] image;
    private int mapXSize = 0;
    private int mapYSize = 0;

    public GameContainer getContainer() {
        return container;
    }

    public LobbyMapDrawer(MapNode mapNode, GameContainer container) {
        this.container = container;
        this.tileSize = container.getScale().getTileSize();

        /*worldWitdh = size * container.getMaxMapCol();
        worldHeight = size * container.getMaxMapRow();*/

        this.mapXSize = 40;
        this.mapYSize = 40;
        loadMapImageLayers();
    }

    private void loadMapImageLayers() {
        try {
            this.image = new BufferedImage[8];
            this.image[0] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/maps/lobby/Capa 1.png"));
            this.image[1] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/maps/lobby/Capa 2.png"));
            this.image[2] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/maps/lobby/Capa 3.png"));
            this.image[3] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/maps/lobby/Capa 4.png"));
            this.image[4] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/maps/lobby/Capa 5.png"));
            this.image[5] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/maps/lobby/Capa 6.png"));
            this.image[6] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/maps/lobby/Capa 7.png"));
            this.image[7] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/maps/lobby/Capa 8.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2, int x, int y) {
        for (int i = 0; i <= 7; i++) {
            g2.drawImage(this.image[i], x, y, tileSize*mapXSize, tileSize*mapYSize, null);
        }
    }
}
