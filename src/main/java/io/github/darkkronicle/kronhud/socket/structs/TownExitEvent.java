package io.github.darkkronicle.kronhud.socket.structs;

import com.google.gson.Gson;
import lombok.Getter;

@Getter
public class TownExitEvent extends TownData {

    public TownExitEvent(String jsonData) {
        super(jsonData);
    }


    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public TownData getData() {
        return new TownData(townName, townOwner, residents, plots);
    }


}
