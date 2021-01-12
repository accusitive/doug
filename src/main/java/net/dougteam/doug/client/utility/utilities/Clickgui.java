package net.dougteam.doug.client.utility.utilities;

import java.util.HashMap;

import org.lwjgl.glfw.GLFW;

import net.dougteam.doug.DougMod;
import net.dougteam.doug.client.clickgui.ClickguiScreen;
import net.dougteam.doug.client.utility.Utility;
import net.dougteam.doug.client.value.BoolValue;
import net.minecraft.client.MinecraftClient;

public class Clickgui extends Utility {
    public Clickgui() {
        super("Clickgui", GLFW.GLFW_KEY_RIGHT_SHIFT, Category.Render);
        this.settings.put("Debug_Save", new BoolValue(true));
    }

    @Override
    public void onEnable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (this.settings.containsKey("Debug_Save") && ((BoolValue) this.settings.get("Debug_Save")).get()) {
            DougMod.client.setClickguiScreen(new ClickguiScreen());
        } else {
            if (DougMod.client.getClickguiScreen() == null) {
                DougMod.client.setClickguiScreen(new ClickguiScreen());
            }
        }
        mc.openScreen(DougMod.client.getClickguiScreen());
        // if(((BoolValue)this.settings.get("Debug_Save")).get()) {

        // }
        // if(DougMod.client.getClickguiScreen() == null) {
        // DougMod.client.setClickguiScreen(new ClickguiScreen());
        // }
        this.toggle();
        super.onEnable();
    }
}
