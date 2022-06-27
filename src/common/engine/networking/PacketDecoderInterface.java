package common.engine.networking;

import java.io.DataInputStream;
import java.io.IOException;

public interface PacketDecoderInterface {
    public Packet decode(int packageType, DataInputStream bufferInput) throws IOException;
}
