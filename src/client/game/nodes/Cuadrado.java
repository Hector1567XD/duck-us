package client.game.nodes;

import client.game.engine.GameContainer;
import client.game.engine.GameNode;
import java.awt.Color;
import java.awt.Graphics2D;

public class Cuadrado extends GameNode {
    private int identificador;
    private int contador = 0;
    private int direccion = 0; // 0 UP 1 DER 2 ABJ 3 IZQ
    private int color = 1; // 1 = verde, -1 = rojo
    public Cuadrado(int identificador) {
        this.identificador = identificador;
    }

    @Override
    public void created(GameContainer container) {
        this.x = 256;
        this.y = 256;
        System.out.println("Nodo cuadrado creado " + identificador);
    }

    @Override
    public void update(GameContainer container) {
       if (contador > 60) {
          contador = 0;
          direccion += 1;
          if (direccion > 3) {
              direccion = 0;
          }
       }
       /*if (contador == 45) {
           this.changeColor();
       }*/
       contador++;
       if (direccion == 0) {
           this.y -= 2;
       }else if (direccion == 1) {
           this.x += 2;
       }else if (direccion == 2) {
           this.y += 2;
       }else if (direccion == 3) {
           this.x -= 2;
       }
    }

    @Override
    public void draw(GameContainer container, Graphics2D g2) {
        int escala = container.getScale().getScale();
        if (color == -1) {
            g2.setColor(Color.green);
        }else if (color == 1) {
            g2.setColor(Color.red);
        }
        g2.fillRect(x*escala, y*escala, 16*escala, 16*escala);
    }
    
    public void changeColor() {
        this.color = this.color*-1;
    }
    
    public void eliminarCuadradito() {
        this.remove();
    }
    
    public String getNodeTag() {
        return "Cuadrado";
    }
}
