package common.game.engine;

import common.networking.engine.Agent;
import common.networking.engine.Packet;
import common.networking.engine.socket.SocketEventSuscriber;
import java.util.LinkedList;
import java.util.Queue;

public abstract class Network implements SocketEventSuscriber {
    protected Queue<Packet> pendingPackages = new LinkedList<Packet>();

    public void update(Container container) {
        while (!pendingPackages.isEmpty()) {
            Packet packet = pendingPackages.poll();
            packetArrived(container, packet);
        }
    }

    @Override
    public void packetReceived(Packet packet) {
        System.out.println("Recibiendo paquete tipo " + packet.getPackageType() + " (" + packet.getDatagramPacket().getLength() + " bytes). hacia " + packet.getSender().getIpAddress().getHostAddress() + ":" + packet.getSender().getPort());
        appendPacket(packet);
    }

    private void appendPacket(Packet packet) {
        pendingPackages.add(packet);
    }

    @Override
    public void packetSended(Packet packet, Agent receiver) {
        System.out.println("Enviando paquete tipo " + packet.getPackageType() + " (" + packet.getDatagramPacket().getLength() + " bytes). hacia " + packet.getReceiver().getIpAddress().getHostAddress() + ":" + packet.getReceiver().getPort());
        // Las "Network" no necesitan saber de los paquetes enviados :)
    }

    public abstract void packetArrived(Container container, Packet packet);
}
