package io.github.darkkronicle.kronhud.socket.structs;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TownData {
    public String townName;
    public String townOwner;
    public int residents;
    public int plots;

    public TownData(String townName, String townOwner, int residents, int plots) {
        this.townName = townName;
        this.townOwner = townOwner;
        this.residents = residents;
        this.plots = plots;
    }

    public TownData(String jsonData) {
        Gson gson = new Gson();
        TownData event = gson.fromJson(jsonData, TownData.class);
        this.townName = event.townName;
        this.townOwner = event.townOwner;
        this.residents = event.residents;
        this.plots = event.plots;
    }

}
