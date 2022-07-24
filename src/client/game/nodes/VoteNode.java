/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.game.nodes;

import client.game.engine.GameContainer;
import client.game.engine.GameNode;
import client.game.engine.core.Input;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;

/**
 *
 * @author Ortiz pc
 */
public class VoteNode extends GameNode {
  
    private boolean abrir = false;
    private AbrirVote votacion;
    
    
    public VoteNode() {
    }
    
    public VoteNode(AbrirVote mision1) {
        this.votacion = mision1;
    }

    public boolean isAbrir() {
        return abrir;
    }
    
    
    
    public void setAbrir(boolean abrir) {
        this.abrir = abrir;
    }
    
    
    
    @Override
    public void created(GameContainer container) {
        this.x = 140;
        this.y = 200;
       
    }       

    @Override
    public void update(GameContainer container) {
     if (isAbrir() == true) {  
         //Input input = container.getInput(); 
         //MouseListener[] click = container.getWindow().getWindow().getMouseListeners();
             //this.setAbrir(false);
            // this.votacion.setMisionAbierta(false);
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
        g2.setFont(new Font( "Arial", Font.BOLD, 23*scale ));
        g2.drawString("VOTACIONES", 250*scale, 150*scale);
    //  g2.drawString(palabra, 400, 300);

      } 
        
    }

    public String getNodeTag() {
        return "vote";
    }
    
    public int getOffsetX() {
        return 16;
    }

    public int getOffsetY() {
        return 16;
    }
    
}
 
    
    
    

