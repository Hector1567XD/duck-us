package common.networking.packets.classes;

public class PlayerJoined {
    private int playerId;
    private String playerName;
    private int x;
    private int y;
    private boolean dead;
    
    public PlayerJoined(int playerId, String playerName, int x, int y, boolean dead) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.x = x;
        this.y = y;
        this.dead = dead;
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

    public boolean isDead() {
        return dead;
    }
}
