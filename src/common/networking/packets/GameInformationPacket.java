package common.networking.packets;
import common.networking.PacketTypes;
import common.networking.engine.Packet;
import common.networking.packets.classes.PlayerJoined;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class GameInformationPacket extends Packet {
    private ArrayList<PlayerJoined> players;

    public GameInformationPacket(ArrayList<PlayerJoined> players) {
        this.players = players;
    }

    public GameInformationPacket(DataInputStream bufferInput) throws IOException {
        this.players = new ArrayList<PlayerJoined>();
        int arraySize = bufferInput.readByte();
        for (int i = 0; i < arraySize; i++) {
            int playerId = bufferInput.readShort();
            String playerName = bufferInput.readUTF();
            int posX = bufferInput.readInt();
            int posY = bufferInput.readInt();
            PlayerJoined playerJoined = new PlayerJoined(playerId, playerName, posX, posY);
            players.add(playerJoined);
        }
    }

    @Override
    public void write(DataOutputStream bufferOutput) throws IOException {
        super.write(bufferOutput);
        // La cantidad de jugadores que habran y por lo tanto la cantidad de datos que hay que leer
        bufferOutput.writeByte(players.size());
        for (PlayerJoined player: players) {
            bufferOutput.writeShort(player.getPlayerId());
            bufferOutput.writeUTF(player.getPlayerName());
            bufferOutput.writeInt(player.getX());
            bufferOutput.writeInt(player.getY());
        }
    }

    @Override
    public int getPackageType() {
        return PacketTypes.GAME_INFORMATION;
    }

    public ArrayList<PlayerJoined> getPlayers() {
        return players;
    }
}
