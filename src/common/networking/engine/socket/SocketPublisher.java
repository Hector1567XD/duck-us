package common.networking.engine.socket;

import common.CommonConstants;
import common.networking.engine.Packet;
import java.util.ArrayList;

public class SocketPublisher {
    private final ArrayList<SocketEventSuscriber> subscribers;

    public SocketPublisher() {
        this.subscribers = new ArrayList<>();
    }

    public void subscribe(SocketEventSuscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void unsubscribe(SocketEventSuscriber subscriber) {
        if (subscribers.contains(subscriber))
            subscribers.remove(subscriber);
    }

    public void notify(short event, Packet packet) {
        if (packet == null) {
            if (CommonConstants.DEBUG_MODE && CommonConstants.EDGE_CASES_LOG) {
                System.out.println("Se ha notificado de un paquete nulo. ignoraremos el error.");
            }
            return;
        }
        switch (event) {
            case SocketEvents.PACKET_RECEIVED_EVENT -> {
                for (SocketEventSuscriber subscriber : subscribers) {
                    subscriber.packetReceived(packet);
                }
            }
            case SocketEvents.PACKET_SENDED_EVENT -> {
                for (SocketEventSuscriber subscriber : subscribers) {
                    subscriber.packetSended(packet, packet.getReceiver());
                }
            }
        }
    }
}

/*
 * Patron Observer:
 * https://refactoring.guru/es/design-patterns/observer
 */
