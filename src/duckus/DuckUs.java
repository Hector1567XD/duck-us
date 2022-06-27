package duckus;

import common.engine.Scale;
import duckus.engine.*;

public class DuckUs {

    public static void main(String[] args) {
        GameController controller = new GameController();
        GameNetwork network = new GameNetwork();
        Scale scale = new Scale(32, 2);
        GameContainer container = new GameContainer(scale, network, controller);

        /*Player player = new Player();
        game.addNode(player);*/

        container.start();
    }
    
}
