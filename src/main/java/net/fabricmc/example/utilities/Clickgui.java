package net.fabricmc.example.utilities;


import org.lwjgl.glfw.GLFW;

import net.fabricmc.example.Utility;
import net.fabricmc.example.clickgui.ClickguiScreen;
import net.minecraft.client.MinecraftClient;

public class Clickgui extends Utility {
    public Clickgui() {
        super("Clickgui", GLFW.GLFW_KEY_RIGHT_SHIFT, Category.Render);
    }

    @Override
    public void onEnable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        mc.openScreen(new ClickguiScreen());
        super.onEnable();
    }
}
