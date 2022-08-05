package common.networking;

public class PacketTypes  {
    // ENVIADOS POR EL CLIENTE [0 - 59]
    public static final short PLAYER_LOGIN_PACKET = 0;
    public static final short PLAYER_PING = 1;
    public static final short PLAYER_MOVE = 20;
    public static final short KILL_PACKET = 40;
    //public static final short CRITICAL_PACKET_SENDED = 50;

    // ENVIADOS POR EL SERVIDOR [60 - 119]
    public static final short PLAYER_JOINED_PACKET = 60;
    public static final short SERVER_PONG = 61;
    public static final short PLAYER_DISCONNECTED = 62;
    public static final short PLAYER_MOVED = 80;
    public static final short PLAYER_KILLED = 100;
    //public static final short CRITICAL_PACKET_RECEIVED = 110;
    
    // ESPECIAL PACKETS [SERVER] [120 - 125]
    public static final short GAME_INFORMATION = 120;
}

/*
    
*/