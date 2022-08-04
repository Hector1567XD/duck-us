/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.networking.packets;

import common.networking.PacketTypes;
import common.networking.engine.Packet;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author Ortiz pc
 */
public class PlayerEmergencyPacket extends Packet {


    public PlayerEmergencyPacket() {

    }

     public PlayerEmergencyPacket(DataInputStream bufferInput) {

    }   
        
    @Override
    public void write(DataOutputStream bufferOutput) throws IOException {
    }

    @Override
    public int getPackageType() {
        return PacketTypes.PLAYER_EMERGENCY;
    }

}
