package net.fabricmc.example;

import java.util.HashMap;

import net.fabricmc.example.utilities.AutoSprint;

public class Client {
    String name;
    String version;
    HashMap<String, Utility> utilities;

    public Client() {
        this.utilities = new HashMap<>();
        this.utilities.put("sprint", new AutoSprint());

        for(Utility u : this.utilities.values()) {
            u.init();
        }
    }
}
