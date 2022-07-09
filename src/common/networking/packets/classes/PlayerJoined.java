package common.networking.packets.classes;

public class PlayerJoined {
    private int playerId;
    private String playerName;
    private int x;
    private int y;
    
    public PlayerJoined(int playerId, String playerName, int x, int y) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.x = x;
        this.y = y;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
