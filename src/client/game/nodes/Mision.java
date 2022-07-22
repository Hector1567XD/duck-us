/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client.game.nodes;

import client.game.engine.GameContainer;
import client.game.engine.GameNode;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Scanner;

/**
 *
 * @author david_000
 */
public class Mision extends GameNode {
    private boolean abrir = false;
    private String[] palabrasRestantes;
    Scanner leer = new Scanner(System.in);
    String acertar;
    
    public boolean isAbrir() {
        return abrir;
    }
    
    
    
    public void setAbrir(boolean abrir) {
        this.abrir = abrir;
    }
    
    
    
    @Override
    public void created(GameContainer container) {
        this.x = 235;
        this.y = 200;
        this.leer = leer;
        this.palabrasRestantes = new String[4];
        this.palabrasRestantes[0] = "o";
        this.palabrasRestantes[1] = "a";
        this.palabrasRestantes[2] = "u";
        this.palabrasRestantes[3] = "n";
    }       

    @Override
    public void update(GameContainer container) {
     if (isAbrir() == true) {   
       acertar = leer.nextLine();
       System.out.println(acertar);
     }
    }

    @Override
    public void draw(GameContainer container, Graphics2D g2) {
       if (isAbrir() == true) { 
        int scale = container.getScale().getScale();
        int originalTileSize = container.getScale().getOriginalTileSize();
        int tileSize = scale * originalTileSize;
        int maxScreenCol = container.getWindow().getMaxScreenCol();
        int maxScreenRow = container.getWindow().getMaxScreenRow();
        g2.setColor(new Color(0,0,0,85));
        g2.fillRect(0,0, maxScreenCol * tileSize, maxScreenRow * tileSize);
        
        g2.setColor(Color.BLACK);
        g2.fillRect((int) (1.5 * tileSize),(int) (1.5 * tileSize),(int) (maxScreenCol - 2.5) * tileSize,(int) (maxScreenRow - 2.5) * tileSize);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font( "Arial", Font.BOLD, 46 ));
        g2.drawString("JUEGO DEL AHORCADO", 250, 150);
        g2.drawString("H_L_M__DO", 400, 300);
        int i = 0;
        
           for ( i = 0; i<4; i++) {
               if (palabrasRestantes[i].equals(acertar)){
                   System.out.println("Entre :)");
                   g2.setFont(new Font( "Arial", Font.BOLD, 46 ));
                   g2.drawString(acertar, 450, 500);
                   palabrasRestantes[i] = "0";
                }   
           }    

      } 
        
    }

    public int getOffsetX() {
        return 16;
    }

    public int getOffsetY() {
        return 16;
    }
    
}
