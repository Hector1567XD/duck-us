/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client.game.engine;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import client.game.engine.core.Panel;
import client.game.engine.core.Window;

/**
 *
 * @author david_000
 */
public class TilesManager {
    GameContainer container;
    int tamaño = container.getScale().getTileSize();
    Window gp;
    Tiles[] tile;
    int mapTileNum[][];

    public TilesManager(Window gp) {
        this.gp = gp;
        tile = new Tiles[10]; //10 mosaicos
        mapTileNum = new int[gp.getMaxScreenCol()][gp.getMaxScreenRow()];
        getTileImagen();
        loadMap();
    }
     public void getTileImagen() {
        try{
           tile[0] = new Tiles();
           tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Resource.Tile/grass.png"));
           
           tile[1] = new Tiles();
           tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Resource.Tile/wall.png"));
           
           tile[2] = new Tiles();
           tile[2].image = ImageIO.read(getClass().getResourceAsStream("/Resource.Tile/water.png"));
            
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void loadMap() { 
       try {
           InputStream is = getClass().getResourceAsStream("/Mapa/mapa.txt");
           BufferedReader br = new BufferedReader(new InputStreamReader(is)); //lector almacenado de buffer
           int col = 0;
           int row = 0;
           
           while(col < gp.getMaxScreenCol() && row < gp.getMaxScreenRow()){
               String line  = br.readLine(); //esto leera una linea 
               while (col <gp.getMaxScreenCol()) {
                   String numbers[] = line.split(" "); //esto parte la linea asi obteniendo los numeros del mapa 1 por 1
                   int num = Integer.parseInt(numbers[col]);
                   mapTileNum[col][row] = num;
                   col++;
               }
             if (col == gp.getMaxScreenCol()){
                 col = 0;
                 row++;
             }  
           }
            
       }catch (Exception e){
       }
    }
    
    public void draw(Graphics2D g2){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        while(col < gp.getMaxScreenCol() && row < gp.getMaxScreenRow())   {
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image, x, y, tamaño,tamaño,null);
            col++;
            x+= tamaño;
            
            if (col == gp.getMaxScreenCol()) {
                col = 0;
                x = 0;
                row++;
                y += tamaño;
            }
        }
     container.draw(g2);
    }
    
    
}
