package net.fabricmc.example.utilities;

import java.util.HashMap;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.example.NumberValue;
import net.fabricmc.example.Utility;
import net.fabricmc.example.Value;
import net.minecraft.client.MinecraftClient;

public class Flight extends Utility {
    HashMap<String, Value> settings = new HashMap<>();

    public Flight() {
        super("Flight", GLFW.GLFW_KEY_G, Category.Movement);
        this.settings.put("Speed", new NumberValue(5, 1, 10));
    }
    @Override
    public void tick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        NumberValue flySpeed = ((NumberValue) settings.get("Speed"));
        mc.player.abilities.setFlySpeed(flySpeed.get()/10);
        mc.player.abilities.flying = true;
        super.tick();
    }
    @Override
    public void onDisable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        mc.player.abilities.flying = false;
        mc.player.abilities.setFlySpeed(0.1f);

        super.onDisable();
    }
    @Override
    public HashMap<String, Value> getSettings() {
        return settings;
    }
}
