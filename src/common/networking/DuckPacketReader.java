package common.networking;

import common.networking.packets.KillPacket;
import common.networking.packets.PlayerJoinedPacket;
import common.networking.packets.PlayerLoginPacket;
import common.networking.packets.MovePacket;
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
            case PLAYER_LOGIN -> packageReaded = new PlayerLoginPacket(bufferInput);
            case PLAYER_JOINED_PACKET -> packageReaded = new PlayerJoinedPacket(bufferInput);
            case MOVE_PACKET -> packageReaded = new MovePacket(bufferInput);
            case KILL_PACKET -> packageReaded = new KillPacket(bufferInput);
        }
        return packageReaded;
    }
}
