package common;

import static common.PacketTypes.*;
import common.packets.*;
import common.engine.networking.Packet;
import common.engine.networking.PacketDecoderInterface;
import java.io.DataInputStream;
import java.io.IOException;

public class PacketDecoder implements PacketDecoderInterface {
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
