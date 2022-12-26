package io.github.darkkronicle.kronhud.socket;

import io.github.darkkronicle.kronhud.socket.structs.NearbyEventPlayer;

public class NearbyEventManager {

    public NearbyEventPlayer[] players;

    public NearbyEventManager() {
        players = new NearbyEventPlayer[0];
    }

    public void setPlayers(NearbyEventPlayer[] players) {
        this.players = players;
    }

    public NearbyEventPlayer[] getPlayers() {
        return players;
    }
}
