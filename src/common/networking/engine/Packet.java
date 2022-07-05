package common.networking.engine;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;

public abstract class Packet {
    private Agent sender = null;
    private Agent receiver = null;
    private DatagramPacket datagramPacket = null;

    public void write(DataOutputStream bufferOutput) throws IOException {
        bufferOutput.writeByte(this.getPackageType());
    }
    public abstract int getPackageType();

    public Agent getSender() {
        return sender;
    }

    public void setSender(Agent sender) {
        this.sender = sender;
    }

    public Agent getReceiver() {
        return receiver;
    }

    public void setReceiver(Agent receiver) {
        this.receiver = receiver;
    }

    public DatagramPacket getDatagramPacket() {
        return datagramPacket;
    }

    public void setDatagramPacket(DatagramPacket datagramPacket) {
        this.datagramPacket = datagramPacket;
    }
}
