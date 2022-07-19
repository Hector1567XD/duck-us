package common.networking.packets;

import common.networking.PacketTypes;
import common.networking.engine.Packet;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PlayerMovedPacket extends Packet {
    private int playerId;
    private int x;
    private int y;

    public PlayerMovedPacket(int playerId, int x, int y) {
        this.playerId = playerId;
        this.x = x;
        this.y = y;
    }

    public PlayerMovedPacket(DataInputStream bufferInput) throws IOException {
        this.playerId = bufferInput.readShort();
        this.x = bufferInput.readInt();
        this.y = bufferInput.readInt();
    }

    @Override
    public void write(DataOutputStream bufferOutput) throws IOException {
        super.write(bufferOutput);
        bufferOutput.writeShort(playerId);
        bufferOutput.writeInt(x);
        bufferOutput.writeInt(y);
    }

    @Override
    public int getPackageType() {
        return PacketTypes.PLAYER_MOVED;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
