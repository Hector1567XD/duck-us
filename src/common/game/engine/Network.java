package common.game.engine;

import common.CommonConstants;
import common.networking.engine.Agent;
import common.networking.engine.Packet;
import common.networking.engine.socket.SocketEventSuscriber;
import java.util.LinkedList;
import java.util.Queue;

public abstract class Network implements SocketEventSuscriber {
    protected final Queue<Packet> pendingPackages = new LinkedList<Packet>();

    public void update(Container container) {
        /**
          * Este synchronized es para protegernos del caso borde en que se
          * agrega al a pila de paquetes un paquete al mismo tiempo que se estan sacando
          * paquetes de la pila
          */
        synchronized (pendingPackages) {
            while (!pendingPackages.isEmpty()) {
                Packet packet = pendingPackages.poll();
                if (packet == null) {
                    if (CommonConstants.DEBUG_MODE && CommonConstants.EDGE_CASES_LOG) {
                        System.out.println("Se ha detectado la recepcion para procesar de un paquete nulo. ignoraremos el error.");
                    }
                    return;
                }
                packetArrived(container, packet);
            }
        }
    }

    @Override
    public void packetReceived(Packet packet) {
        if (CommonConstants.DEBUG_MODE && CommonConstants.NETWORK_DEBUG) {
            System.out.println("Recibiendo paquete tipo " + packet.getPackageType() + " (" + packet.getDatagramPacket().getLength() + " bytes). hacia " + packet.getSender().getIpAddress().getHostAddress() + ":" + packet.getSender().getPort());
        }
        appendPacket(packet);
    }

    private void appendPacket(Packet packet) {
        /**
          * Este synchronized es para protegernos del caso borde en que se
          * agrega al a pila de paquetes un paquete al mismo tiempo que se estan sacando
          * paquetes de la pila
          */
        synchronized (pendingPackages) {
            pendingPackages.add(packet);
        }
    }

    @Override
    public void packetSended(Packet packet, Agent receiver) {
        if (CommonConstants.DEBUG_MODE && CommonConstants.NETWORK_DEBUG) {
            System.out.println("Enviando paquete tipo " + packet.getPackageType() + " (" + packet.getDatagramPacket().getLength() + " bytes). hacia " + packet.getReceiver().getIpAddress().getHostAddress() + ":" + packet.getReceiver().getPort());
        }
        // Las clases de tipo "Network" no necesitan hacer nada con los paquetes enviados :), por lo que ignoramos este packetSended, si acaso lo logueamos con System.out.println
    }

    public abstract void packetArrived(Container container, Packet packet);
}
/*
    Sobre syncronized:
    https://youtu.be/ik-QXq0L0Qc
*/
