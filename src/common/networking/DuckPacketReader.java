package common.networking;

import common.networking.packets.PlayerJoinedPacket;
import common.networking.packets.PlayerLoginPacket;
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
            case PLAYER_LOGIN_PACKET -> packageReaded = new PlayerLoginPacket(bufferInput);
            case PLAYER_JOINED_PACKET -> packageReaded = new PlayerJoinedPacket(bufferInput);
        }
        return packageReaded;
    }
}
