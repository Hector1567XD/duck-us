package common.engine.networking;

import java.io.DataOutputStream;
import java.io.IOException;

public abstract class Packet {
    public void write(DataOutputStream bufferOutput) throws IOException {
        bufferOutput.writeByte(this.getPackageType());
    }
    public abstract int getPackageType();
}
