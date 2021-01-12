package net.fabricmc.example.utilities;

import java.util.HashMap;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.example.Utility;
import net.fabricmc.example.value.FloatValue;
import net.fabricmc.example.value.Value;
import net.minecraft.client.MinecraftClient;

public class Flight extends Utility {

    public Flight() {
        super("Flight", GLFW.GLFW_KEY_G, Category.Movement);
        this.settings.put("Speed", new FloatValue(5, 1, 10));
    }
    @Override
    public void tick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        mc.player.addVelocity(0, -mc.player.getVelocity().y, 0);

        super.tick();
    }
    @Override
    public void onDisable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        mc.player.abilities.flying = false;
        mc.player.abilities.setFlySpeed(0.1f);

        super.onDisable();
    }
}
