package io.github.darkkronicle.kronhud.socket;

import io.github.darkkronicle.kronhud.socket.structs.TownData;
import lombok.Getter;

@Getter
public class TownLocationManager {
    public static TownLocationManager INSTANCE = new TownLocationManager();

    public boolean inTown = false;
    public String townName = "";
    public String townOwner = "";
    public int residents = 0;
    public int plots = 0;

    public void setTown(TownData data) {
        inTown = true;
        townName = data.getTownName();
        townOwner = data.getTownOwner();
        residents = data.getResidents();
        plots = data.getPlots();
    }

    public void setNotInTown() {
        this.townName = "";
        this.townOwner = "";
        this.residents = 0;
        this.plots = 0;
        this.inTown = false;
    }

}
