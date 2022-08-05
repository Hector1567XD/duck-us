/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client.game.nodes;

import client.game.engine.GameContainer;
import client.game.engine.GameNode;
import client.game.engine.core.Input;
import client.game.nodes.classes.Sound;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author david_000
 */
public class Mision4 extends GameNode{
    private AbrirMision4 mision4;
    private boolean ganaste;
    private boolean abrir = false;
    private BufferedImage[] imagen;
    private int contador;
    private int control;
    private Input input;
    private boolean soundDead = false;
    Sound sound = new Sound();

    
    public void setParentMision(AbrirMision4 mision4) {
        this.mision4 = mision4;
    }
    
    public Mision4(AbrirMision4 mision4) {
        this.mision4 = mision4;
    }

    public Mision4() {}

    public boolean isAbrir() {
        return abrir;
    }

    public void setAbrir(boolean abrir) {
        this.abrir = abrir;
    }

    public boolean isGanaste() {
        return ganaste;
    }

    public void setGanaste(boolean ganaste) {
        this.ganaste = ganaste;
    }

    private void ganarMision(GameContainer container) {           
        if (contador == 0) {
            this.setAbrir(false);
            this.mision4.setMisionAbierta(false);
            Player player = container.getController().getNodes().findByName("Player");
            player.setMisionOpen(false);
            this.mision4.setGanaste(ganaste);
        }
    }

    @Override
    public void created(GameContainer container) {
        try {
            this.imagen = new BufferedImage[6]; 
            this.imagen[0] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/misiones/mision4/cartelara duck us.png"));
            this.imagen[1] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/misiones/mision4/cartelara duck us 2.png"));
            this.imagen[2] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/misiones/mision4/cartelara duck us 3.png"));
            this.imagen[3] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/misiones/mision4/cartelara duck us 4.png"));
            this.imagen[4] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/misiones/mision4/cartelara duck us 5.png"));
            this.imagen[5] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/misiones/mision4/cartelara duck us 6.png"));
            this.input = container.getInput();
            this.contador = 13*10;
            this.control = 0;
                    
                    
        }  catch (IOException ex) {

        }
        
        
    }

    @Override
    public void update(GameContainer container) {
        if (isAbrir() == true) {
            if (input.isKeyDown(KeyEvent.VK_O) && control <5) {
                control++;
            }
            
            if (control >=5) {
                contador--;
                this.ganaste = true;
                this.ganarMision(container);
            }
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
            g2.setColor(new Color(0, 0, 0, 85));
            g2.fillRect(0, 0, maxScreenCol * tileSize, maxScreenRow * tileSize);
            g2.drawImage(imagen[control], (int) (1.5 * tileSize), (int) (1.5 * tileSize), (int) (maxScreenCol - 2.5) * tileSize,
                    (int) (maxScreenRow - 2.5) * tileSize, null);
            g2.setFont(new Font("Arial", Font.BOLD, 20 * scale));
            g2.drawString("PRESIONE O PARA LIMPIAR",135 * scale , 300 * scale);
            if (ganaste == true) {
                  if (contador!=0 && soundDead==false){
                        sound.setFile(8);
                        sound.play();
                        soundDead = true;
                     }
                g2.drawString("MISION CUMPLIDA", 170 * scale, 150 * scale);
            }

        }
        
    }

    @Override
    public int getNodeLevel() {
        return 500;
    }
}
