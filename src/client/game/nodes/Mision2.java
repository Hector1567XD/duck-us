/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client.game.nodes;

import client.game.engine.GameContainer;
import client.game.engine.GameNode;
import client.game.engine.core.Input;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    public Mision2(AbrirMision2 mision2) {
        this.mision2 = mision2;
    }

    public boolean isAbrir() {
        return abrir;
    }

    public void setAbrir(boolean abrir) {
        this.abrir = abrir;
    }

    @Override
    public void created(GameContainer container) {
        this.palabraAEscribir = "Harry potter es un legendario mago que estudio en la academa Howard";
        this.letraActual = 0;
        this.cadena = palabraAEscribir.substring(0, 67);

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
        }

    }

    private void ganarMision(GameContainer container) {
        this.setAbrir(false);
        this.mision2.setMisionAbierta(false);
        Player player = container.getController().getNodes().findByName("Player");
        player.setMisionOpen(false);
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

            g2.setColor(Color.BLACK);
            g2.fillRect((int) (1.5 * tileSize), (int) (1.5 * tileSize), (int) (maxScreenCol - 2.5) * tileSize,
                    (int) (maxScreenRow - 2.5) * tileSize);
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.BOLD, 23 * scale));
            g2.drawString("Escribir Veloz", 200 * scale, 75 * scale);
            g2.setFont(new Font("Arial", Font.BOLD, 12 * scale));

            g2.drawString("APURATE!! Esta es la palabra : ", 60, 120 * scale);
            g2.drawString(palabraAEscribir, 60 * scale, 150 * scale);
            g2.drawString("Resultado : ", 60 * scale, 180 * scale);
            g2.drawString(palabraAEscribir.substring(0, letraActual), 60 * scale, 200 * scale);

        }

    }

    public int getOffsetX() {
        return 16;
    }

    public int getOffsetY() {
        return 16;
    }

}