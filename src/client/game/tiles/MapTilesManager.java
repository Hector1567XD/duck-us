package client.game.tiles;

import client.game.engine.GameContainer;
import client.game.engine.tiles.Tiles;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import client.game.nodes.MapNode;

/**
 *
 * @author david_000
 */
public class MapTilesManager {
    GameContainer container;
    int size;
    Tiles[] tile;
    int mapTileNum[][];
    public final int worldWitdh;
    public final int worldHeight;
    public MapNode mapNode;

    public GameContainer getContainer() {
        return container;
    }

    public int getSize() {
        return size;
    }

    public Tiles[] getTile() {
        return tile;
    }

    public int[][] getMapTileNum() {
        return mapTileNum;
    }    

    public MapTilesManager(MapNode mapNode, GameContainer container) {
        this.mapNode = mapNode;
        this.container = container;
        this.size = container.getScale().getTileSize();

        worldWitdh = size * container.getMaxMapCol();
        worldHeight = size * container.getMaxMapRow();

        tile = new Tiles[10]; //10 mosaicos
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
           BufferedReader br = new BufferedReader(new InputStreamReader(is)); //lector almacenado de buffer
           int col = 0;
           int row = 0;
           
           while(col < 16 && row < 12){
               String line  = br.readLine(); //esto leera una linea 
               while (col < container.getMaxMapCol()) {
                   String numbers[] = line.split(" "); //esto parte la linea asi obteniendo los numeros del mapa 1 por 1
                   int num = Integer.parseInt(numbers[col]);
                   mapTileNum[col][row] = num;
                   col++;
               }
             if (col == container.getMaxMapCol()){
                 col = 0;
                 row++;
             }  
           }
            
       }catch (Exception e){
       }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < container.getMaxMapCol() && worldRow < container.getMaxMapRow())   {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * size;
            int worldY = worldRow * size;

            // El Tileset SIGUE al nodo de mapNode que es el nodo donde se mete este MapTilesManager
            int screenX = worldX + mapNode.getDrawX();
            int screenY = worldY + mapNode.getDrawY();
  
            g2.drawImage(tile[tileNum].image, screenX, screenY , size, size, null);
            worldCol++;

            if (worldCol >= container.getMaxMapCol()) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
