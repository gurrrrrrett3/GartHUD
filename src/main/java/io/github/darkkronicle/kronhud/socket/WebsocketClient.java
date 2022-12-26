package io.github.darkkronicle.kronhud.socket;

import java.net.URI;
import java.util.Map;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.darkkronicle.kronhud.KronHUD;
import io.github.darkkronicle.kronhud.socket.structs.NearbyEvent;
import io.github.darkkronicle.kronhud.socket.structs.NearbyEventPlayer;
import io.github.darkkronicle.kronhud.socket.structs.TownEnterEvent;
import io.github.darkkronicle.kronhud.socket.structs.TownExitEvent;
import io.github.darkkronicle.kronhud.util.SocketUtil;
import net.minecraft.client.MinecraftClient;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

/**
 * This example demonstrates how to create a websocket connection to a server. Only the most
 * important callbacks are overloaded.
 */
public class WebsocketClient extends WebSocketClient {

    public WebsocketClient(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    public WebsocketClient(URI serverURI) {
        super(serverURI);
    }

    public WebsocketClient(URI serverUri, Map<String, String> httpHeaders) {
        super(serverUri, httpHeaders);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        MinecraftClient client = MinecraftClient.getInstance();
        // send some general client info to the server as a json string
        JsonObject clientInfo = new JsonObject();
        clientInfo.addProperty("username", client.getSession().getUsername());
        clientInfo.addProperty("uuid", client.getSession().getUuid());
        clientInfo.addProperty("server", SocketUtil.getServer());
        clientInfo.addProperty("timestamp", System.currentTimeMillis());
        this.send("client:ready:" + clientInfo.toString());
    }

    @Override
    public void onMessage(String message) {
        JsonObject json = JsonParser.parseString(message).getAsJsonObject();

        String type = json.get("type").getAsString();

        switch (type) {

            case "command":
                SocketUtil.runCommand(json.get("data").getAsString());
                break;
            case "chat":
                SocketUtil.sendMessage(json.get("data").getAsString());
                break;
            case "private":
                SocketUtil.showClientSideMessage(json.get("data").getAsString());
                break;
            case "town-enter":
                TownEnterEvent enterEvent = new Gson().fromJson(json.get("data"), TownEnterEvent.class);
                break;
            case "town-exit":
                TownExitEvent exitEvent = new Gson().fromJson(json.get("data"), TownExitEvent.class);
                break;
            case "nearby":
                Logger.getGlobal().info(json.get("data").getAsString());
                NearbyEventPlayer[] players = new Gson().fromJson(json.getAsJsonArray("data"), NearbyEventPlayer[].class);
                SocketManager.nearbyEventManager.setPlayers(players);
                break;
        }

    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        // The close codes are documented in class org.java_websocket.framing.CloseFrame
        System.out.println(
                "Connection closed by " + (remote ? "remote peer" : "us") + " Code: " + code + " Reason: "
                        + reason);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
        // if the error is fatal then onClose will be called additionally
    }

}
