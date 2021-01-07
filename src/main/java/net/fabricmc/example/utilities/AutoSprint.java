package net.fabricmc.example.utilities;


import org.lwjgl.glfw.GLFW;

import net.fabricmc.example.Utility;
import net.minecraft.client.MinecraftClient;

public class AutoSprint extends Utility {
    public AutoSprint() {
        super("Sprint", GLFW.GLFW_KEY_P);
    }
    @Override
    public void tick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        mc.player.setSprinting(true);
        super.tick();
    }
    
}
