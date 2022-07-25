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

    @Override
    public void created(GameContainer container) {
        this.x = 235;
        this.y = 200;
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
                if (letrasEncontradas >= letrasTotalesAEncontrar) {
                    this.ganarMision(container);
                }
            }

        }
    }
    
    private void ganarMision(GameContainer container) {
        this.setAbrir(false);
        this.mision1.setMisionAbierta(false);
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
            g2.setFont(new Font("Arial", Font.BOLD, 46));
            g2.drawString("JUEGO DEL AHORCADO", 250, 150);
            g2.drawString(palabra, 400, 300);

        }

    }

    public int getOffsetX() {
        return 16;
    }

    public int getOffsetY() {
        return 16;
    }

}
