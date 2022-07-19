/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client.game.nodes;

import client.game.engine.nodos.AbstractCamera;
import client.game.engine.GameContainer;
import client.game.engine.GameNode;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author hecto
 */
public class Triangulito extends GameNode {
    @Override
    public void created(GameContainer container) {
        
    }
    @Override
    public void update(GameContainer container) {

    }
    @Override
    public void draw(GameContainer container, Graphics2D g2) {
        int scale = container.getScale().getScale();
        int tileSize = container.getScale().getOriginalTileSize();
      
        g2.setColor(Color.ORANGE);
        int alto = tileSize * scale;
        int ancho = tileSize * scale;
        int offSetX = 0;
        int offSetY = 0;
        
        g2.fillRect(drawX, drawY, alto, ancho);
        g2.setColor(Color.BLUE);
        g2.fillRect(x, y, alto, ancho);
    }
}
