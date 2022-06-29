package common.engine;

import common.networking.Packet;
import java.util.LinkedList;
import java.util.Queue;

public abstract class Network {
    protected Queue<Packet> pendingPackages = new LinkedList<Packet>();

    public abstract void start();

    public void appendPacket(Packet packet) {
        pendingPackages.add(packet);
    }

    public void update(Container container) {
        while (!pendingPackages.isEmpty()) {
            Packet packet = pendingPackages.poll();
            packetArrived(container, packet);
        }
    }

    public abstract void packetArrived(Container container, Packet packet);
}
