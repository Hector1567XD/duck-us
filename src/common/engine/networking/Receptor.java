package common.engine.networking;

import java.net.InetAddress;

public abstract class Receptor {
    private InetAddress ipAddress;
    private int port;

    public Receptor(InetAddress ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public InetAddress getIpAddress() {
        return ipAddress;
    }

    public int getPort() {
        return port;
    }
}
