package common.networking;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;

public class PacketWriter {
    // Crea un DatagramPacket en base a un Packet (Nuestro propio Packet)
    public DatagramPacket write(Packet packet, Agent receptor) throws IOException {
        byte[] byteData = writePacketInsideBytes(packet);
        return new DatagramPacket(
            byteData, byteData.length, receptor.getIpAddress(), receptor.getPort()
        );
    }

    // Convierte un ByteArrayOutputStream en un arreglo de bytes
    private byte[] writePacketInsideBytes(Packet packet) throws IOException {
        ByteArrayOutputStream byteBuffer = writePacketInsideByteBuffer(packet);
        // Convierte el Buffer de Bytes en un arreglo de Bytes
        return byteBuffer.toByteArray();
    }

    // Convierte un Paket (Nuestro propio Packet) en un ByteArrayOutputStream
    private ByteArrayOutputStream writePacketInsideByteBuffer(Packet packet) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        DataOutputStream bufferOutput = new DataOutputStream(byteBuffer);
        packet.write(bufferOutput);
        return byteBuffer;
    }
}
