package net.dougteam.doug.client.utility.utilities;

import net.dougteam.doug.client.utility.Utility;
import net.dougteam.doug.client.utils.IMinecraftClient;
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
