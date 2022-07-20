package client;

import client.core.DuckGame;
import client.core.DuckMenu;
import client.game.engine.GameContainer;
import client.game.engine.GameNetwork;
import client.game.engine.GameController;
import common.networking.DuckPacketReader;
import common.networking.engine.socket.SocketPublisher;
import client.networking.Client;
import client.game.nodes.MapNode;
import client.game.nodes.Player;

public class DuckUs {
    public static void main(String[] args) {
        /*if (true) {
        
        }*/
        DuckOrquestador orquestator = new DuckOrquestador();
        if (Constants.SKIP_MENU) {
            orquestator.starGameWithoutMenu();
        }else{
            DuckMenu.start(orquestator);
        }
    }
}
