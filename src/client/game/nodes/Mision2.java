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
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author david_000
 */
public class Mision2 extends GameNode {

    private boolean abrir = false;
    private String palabra;
    private char[] letrasRestantes;
    private int[] keyRestantes;
    private int letraActual;
    private String palabraAEscribir;
    private AbrirMision2 mision2;
    private String cadena;
    private BufferedImage[] imagen;
    private int contador;
    private boolean ganaste;
    private boolean soundDead = false;
    Sound sound = new Sound();

    public void setParentMision(AbrirMision2 mision2) {
        this.mision2 = mision2;
    }
    
    public Mision2(AbrirMision2 mision2) {
        this.mision2 = mision2;
    }

    public Mision2() {}
    
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
    
    
    @Override
    public void created(GameContainer container) {
        try {
            this.imagen = new BufferedImage[2];
            this.imagen[0] = ImageIO.read(getClass().getResourceAsStream("/client/resources/game/misiones/misiontyperacer.png"));
            this.palabraAEscribir = "Harry potter es un legendario mago que estudio en la academia Howard ";
            this.letraActual = 0;
            this.cadena = palabraAEscribir.substring(0, this.palabraAEscribir.length());
            this.contador = 13 * 10;
            this.ganaste = false;
            

        } catch (IOException ex) {
            
        }
        
    }

    @Override
    public void update(GameContainer container) {
        if (isAbrir() == true) {
            Input input = container.getInput();
            String palabraAEscribirMayuscula = palabraAEscribir.toUpperCase();
            // Convertimos a mayusculas por que el Input KeyEvent solo lee las constantes en mayuscula
            char[] letrasAEscribir = palabraAEscribirMayuscula.toCharArray();
            char character = letrasAEscribir[letraActual];
            int ascii = (int) character;
            if (input.isKeyDown(ascii)) {
                letraActual++;     
            }
            if (letraActual == this.palabraAEscribir.length()-1){
                       contador--;
                       this.ganaste = true;
                       this.ganarMision(container);
                   }
         }
     }

    private void ganarMision(GameContainer container) {
        
       if (contador == 0) {
         this.setAbrir(false);
         this.mision2.setMisionAbierta(false);
         Player player = container.getController().getNodes().findByName("Player");
         player.setMisionOpen(false);
         this.mision2.setGanaste(ganaste);
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

            g2.setColor(Color.white);
            g2.drawImage(imagen[0], (int) (1.5 * tileSize), (int) (1.5 * tileSize), (int) (maxScreenCol - 2.5) * tileSize,
                     (int) (maxScreenRow - 2.5) * tileSize, null);
            g2.setFont(new Font("Arial", Font.BOLD, 15 * scale));
            g2.drawString("Escribir Veloz", 200 * scale, 75 * scale);

            g2.setFont(new Font("Arial", Font.BOLD, 12 * scale));

            g2.drawString("APURATE!! Esta es la palabra : ", 150, 125 * scale);
            g2.setFont(new Font("Arial", Font.BOLD, 10 * scale));
            g2.drawString(palabraAEscribir, 70 * scale, 136 * scale);
            g2.setColor(Color.BLACK);
            g2.drawString("Resultado : ", 75 * scale, 180 * scale);
            g2.drawString(palabraAEscribir.substring(0, letraActual), 74 * scale, 200 * scale);
              if (ganaste == true) {
                   if (contador!=0 && soundDead==false){
                        sound.setFile(8);
                        sound.play();
                        soundDead = true;
                     }
                  g2.setFont(new Font("Arial", Font.BOLD, 20 * scale));
                  g2.drawString("MISION CUMPLIDA", 170 *scale, 250 *scale);
              }
              
        }

    }

    public int getOffsetX() {
        return 16;
    }

    public int getOffsetY() {
        return 16;
    }

    @Override
    public int getNodeLevel() {
        return 500;
    }
}
