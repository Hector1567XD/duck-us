package client.game.tiles;

import client.game.engine.GameContainer;
import client.game.engine.tiles.Tiles;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
//import client.game.engine.core.Window;

/**
 *
 * @author david_000
 */
public class MapTilesManager {
    GameContainer container;
    int size;
    Tiles[] tile;
    int mapTileNum[][];

    public MapTilesManager(GameContainer container) {
        this.container = container;
        this.size = container.getScale().getTileSize();

        tile = new Tiles[10]; // 10 mosaicos
        mapTileNum = new int[container.getMaxMapCol()][container.getMaxMapRow()];
        getTileImagen();
        loadMap();
    }

    public void getTileImagen() {
        try {
            tile[0] = new Tiles();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/tiles/grass.png"));

            tile[1] = new Tiles();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/tiles/wall.png"));

            tile[2] = new Tiles();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/tiles/water.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/client/game/maps/map1");
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); // lector almacenado de buffer
            int col = 0;
            int row = 0;

            while (col < container.getMaxMapCol() && row < container.getMaxMapRow()) {
                String line = br.readLine(); // esto leera una linea
                while (col < container.getMaxMapCol()) {
                    String numbers[] = line.split(" "); // esto parte la linea asi obteniendo los numeros del mapa 1 por
                                                        // 1
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == container.getMaxMapCol()) {
                    col = 0;
                    row++;
                }
            }

        } catch (Exception e) {
        }
    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        while (col < container.getMaxMapCol() && row < container.getMaxMapRow()) {
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image, x, y, size, size, null);
            col++;
            x += size;

            if (col == container.getMaxMapCol()) {
                col = 0;
                x = 0;
                row++;
                y += size;
            }
        }
        // container.draw(g2);
    }

}
