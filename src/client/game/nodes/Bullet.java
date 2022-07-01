package client.game.nodes;

import client.game.engine.GameContainer;
import client.game.engine.GameNode;
import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet extends GameNode {
    Player jugador; //almacenador de la inyeccion
    
    public Bullet(Player jugador) { //inyeccion de depedencia se usa para que la bala que fue creada por el jugador 
        this.jugador = jugador;
        this.x = jugador.getX();
        this.y = jugador.getY();     //obtengamos los metodos del jugador para utilizarlos :3
    }
    
    @Override
    public void created(GameContainer container) {
        //
    }

    @Override
    public void update(GameContainer container) {
        //
    }

    @Override
    public void draw(GameContainer container, Graphics2D g2) {
        int scale = container.getScale().getScale();
        g2.setColor(Color.yellow);
        g2.fillOval(this.x * scale, this.y * scale, 8 * scale, 8 * scale);//listo la bala se genera en la posicion del jugador
    }
}
