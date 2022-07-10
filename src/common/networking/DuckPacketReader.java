package common.networking;

import common.networking.packets.*;
import static common.networking.PacketTypes.*;
import common.networking.engine.Packet;
import common.networking.engine.PacketReader;
import java.io.DataInputStream;
import java.io.IOException;

public class DuckPacketReader extends PacketReader {
    @Override
    public Packet decode(int packageType, DataInputStream bufferInput) throws IOException {
        Packet packageReaded = null;
        switch(packageType) {
            // Player Join
            case PLAYER_LOGIN_PACKET -> packageReaded = new PlayerLoginPacket(bufferInput);
            case PLAYER_JOINED_PACKET -> packageReaded = new PlayerJoinedPacket(bufferInput);
            // Player Ping / Server Pong
            case PLAYER_PING -> packageReaded = new PingPacket(bufferInput);
            case SERVER_PONG -> packageReaded = new PongPacket(bufferInput);
            // Player Move
            case PLAYER_MOVE -> packageReaded = new PlayerMovePacket(bufferInput);
            case PLAYER_MOVED -> packageReaded = new PlayerMovedPacket(bufferInput);
            case GAME_INFORMATION -> packageReaded = new GameInformationPacket(bufferInput);
        }
        return packageReaded;
    }
}
