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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author david_000
 */
public class Mision extends GameNode {

    private boolean abrir = false;
    private String palabra;
    private char[] letrasRestantes;
    private int[] keyRestantes;
    private int[] posicionesRestantes;
    private int letrasEncontradas;
    private int letrasTotalesAEncontrar;
    private AbrirMision1 mision1;
    private BufferedImage[] imagen;
    private boolean ganaste;
    private int contador;
    private boolean soundDead = false;
    Sound sound = new Sound();

    public void setParentMision(AbrirMision1 mision1) {
        this.mision1 = mision1;
    }

    public Mision() {
    }

    public Mision(AbrirMision1 mision1) {
        this.mision1 = mision1;
    }

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
            this.imagen = new BufferedImage[3];
            this.imagen[0] = ImageIO
                    .read(getClass().getResourceAsStream("/client/resources/game/misiones/Te ahorco v1.png"));
            this.imagen[1] = ImageIO
                    .read(getClass().getResourceAsStream("/client/resources/game/misiones/Te ahorco v2.png"));
            this.imagen[2] = ImageIO
                    .read(getClass().getResourceAsStream("/client/resources/game/misiones/Te ahorco v3.png"));
            this.palabra = "H_l_Mundo";
            this.keyRestantes = new int[3];
            this.keyRestantes[0] = KeyEvent.VK_O;
            this.keyRestantes[1] = KeyEvent.VK_A;
            this.letrasEncontradas = 0;
            this.letrasTotalesAEncontrar = 2;
            this.letrasRestantes = new char[2];
            this.letrasRestantes[0] = 'o';
            this.letrasRestantes[1] = 'a';
            this.posicionesRestantes = new int[2];
            this.posicionesRestantes[0] = 1;
            this.posicionesRestantes[1] = 3;
            this.ganaste = false;
            this.contador = 13 * 10;

        } catch (IOException ex) {

        }

    }

    @Override
    public void update(GameContainer container) {
        if (isAbrir() == true) {
            Input input = container.getInput();
            if (input.isKeyDown(keyRestantes[letrasEncontradas])) {
                char[] palabraArreglo = palabra.toCharArray();
                palabraArreglo[posicionesRestantes[letrasEncontradas]] = letrasRestantes[letrasEncontradas];
                this.palabra = String.valueOf(palabraArreglo);
                letrasEncontradas++;

            }
            if (letrasEncontradas >= letrasTotalesAEncontrar) {
                contador--;
                this.ganaste = true;
                this.ganarMision(container);
            }

        }
    }

    private void ganarMision(GameContainer container) {
        if (contador == 0) {
            this.setAbrir(false);
            this.mision1.setMisionAbierta(false);
            Player player = container.getController().getNodes().findByName("Player");
            player.setMisionOpen(false);
            this.mision1.setGanaste(ganaste);

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
            g2.drawImage(imagen[letrasEncontradas], (int) (1.5 * tileSize), (int) (1.5 * tileSize),
                    (int) (maxScreenCol - 2.5) * tileSize,
                    (int) (maxScreenRow - 2.5) * tileSize, null);

            g2.setColor(Color.BLACK);

            g2.setFont(new Font("Arial", Font.BOLD, 23 * scale));
            // g2.drawString("JUEGO DEL AHORCADO", 125 *scale, 75 *scale);
            g2.drawString(palabra, 200 * scale, 200 * scale);
            if (ganaste == true) {
                if (contador != 0 && soundDead == false) {
                    sound.setFile(8);
                    sound.play();
                    soundDead = true;
                }
                g2.setFont(new Font("Arial", Font.BOLD, 20 * scale));
                g2.drawString("MISION CUMPLIDA", 170, 250);
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
