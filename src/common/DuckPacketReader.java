package common;

import static common.PacketTypes.*;
import common.packets.*;
import common.networking.Packet;
import common.networking.PacketReader;
import java.io.DataInputStream;
import java.io.IOException;

public class DuckPacketReader extends PacketReader {
    @Override
    public Packet decode(int packageType, DataInputStream bufferInput) throws IOException {
        Packet packageReaded = null;
        switch(packageType) {
            case PLAYER_LOGIN -> packageReaded = new PlayerLoginPacket(bufferInput);
            case PLAYER_JOINED_PACKET -> packageReaded = new PlayerJoinedPacket(bufferInput);
            case MOVE_PACKET -> packageReaded = new KillPacket(bufferInput);
            case KILL_PACKET -> packageReaded = new MovePacket(bufferInput);
        }
        return packageReaded;
    }
}
