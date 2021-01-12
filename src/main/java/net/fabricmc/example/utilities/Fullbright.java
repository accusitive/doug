package net.fabricmc.example.utilities;

import net.fabricmc.example.IMinecraftClient;
import net.fabricmc.example.Utility;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Session;

public class Fullbright extends Utility {

    public Fullbright() {
        super("Fullbright", 0, Category.Render);

    }
    @Override
    public void onEnable() {
        ((IMinecraftClient) MinecraftClient.getInstance()).setSession(new Session("XAA", "", "", "mojang"));
        MinecraftClient mc = MinecraftClient.getInstance();
        mc.options.gamma = 100;
        super.onEnable();
    }
}
