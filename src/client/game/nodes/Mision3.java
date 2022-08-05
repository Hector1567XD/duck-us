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
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author david_000
 */
public class Mision3 extends GameNode {

    private AbrirMision3 mision3;
    private boolean ganaste;
    private boolean abrir = false;
    private BufferedImage[] imagen;
    private int contador;
    private int timer;
    private int control;
    private int imprimir;
    private boolean soundDead = false;
    Sound sound = new Sound();

    public void setParentMision(AbrirMision3 mision3) {
        this.mision3 = mision3;
    }
    
    public Mision3(AbrirMision3 mision3) {
        this.mision3 = mision3;
    }

    public Mision3() {}

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
            this.mision3.setMisionAbierta(false);
            Player player = container.getController().getNodes().findByName("Player");
            player.setMisionOpen(false);
            this.mision3.setGanaste(ganaste);
        }
    }

    @Override
    public void created(GameContainer container) {
        try {
            this.imagen = new BufferedImage[12];
            this.imagen[0] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/misiones/mision3/mision3_0.png"));
            this.imagen[1] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/misiones/mision3/mision3_1.png"));
            this.imagen[2] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/misiones/mision3/mision3_2.png"));
            this.imagen[3] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/misiones/mision3/mision3_3.png"));
            this.imagen[4] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/misiones/mision3/mision3_4.png"));
            this.imagen[5] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/misiones/mision3/mision3_5.png"));
            this.imagen[6] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/misiones/mision3/mision3_6.png"));
            this.imagen[7] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/misiones/mision3/mision3_7.png"));
            this.imagen[8] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/misiones/mision3/mision3_8.png"));
            this.imagen[9] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/misiones/mision3/mision3_9.png"));
            this.imagen[10] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/misiones/mision3/mision3_10.png"));
            this.imagen[11] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/misiones/mision3/mision3_11.png"));
            this.contador = 13 * 10;
            this.timer = 15 * 60;
            this.control = 0;
            this.imprimir = 0;
            this.ganaste = false;

        } catch (IOException ex) {

        }

    }

    @Override
    public void update(GameContainer container) {
        if (isAbrir() == true) {
           if (timer != 0){ 
            timer--;
            control++;
           }

            if (control == 81) {   
              imprimir++;
              control = 0;
            }
            if (timer == 0) {
                timer = 0;
                control = 0;
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
            g2.drawImage(imagen[imprimir], (int) (1.5 * tileSize), (int) (1.5 * tileSize), (int) (maxScreenCol - 2.5) * tileSize,
                    (int) (maxScreenRow - 2.5) * tileSize, null);
            if (ganaste == true) {
                   if (contador!=0 && soundDead==false){
                        sound.setFile(11);
                        sound.play();
                        soundDead = true;
                     }
                g2.setFont(new Font("Arial", Font.BOLD, 20 * scale));
                g2.drawString("MISION CUMPLIDA", 170 * scale, 70 *scale);
            }

        }
    }

    @Override
    public int getNodeLevel() {
        return 500;
    }
}
