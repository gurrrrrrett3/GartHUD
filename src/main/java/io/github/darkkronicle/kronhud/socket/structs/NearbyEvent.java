package io.github.darkkronicle.kronhud.socket.structs;

import lombok.Getter;
import net.minecraft.client.MinecraftClient;

@Getter
public class NearbyEvent {

    public NearbyEventPlayer[] players = new NearbyEventPlayer[0];

    public NearbyEvent(NearbyEventPlayer[] players) {
        this.players = players;
    }

    public static double getDistance(NearbyEventPlayer nearbyPlayer) {
        MinecraftClient client = MinecraftClient.getInstance();
        assert client.player != null;
        return Math.sqrt(Math.pow(nearbyPlayer.x - client.player.getX(), 2) + Math.pow(nearbyPlayer.z - client.player.getZ(), 2));
    }
}
