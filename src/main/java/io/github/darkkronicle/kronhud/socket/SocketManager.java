package io.github.darkkronicle.kronhud.socket;

import io.github.darkkronicle.kronhud.util.SocketUtil;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.Text;

import java.net.URI;

public class SocketManager {

    public static WebsocketClient ws;
    public static TownLocationManager townLocationManager = new TownLocationManager();
    public static NearbyEventManager nearbyEventManager = new NearbyEventManager();

    public static void init() {
        MinecraftClient client = MinecraftClient.getInstance();

        // run once player joins a server
        ClientPlayConnectionEvents.JOIN.register(
                ((handler, sender, client1) -> {
                    String serverIP = SocketUtil.getServer();
                    //if (serverIP.contains("craftyourtown.com") || serverIP.equals("craftyour.town")) {
                     if (true) {
                        client.getToastManager().add(new SystemToast(SystemToast.Type.TUTORIAL_HINT, Text.of("CYT Update Mod"), Text.of("Connecting to data server...")));

                        boolean res = connectToDataServer();

                        if (res) {
                            client.getToastManager().add(new SystemToast(SystemToast.Type.TUTORIAL_HINT, Text.of("CYT Update Mod"), Text.of("Connected to data server!")));
                        } else {
                            client.getToastManager().add(new SystemToast(SystemToast.Type.TUTORIAL_HINT, Text.of("CYT Update Mod"), Text.of("Failed to connect to data server.")));
                        }
                    }
                }));

        // run once player disconnects
        ClientPlayConnectionEvents.DISCONNECT.register(
                ((handler, sender) -> {
                    if (ws != null) {
                        ws.close();
                    }
                }));
    }

    public static boolean connectToDataServer() {
        try {
            //ws = new WebsocketClient(new URI("ws://95.216.205.34:3001/"));
            ws = new WebsocketClient(new URI("ws://localhost:3001/"));
            ws.connect();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
