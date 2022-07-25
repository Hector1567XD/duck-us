package common.networking.engine;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;

public abstract class PacketReader {
    public Packet read(DatagramPacket datagramPacket) {
        DataInputStream bufferInput = new DataInputStream(
            new ByteArrayInputStream(datagramPacket.getData(), datagramPacket.getOffset(), datagramPacket.getLength())
        );

        try {
            int packageType = bufferInput.readByte();
            Packet packet = this.decode(packageType, bufferInput);

            // Cosas extras para llevar control de los paquetes
            packet.setDatagramPacket(datagramPacket);
            Agent agent = new Agent(datagramPacket.getAddress(), datagramPacket.getPort());
            packet.setSender(agent);

            return packet;
        } catch (IOException e) {
            // Ocurrio un error al leer el paquete recibido por el servidor
            // TODO: Matar juego y dar aviso de error
            e.printStackTrace();
        }

        return null;
    }

    public abstract Packet decode(int packageType, DataInputStream bufferInput) throws IOException;
}

// TODO: Change to UnsignedByte instead Byte
