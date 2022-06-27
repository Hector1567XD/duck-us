package common.packets;

import common.PacketTypes;
import common.engine.networking.Packet;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class MovePacket extends Packet {
    private int x;
    private int y;

    public MovePacket(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public MovePacket(DataInputStream bufferInput) throws IOException {
        this.x = bufferInput.readInt();
        this.y = bufferInput.readInt();
    }

    @Override
    public void write(DataOutputStream bufferOutput) throws IOException {
        super.write(bufferOutput);
        bufferOutput.writeInt(x);
        bufferOutput.writeInt(y);
    }

    @Override
    public int getPackageType() {
        return PacketTypes.MOVE_PACKET;
    }
}
