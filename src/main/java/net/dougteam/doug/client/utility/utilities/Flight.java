package net.dougteam.doug.client.utility.utilities;

import java.util.HashMap;

import com.darkmagician6.eventapi.EventTarget;

import org.lwjgl.glfw.GLFW;

import net.dougteam.doug.client.utility.Utility;
import net.dougteam.doug.client.value.FloatValue;
import net.dougteam.doug.events.PreMotionUpdatesEvent;
import net.minecraft.client.MinecraftClient;

public class Flight extends Utility {

    public Flight() {
        super("Flight", GLFW.GLFW_KEY_G, Category.Movement);
        this.settings.put("Speed", new FloatValue(5, 1, 10));
    }
    @EventTarget
    public void pmue(PreMotionUpdatesEvent pmue) {
        MinecraftClient mc = MinecraftClient.getInstance();
        mc.player.abilities.flying = true;
    }
    @Override
    public void onDisable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        mc.player.abilities.flying = false;
        mc.player.abilities.setFlySpeed(0.1f);

        super.onDisable();
    }
}
