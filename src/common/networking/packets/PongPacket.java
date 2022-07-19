package common.networking.packets;
import common.networking.PacketTypes;
import common.networking.engine.Packet;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PongPacket extends Packet {

    public PongPacket() {}

    public PongPacket(DataInputStream bufferInput) throws IOException {}

    @Override
    public void write(DataOutputStream bufferOutput) throws IOException {
        super.write(bufferOutput);
    }

    @Override
    public int getPackageType() {
        return PacketTypes.SERVER_PONG;
    }
}
