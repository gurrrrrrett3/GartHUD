package io.github.darkkronicle.kronhud.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class SocketUtil {

    public static void runCommand(String command) {
        try {
            MinecraftClient.getInstance().player.sendCommand(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showClientSideMessage(String text) {
        try {
            assert MinecraftClient.getInstance().player != null;
            MinecraftClient.getInstance().player.sendMessage(Text.of(text));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendMessage(String text) {
        try {
            assert MinecraftClient.getInstance().player != null;
            MinecraftClient.getInstance().player.sendChatMessage(text, Text.of(text));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * stolen from <a href="https://github.com/DarkKronicle/AdvancedChatCore/blob/main/src/main/java/io/github/darkkronicle/advancedchatcore/AdvancedChatCore.java#L109-L122">AdvancedChatCore</a>
     * Returns the server address that the client is currently connected to.
     *
     * @return The server address if connected, 'singleplayer' if singleplayer, 'none' if none.
     */
    public static String getServer() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.isInSingleplayer()) {
            return "singleplayer";
        }
        if (client.getCurrentServerEntry() == null) {
            return "none";
        }
        return client.getCurrentServerEntry().address;
    }


}
