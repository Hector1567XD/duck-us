package client.game.tiles;

import client.game.engine.GameContainer;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import client.game.nodes.MapEscuelaNode;

/**
 *
 * @author david_000
 */
public class MapTilesManager {
    GameContainer container;
    int size;
    int mapTileNum[][];
    public final int worldWitdh;
    public final int worldHeight;
    public MapEscuelaNode mapNode;

    public GameContainer getContainer() {
        return container;
    }

    public int getSize() {
        return size;
    }

    public int[][] getMapTileNum() {
        return mapTileNum;
    }    

    public MapTilesManager(MapEscuelaNode mapNode, GameContainer container) {
        this.mapNode = mapNode;
        this.container = container;
        this.size = container.getScale().getTileSize();

        worldWitdh = size * container.getMaxMapCol();
        worldHeight = size * container.getMaxMapRow();

        mapTileNum = new int[container.getMaxMapCol()][container.getMaxMapRow()];
        loadMap();
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

    /*public void draw(Graphics2D g2) {
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
    }*/

    /*@Override
    public void draw(Graphics2D g2) {
        if (showCollitionsShape) {
            //CenterBorders centerBorders = this.parent.getCenterBorders();
            g2.setColor(Color.ORANGE);
            CollideBox collideBox = this.getPositionCollideBox(this.x, this.y);
            this.drawCollideBox(container, g2, collideBox);
            MapTilesManager mapTilesManager = mapNode.getMapa();
            int[][] arregloTilesets = mapTilesManager.getMapTileNum();

            for (int posX = 0; posX < container.getMaxMapCol(); posX++) {
                for (int posY = 0; posY < container.getMaxMapRow(); posY++) {
                    int tile = arregloTilesets[posX][posY];
                    if (tile == 1) {
                        int offsetBlock = CommonConstants.TILE_SIZE / 2;
                        CollideBox tileCollideBox = this.getCollideBoxByTile(posX, posY, offsetBlock);
                        g2.setColor(Color.red);
                        this.drawCollideBox(container, g2, tileCollideBox);
                    }
                }
            }
        }
    }*/
}
