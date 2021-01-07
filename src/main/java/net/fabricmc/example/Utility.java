package net.fabricmc.example;

import java.util.HashMap;

public class Utility {
    String name;
    int bind;
    Boolean enabled;

    public Utility(String name, int bind) {
        this.name = name;
        this.bind = bind;
    }

    public void init() {

    }

    public String name() {
        return this.name;
    }

    public int bind() {
        return this.bind;
    }

    public Boolean enabled() {
        return this.enabled;
    }

    public void onEnable() {

    }

    public void onDisable() {

    }

    public void toggle() {
        if (this.enabled) {
            this.enabled = false;
            this.onDisable();
        } else {
            this.enabled = true;
            this.onEnable();
        }

    }
}
