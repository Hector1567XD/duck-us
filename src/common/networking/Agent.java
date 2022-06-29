package common.networking;

import java.net.InetAddress;

public class Agent {
    private final InetAddress ipAddress;
    private final int port;

    public Agent(InetAddress ipAddress, int port) {
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
