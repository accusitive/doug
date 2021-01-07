package net.fabricmc.example;

import java.util.HashMap;

import net.minecraft.client.util.math.MatrixStack;

public class Utility {
    String name;
    int bind;
    Boolean state;
    HashMap<String, Value<?>> settings;

    public Utility(String name, int bind) {
        this.name = name;
        this.bind = bind;
        this.state = false;
        this.settings = new HashMap<>();
    }

    public void init() {

    }

    public String name() {
        return this.name;
    }

    public int bind() {
        return this.bind;
    }

    public HashMap<String, Value<?>> getSettings() {
        return this.settings;

    }

    public Boolean enabled() {
        return this.state;
    }

    public void onEnable() {

    }

    public void onDisable() {

    }

    public void toggle() {
        System.out.printf("Module %s toggled.", this.name);
        if (this.enabled()) {
            this.state = false;
            this.onDisable();
        } else {
            this.state = true;
            this.onEnable();
        }

    }

	public void render(MatrixStack matrices, float tickDelta) {
    }
    public void tick(){
        
    }
}
