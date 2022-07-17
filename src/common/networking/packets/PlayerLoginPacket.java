package common.networking.packets;
import common.networking.PacketTypes;
import common.networking.engine.Packet;
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

    public String getPlayerName() {
        return playerName;
    }
}