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
    private int selfPlayerId = 0;

    public GameInformationPacket(ArrayList<PlayerJoined> players, int selfPlayerId) {
        this.players = players;
        this.selfPlayerId = selfPlayerId;
    }

    public GameInformationPacket(DataInputStream bufferInput) throws IOException {
        this.players = new ArrayList<PlayerJoined>();
        // Leemos el playerId de tu propio jugador
        this.selfPlayerId = bufferInput.readShort();
        // Leemos el arraylist de jugadores
        int arraySize = bufferInput.readByte();
        for (int i = 0; i < arraySize; i++) {
            int playerId = bufferInput.readShort();
            String playerName = bufferInput.readUTF();
            int posX = bufferInput.readInt();
            int posY = bufferInput.readInt();
            boolean isDead = bufferInput.readBoolean();
            PlayerJoined playerJoined = new PlayerJoined(playerId, playerName, posX, posY, isDead);
            players.add(playerJoined);
        }
    }

    @Override
    public void write(DataOutputStream bufferOutput) throws IOException {
        super.write(bufferOutput);
        // Le envias al jugador su propio ID de jugador
        bufferOutput.writeShort(selfPlayerId);
        // La cantidad de jugadores que habran y por lo tanto la cantidad de datos que hay que leer
        bufferOutput.writeByte(players.size());
        for (PlayerJoined player: players) {
            bufferOutput.writeShort(player.getPlayerId());
            bufferOutput.writeUTF(player.getPlayerName());
            bufferOutput.writeInt(player.getX());
            bufferOutput.writeInt(player.getY());
            bufferOutput.writeBoolean(player.isDead());
        }
    }

    @Override
    public int getPackageType() {
        return PacketTypes.GAME_INFORMATION;
    }

    public ArrayList<PlayerJoined> getPlayers() {
        return players;
    }

    /*
        Te da el playerId de tu propio jugador, por que cuando entras a la
        partida no tienes forma de saber cual es tu propio playerId en el servidor
        hasta que el servidor te lo dice
    */
    public int getSelfPlayerId() {
        return selfPlayerId;
    }
}
