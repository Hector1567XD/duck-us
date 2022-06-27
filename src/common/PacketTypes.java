package common;

public class PacketTypes  {
    // ENVIADOS POR EL CLIENTE [0 - 60]
    public static final short PLAYER_LOGIN = 0;
    public static final short MOVE_PACKET = 20;
    public static final short KILL_PACKET = 40;
    public static final short CRITICAL_PACKET_SENDED = 50;

    // ENVIADOS POR EL SERVIDOR [60 - 120]
    public static final short PLAYER_JOINED_PACKET = 60;
    public static final short PLAYER_MOVED = 80;
    public static final short PLAYER_KILLED = 100;
    public static final short CRITICAL_PACKET_RECEIVED = 110;
}

/*
    
*/