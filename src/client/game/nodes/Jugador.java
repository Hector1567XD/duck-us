package client.game.nodes;

import client.game.engine.GameContainer;
import client.game.engine.GameController;
import client.game.engine.GameNode;
import client.game.engine.core.Input;
import common.game.engine.node.NodeCollection;
import common.utils.NodeCollectionUtils;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Jugador extends GameNode {
    private int contador = 0;

    public Jugador() {
        System.out.println("Constructor del nodo jugador");
    }

    @Override
    public void created(GameContainer container) {
        this.x = 128;
        this.y = 256;
        System.out.println("Nodo jugador creado");
    }
    @Override
    public void update(GameContainer container) {
        contador++;

        if (contador == 30) {
            Cuadrado cuadrado1 = new Cuadrado(contador-120);
            container.getController().addNode(cuadrado1, "cuadrado-1");
        }

        if (contador == 100) {
            container.getController().addNode(new Cuadrado(contador-120));
        }

        if (contador == 160) {
            container.getController().addNode(new Cuadrado(contador-120));
        }

        if (contador == 170) {
            NodeCollectionUtils.print(container.getController().getNodes());
        }

        Input input = container.getInput();
        if (input.isKeyDown(KeyEvent.VK_A)) {
            Cuadrado cuadradito = container.getController().getNodes().findByName("cuadrado-1");
            if (cuadradito != null) {
                cuadradito.eliminarCuadradito();
            }
        }

        /*Input input = container.getInput();
        Cuadrado cuadradito = container.getController().getNodes().find(3);
        if (cuadradito != null) {
            if (input.isKeyDown(KeyEvent.VK_A)) {
                cuadradito.eliminarCuadradito();
            }
        }*/
        
        /*if (contador == 200) {
            ArrayList<Jugador> jugadorNodes = container.getController().getNodes().getListByTag("Jugador");
            NodeCollectionUtils.print(jugadorNodes);
            for (Jugador jugador: jugadorNodes) {
                if (jugador.getPlayerId() == 5) {
                    jugador.morir();
                }
            }
        }*/
        
        /*if (contador == 200) {
            GameController controller = container.getController();
            NodeCollection nodeCollection = controller.getNodes();
            ArrayList<Cuadrado> cuadrados = nodeCollection.getListByTag("Cuadrado");
            NodeCollectionUtils.print(cuadrados);
            for (Cuadrado cuadrado: cuadrados) {
                if (cuadrado.getNodeId() == 3) {
                    cuadrado.remove();
                }
            }
        }*/
    }
    
    public int getPlayerId() {
        return 5;
    }
    
    public void morir() {
        // Hacer algo :3
    }

    @Override
    public void draw(GameContainer container, Graphics2D g2) {
        int escala = container.getScale().getScale();
        g2.fillRect(x*escala, y*escala, 32*escala, 32*escala);
    }
    
    public String getNodeTag() {
        return "Jugador";
    }
}
