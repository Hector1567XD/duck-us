package client.core;

import client.DuckOrquestador;
import client.forms.MenuWindow;

public class DuckMenu {
    public static void start(DuckOrquestador orquestator) {
        MenuWindow window = new MenuWindow();
        window.open();
        
        orquestator.setWindow(window);
        orquestator.openMenuPrincipal();
    }
}
