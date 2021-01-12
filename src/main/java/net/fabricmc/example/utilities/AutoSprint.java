package net.fabricmc.example.utilities;

import java.util.HashMap;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.example.Utility;
import net.fabricmc.example.value.BoolValue;
import net.fabricmc.example.value.Value;
import net.minecraft.client.MinecraftClient;

public class AutoSprint extends Utility {

    public AutoSprint() {
        super("Sprint", GLFW.GLFW_KEY_P, Category.Movement);

        this.settings.put("Omni", new BoolValue(false));
        this.settings.put("Always", new BoolValue(false));
    }

    @Override
    public void onPreMotionUpdate() {
        MinecraftClient mc = MinecraftClient.getInstance();
        boolean omni = ((BoolValue) this.getSettings().get("Omni")).get();
        if (!omni) {
            if (mc.player.forwardSpeed > 0.0f) {
                mc.player.setSprinting(true);
            }
        } else {
            mc.player.setSprinting(true);
        }
        super.tick();
    }

}
