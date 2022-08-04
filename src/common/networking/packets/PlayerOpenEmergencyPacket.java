
package common.networking.packets;

import common.networking.PacketTypes;
import common.networking.engine.Packet;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class PlayerOpenEmergencyPacket extends Packet {
    private int playerId;


    public PlayerOpenEmergencyPacket(int playerId) {
         this.playerId = playerId;
    }

    public PlayerOpenEmergencyPacket(DataInputStream bufferInput) throws IOException {
        this.playerId = bufferInput.readShort();
    }

    @Override
    public void write(DataOutputStream bufferOutput) throws IOException {
        super.write(bufferOutput);
        bufferOutput.writeShort(playerId);
    }

    @Override
    public int getPackageType() {
        return PacketTypes.PLAYER_EMERGENCYOPEN;
    }

    public int getPlayerId() {
        return playerId;
    }
    
    
}
