package io.github.darkkronicle.kronhud.socket.structs;

import lombok.Getter;
import net.minecraft.client.MinecraftClient;

@Getter
public class NearbyEventPlayer {

    public String name;
    public double x;
    public double z;

    public NearbyEventPlayer(String name, float x, float z) {
        this.name = name;
        this.x = x;
        this.z = z;
    }

    public double getDistance() {
        MinecraftClient client = MinecraftClient.getInstance();
        assert client.player != null;
        return Math.sqrt(Math.pow(x - client.player.getX(), 2) + Math.pow(z - client.player.getZ(), 2));
    }
}
