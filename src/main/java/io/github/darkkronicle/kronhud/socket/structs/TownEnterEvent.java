package io.github.darkkronicle.kronhud.socket.structs;

import com.google.gson.Gson;
import lombok.Getter;

public class TownEnterEvent extends TownData {

    public TownEnterEvent(String jsonData) {
        super(jsonData);
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
