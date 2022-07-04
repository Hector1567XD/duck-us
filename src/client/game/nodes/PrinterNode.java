/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client.game.nodes;

import client.game.engine.GameContainer;
import client.game.engine.GameController;
import client.game.engine.GameNode;
import common.utils.NodeCollectionUtils;
import java.awt.Graphics2D;

/**
 *
 * @author hecto
 */
public class PrinterNode extends GameNode {

    @Override
    public void created(GameContainer container) {
        GameController controller = container.getController();
        System.out.println("NODE LIST:");
        NodeCollectionUtils.print(controller.getNodes());

        // Deberia retornar el jugador;
        System.out.println("Jugador:" + controller.getNodes().get(0));

         // Deberia retornar especificamente al nodo de ID 3;
        System.out.println("Nodo(ID:3):" + controller.getNodes().find(3));
        // Deberia retornar el jugador;
        System.out.println("Jugador:" + controller.getNodes().find(1));
        System.out.println("================");
        System.out.println("Jugador:");
        // Imprimira todos la lista de nodos con tag "Jugador"
        NodeCollectionUtils.print(controller.getNodes().getListByTag("Player"));
        System.out.println("ExampleNode:");
        // Imprimira todos los nodos con el tag "ExampleNode"
        NodeCollectionUtils.print(controller.getNodes().getListByTag("ExampleNode"));
        System.out.println("Nothing:");
        // Imprimira ninguna lista de nodos
        NodeCollectionUtils.print(controller.getNodes().getListByTag("Nothing"));
        System.out.println("================");
        NodeCollectionUtils.print(controller.getNodes().getListByTag("Nothing"));
    }

    @Override
    public void update(GameContainer container) {
        
    }

    @Override
    public void draw(GameContainer container, Graphics2D g2) {
        
    }
    
}
