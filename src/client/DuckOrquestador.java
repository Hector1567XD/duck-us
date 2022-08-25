package client;

import client.core.DuckConnect;
import client.core.DuckGame;
import client.forms.AcercaDe;
import client.forms.Ayuda;
import client.forms.Connect;
import client.forms.MenuPrincipal;
import client.forms.MenuWindow;
import client.forms.SeleccionDeOpciones;
import client.forms.SeleccionDeOpciones;
import client.networking.Client;
import common.networking.engine.socket.SocketPublisher;

public class DuckOrquestador {
    MenuWindow window;
    SocketPublisher publisher;
    Client client;
    String nombre;
    String defaultName;

    public DuckOrquestador() {
        this.defaultName = Constants.DEFAULT_NAME;
        this.publisher = new SocketPublisher();
    }
    
    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
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
        window.setPanel(new Connect(this, defaultName));
    }
    
    public void openAcercaDe() {
        window.setPanel(new AcercaDe(this));
    }
    
    public void openAyuda() {
        window.setPanel(new Ayuda(this));
    }
    
    
    public void connectToServer(String ipAddress, int port) {
        this.client = DuckConnect.createClient(publisher, ipAddress, port);
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    public void openGame() {
        if (window != null) {
            window.close();
        }
        DuckGame.start(publisher, client, nombre);
    }

    public void starGameWithoutMenu() {
        this.connectToServer(Constants.DEFAULT_IP_ADDRESS, Integer.parseInt(Constants.DEFAULT_PORT));
        this.setNombre(defaultName);
        this.openGame();
    }
}
