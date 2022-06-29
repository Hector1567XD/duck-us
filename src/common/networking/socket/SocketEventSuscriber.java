package common.networking.socket;

import common.networking.Agent;
import common.networking.Packet;

public interface SocketEventSuscriber {
    public void packetReceived(Packet packet);
    public void packetSended(Packet packet, Agent receiver);
}
