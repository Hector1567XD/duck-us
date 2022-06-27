package common.packets;
import common.PacketTypes;
import common.engine.networking.Packet;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PlayerLoginPacket extends Packet {
    private String playerName;

    public PlayerLoginPacket(String playerName) {
        this.playerName = playerName;
    }

    public PlayerLoginPacket(DataInputStream bufferInput) throws IOException {
        this.playerName = bufferInput.readUTF();
    }

    @Override
    public void write(DataOutputStream bufferOutput) throws IOException {
        super.write(bufferOutput);
        bufferOutput.writeUTF(playerName);
    }

    @Override
    public int getPackageType() {
        return PacketTypes.PLAYER_LOGIN;
    }
}
