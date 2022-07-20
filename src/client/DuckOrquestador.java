package client;

import client.core.DuckConnect;
import client.core.DuckGame;
import client.forms.Connect;
import client.forms.MenuPrincipal;
import client.forms.MenuWindow;
import client.forms.SeleccionDeOpciones;
import client.networking.Client;
import common.networking.engine.socket.SocketPublisher;

public class DuckOrquestador {
    MenuWindow window;
    SocketPublisher publisher;
    Client client;

    public DuckOrquestador() {
        this.publisher = new SocketPublisher();
    }
    
    public void setWindow(MenuWindow window) {
        this.window = window;
    }

    public void openMenuPrincipal() {
        window.setPanel(new MenuPrincipal(this));
    }

    public void openSeleccionDeOpciones() {
        window.setPanel(new SeleccionDeOpciones(this));
    }
    
    public void openConnect() {
        window.setPanel(new Connect(this));
    }
    
    public void connectToServer(String ipAddress, int port) {
        this.client = DuckConnect.createClient(publisher, ipAddress, port);
    }

    public void openGame() {
        if (window != null) {
            window.close();
        }
        DuckGame.start(publisher, client);
    }

    public void starGameWithoutMenu() {
        this.connectToServer(Constants.DEFAULT_IP_ADDRESS, Integer.parseInt(Constants.DEFAULT_PORT));
        this.openGame();
    }
}
