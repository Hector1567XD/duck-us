package common.packets;
import common.PacketTypes;
import common.engine.networking.Packet;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PlayerJoinedPacket extends Packet {
    private int playerId;
    private String playerName;

    public PlayerJoinedPacket(int playerId, String playerName) {
        this.playerId = playerId;
        this.playerName = playerName;
    }

    public PlayerJoinedPacket(DataInputStream bufferInput) throws IOException {
        this.playerId = bufferInput.readShort();
    }

    @Override
    public void write(DataOutputStream bufferOutput) throws IOException {
        super.write(bufferOutput);
        bufferOutput.writeShort(playerId);
    }

    @Override
    public int getPackageType() {
        return PacketTypes.PLAYER_JOINED_PACKET;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }
}
