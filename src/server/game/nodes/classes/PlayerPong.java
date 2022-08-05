package server.game.nodes.classes;

import server.game.nodes.SPlayer;

public class PlayerPong {
    private SPlayer player;
    private int framesFromLastPing;

    public PlayerPong(SPlayer player) {
        this.player = player;
        this.framesFromLastPing = 0;
    }

    public SPlayer getPlayer() {
        return player;
    }

    public int getFramesFromLastPing() {
        return framesFromLastPing;
    }

    public void addFrameFromLastPing() {
        this.framesFromLastPing++;
    }

    public void clearFramesFromLastPing() {
        this.framesFromLastPing = 0;
    }
}

