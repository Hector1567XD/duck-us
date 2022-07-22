/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client.game.nodes;

import client.game.engine.GameContainer;
import client.game.engine.GameNode;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author david_000
 */
public class Mision extends GameNode {
    private boolean abrir = false;

    
    
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
    }

    @Override
    public void update(GameContainer container) {
    }

    @Override
    public void draw(GameContainer container, Graphics2D g2) {
       if (isAbrir() == true) { 
        /*int scale = container.getScale().getScale();
        int tileSize = container.getScale().getOriginalTileSize();
        
        g2.fillRect(235,200,container.getWindow().getScreenWidth()-300, container.getWindow().getScreenHeight()-200);
       */
        int scale = container.getScale().getScale();
        int originalTileSize = container.getScale().getOriginalTileSize();
        int tileSize = scale * originalTileSize;
        int maxScreenCol = container.getWindow().getMaxScreenCol();
        int maxScreenRow = container.getWindow().getMaxScreenRow();
        g2.setColor(new Color(0,0,0,85));
        g2.fillRect(0,0, maxScreenCol * tileSize, maxScreenRow * tileSize);
        
        g2.setColor(Color.WHITE);
        g2.fillRect((int) (1.5 * tileSize),(int) (1.5 * tileSize),(int) (maxScreenCol - 2.5) * tileSize,(int) (maxScreenRow - 2.5) * tileSize);
      } 
        
    }

    public int getOffsetX() {
        return 16;
    }

    public int getOffsetY() {
        return 16;
    }
    
}
