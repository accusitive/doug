package net.dougteam.doug.client.utility;

import java.util.HashMap;

import com.darkmagician6.eventapi.EventManager;

import org.lwjgl.glfw.GLFW;

import net.dougteam.doug.client.value.Value;
import net.minecraft.client.util.math.MatrixStack;

public class Utility {
    public String name;
    public int bind;
    public Boolean state;
    public Category category;
    public HashMap<String, Value> settings;
    public enum Category {
        Combat, Render, Movement, Player
    }

    public Utility(String name, int bind, Category category) {
        this.name = name;
        this.bind = bind;
        this.state = false;
        this.category = category;
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

    public HashMap<String, Value> getSettings() {
        return settings;
    }

    public Boolean enabled() {
        return this.state;
    }

    public void onEnable() {
        EventManager.register(this);
    }

    public void onDisable() {
        EventManager.unregister(this);
    }

    public void toggle() {
        if (this.enabled()) {
            this.state = false;
            this.onDisable();
        } else {
            this.state = true;
            this.onEnable();
        }

    }

    public String getName() {
        return this.name;

    }

    public Category category() {
        return this.category;
    }

    
}
