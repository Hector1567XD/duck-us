package common.networking.packets;

import common.networking.PacketTypes;
import common.networking.engine.Packet;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PlayerMovePacket extends Packet {
    private int x;
    private int y;

    public PlayerMovePacket(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public PlayerMovePacket(DataInputStream bufferInput) throws IOException {
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
        return PacketTypes.PLAYER_MOVE;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
