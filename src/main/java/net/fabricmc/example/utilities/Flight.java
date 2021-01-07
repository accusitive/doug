package net.fabricmc.example.utilities;


import java.util.HashMap;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.example.BoolValue;
import net.fabricmc.example.Utility;
import net.fabricmc.example.Value;
import net.minecraft.client.MinecraftClient;

public class Flight extends Utility {
    public Flight() {
        super("Flight", GLFW.GLFW_KEY_G, Category.Movement);
    }
    @Override
    public void tick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        mc.player.abilities.flying = true;
        super.tick();
    }
    @Override
    public void onDisable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        mc.player.abilities.flying = false;
        super.onDisable();
    }
    @Override
    public HashMap<String, Value> getSettings() {
        HashMap<String, Value> settings = new HashMap<>();
        settings.put("auto", new BoolValue(true));
        settings.put("Omni directional", new BoolValue(false));
        settings.put("always", new BoolValue(false));

        return settings;
    }
}
