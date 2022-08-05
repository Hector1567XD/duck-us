package client.game.nodes.tiles;

import client.Constants;
import client.game.engine.GameContainer;
import client.game.engine.GameNode;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import client.game.utils.CollideBoxCamDrawer;
import client.utils.game.collitions.CenterBorders;
import client.utils.game.collitions.CollideBox;
import client.utils.game.collitions.CollitionsUtils;
import common.CommonConstants;
import java.awt.Color;

public abstract class MapCollitionManagerParent extends GameNode {
    protected int mapTileNum[][];
    private final boolean showCollitionsShape = Constants.SHOW_COLLISION_SHAPE && Constants.SHOW_COLLISION_MANAGER_COLLISION_SHAPE;
    private int offsetCols = 0;
    private int offsetRows = 0;

    public int getOffsetCols() {
        return offsetCols;
    }

    public int getOffsetRows() {
        return offsetRows;
    }

    public abstract int getWorldCols();

    public abstract int getWorldRows();

    public int[][] getMapTileNum() {
        return mapTileNum;
    }

    public MapCollitionManagerParent(int offsetCols, int offsetRows) {
        this.offsetCols = offsetCols;
        this.offsetRows = offsetRows;
        mapTileNum = new int[this.getWorldCols() + this.getOffsetCols()][this.getWorldRows() + this.getOffsetRows()];
        initializeTileNum();
        loadMap();
    }

    public abstract String getCollitionMapFileName();

    public void initializeTileNum() {
        System.out.println("=== INITIALIZE");
        int col = 0;
        int row = 0;
        while (col < getWorldCols() + getOffsetCols() && row < getWorldRows() + getOffsetRows()) {
            while (col < getWorldCols()) {
                mapTileNum[col][row] = 0;
                col++;
            }
            if (col == getWorldCols()) {
                col = 0;
                row++;
            }
        }
    }

    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream(getCollitionMapFileName());
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); //lector almacenado de buffer
            int col = 0;
            int row = 0;
            while (col < getWorldCols() && row < getWorldRows()) {
                String line = br.readLine(); //esto leera una linea 
                while (col < getWorldCols()) {
                    String numbers[] = line.split(" "); //esto parte la linea asi obteniendo los numeros del mapa 1 por 1
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col + getOffsetCols()][row + getOffsetRows()] = num;
                    col++;
                }
                if (col == getWorldCols()) {
                    col = 0;
                    row++;
                }
            }
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
            e.fillInStackTrace();
        }
    }

    @Override
    public void draw(GameContainer container, Graphics2D g2) {
        if (showCollitionsShape) {
            int[][] arregloTilesets = this.getMapTileNum();
            for (int posX = 0; posX < getWorldCols() + getOffsetCols(); posX++) {
                for (int posY = 0; posY < getWorldRows() + getOffsetRows(); posY++) {
                    int tile = arregloTilesets[posX][posY];
                    if (tile == 1) {
                        int offsetBlock = CommonConstants.TILE_SIZE / 2;
                        CollideBox tileCollideBox = this.getCollideBoxByTile(posX, posY, offsetBlock);
                        g2.setColor(Color.red);
                        CollideBoxCamDrawer.drawCollideBox(container, g2, tileCollideBox);
                    }
                }
            }
        }
    }

    public CollideBox getCollideBoxByTile(int posX, int posY, int offsetBlock) {
        int tilePosX = posX * CommonConstants.TILE_SIZE + offsetBlock;
        int tilePosY = posY * CommonConstants.TILE_SIZE + offsetBlock - 2 * CommonConstants.TILE_SIZE;

        CollideBox tileCollideBox = CollitionsUtils.createCenteredBox(
                tilePosX,
                tilePosY,
                new CenterBorders(offsetBlock, offsetBlock, offsetBlock, offsetBlock)
        );
        return tileCollideBox;
    }

    @Override
    public void created(GameContainer container) {
    }

    @Override
    public void update(GameContainer container) {
    }
}
