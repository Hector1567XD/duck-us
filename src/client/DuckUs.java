package client;

import client.core.DuckGame;
import client.core.DuckMenu;

public class DuckUs {

    public static void main(String[] args) {
        DuckOrquestador orquestator = new DuckOrquestador();
        
        //orquestator.setDefaultName("Nombre por defecto");
        
        if (Constants.SKIP_MENU) {
            orquestator.starGameWithoutMenu();
        } else {
            DuckMenu.start(orquestator);
        }
    }
}
