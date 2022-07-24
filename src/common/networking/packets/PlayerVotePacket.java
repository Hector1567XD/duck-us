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
public class PlayerVotePacket extends Packet {
    private int x;
    private int y;

    public PlayerVotePacket(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public PlayerVotePacket(DataInputStream bufferInput) throws IOException {
        this.x = bufferInput.readInt();
        this.y = bufferInput.readInt();
    }

    @Override
    public void write(DataOutputStream bufferOutput) throws IOException {
        super.write(bufferOutput);
        bufferOutput.writeInt(x);
        bufferOutput.writeInt(y);
    }

    @Override
    public int getPackageType() {
        return PacketTypes.PLAYER_VOTE;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
