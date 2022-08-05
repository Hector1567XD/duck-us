package common.networking.packets;

import common.networking.PacketTypes;
import common.networking.engine.Packet;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PlayerKillPacket extends Packet {
    private int playerId;

    public PlayerKillPacket(int playerId) {
        this.playerId = playerId;
    }

    public PlayerKillPacket(DataInputStream bufferInput) throws IOException {
        this.playerId = bufferInput.readShort();

    }

    @Override
    public void write(DataOutputStream bufferOutput) throws IOException {
        super.write(bufferOutput);
        bufferOutput.writeShort(playerId);
    }

    @Override
    public int getPackageType() {
        return PacketTypes.KILL_PACKET;
    }

    public int getPlayerId() {
        return playerId;
    }

}
