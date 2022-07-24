package common.networking.engine;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;

public class PacketWriter {
    // Crea un DatagramPacket en base a un Packet (Nuestro propio Packet)
    public DatagramPacket write(Packet packet, Agent receptor) throws IOException {
        // Tomo al Packet y lo convirtio en un arreglo de Bytes
        byte[] byteData = writePacketInsideBytes(packet);
        // Creamos el DatagramPacket
        DatagramPacket datagramPacket = new DatagramPacket(
            byteData, byteData.length, receptor.getIpAddress(), receptor.getPort()
        );

        // Cosas extras para llevar control de los paquetes
        packet.setReceiver(receptor);
        packet.setDatagramPacket(datagramPacket);

        // Retornamos el DatagramPacket
        return datagramPacket;
    }

    // Convierte un Packet en un arreglo de bytes
    private byte[] writePacketInsideBytes(Packet packet) throws IOException {
        // Convierte un ByteArrayOutputStream en un arreglo de bytes
        ByteArrayOutputStream byteBuffer = writePacketInsideByteBuffer(packet);
        // Convierte el Buffer de Bytes en un arreglo de Bytes
        return byteBuffer.toByteArray();
    }

    // Convierte un Paket (Nuestro propio Packet) en un ByteArrayOutputStream
    private ByteArrayOutputStream writePacketInsideByteBuffer(Packet packet) throws IOException {
        // Creamos el Byte Buffer
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        // Abrimos una forma de "meter datos" dentro del ByteBuffer
        DataOutputStream bufferOutput = new DataOutputStream(byteBuffer);
        // Usamos Packet.Write() para escribir datos ordenamente dentro de el byteBuffer por medio del bufferOutput
        packet.write(bufferOutput);
        // Retornamos el Byte Buffer
        return byteBuffer;
    }
}
