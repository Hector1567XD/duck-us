/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client.core;

import client.networking.Client;
import common.networking.DuckPacketReader;
import common.networking.engine.socket.SocketPublisher;

public class DuckConnect {
    public static Client createClient(SocketPublisher publisher, String ipAddress, int port) {
        Client client = new Client(publisher, new DuckPacketReader());
        client.start(ipAddress, port);
        return client;
    }
}
