package common.networking.engine.socket;

import common.networking.engine.Agent;
import common.networking.engine.Packet;

public interface SocketEventSuscriber {
    public void packetReceived(Packet packet);
    public void packetSended(Packet packet, Agent receiver);
}
