package common.networking.engine;

import java.net.InetAddress;
import java.util.Objects;

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

    /*
        Sobrescribimos equals para que pueda verificar si 2 Agents a pesar
        de ser distintas referencias tienen los mismos datos y por lo tanto
        realmente son el mismo objeto
    */
    // TODO: Esto podria no ser tan optimo ya que existiran duplicados de 1 mismo Agent, pero de momento funcionara
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Agent)) return false;
        Agent otherAgent = (Agent) obj;

        return otherAgent.port == this.port && this.ipAddress.equals(otherAgent.ipAddress);
    }
}
