package common.engine.networking;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;

public abstract class PacketReader {
    public Packet read(DatagramPacket packet) {
        DataInputStream bufferInput = new DataInputStream(
            new ByteArrayInputStream(packet.getData(), packet.getOffset(), packet.getLength())
        );

        try {
            int packageType = bufferInput.readByte();
            return this.decode(packageType, bufferInput);
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
