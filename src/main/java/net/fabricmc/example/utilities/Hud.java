package net.fabricmc.example.utilities;

import com.mojang.blaze3d.platform.GlStateManager;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.example.DougMod;
import net.fabricmc.example.Utility;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;

public class Hud extends Utility {

    public Hud() {
        super("Hud", GLFW.GLFW_KEY_MINUS);
        this.toggle();
    }

    @Override
    public void render(MatrixStack matrices, float tickDelta) {
        super.render(matrices, tickDelta);
        MinecraftClient mc = MinecraftClient.getInstance();
        TextRenderer fr = mc.inGameHud.getFontRenderer();
        int width = mc.getWindow().getScaledWidth();
        int y = 0;


        fr.drawWithShadow(matrices, "Doug Client", 1, 1, -1);
        fr.drawWithShadow(matrices, String.format("FPS: %s", mc.fpsDebugString.split(" ")[0]), 1, 10, 0x00ff00);

        // Arraylist
        for (Utility u : DougMod.client.utilities().values()) {
            String name = String.format("[%s] %s", GLFW.glfwGetKeyName(u.bind(), 0), u.name());
            int nameWidth = fr.getWidth(name);
            int color = u.enabled() ? 0x00FF00 : 0xff0000;
            fr.drawWithShadow(matrices, name, width - nameWidth, y, color);
            y += fr.fontHeight;
        }
    }

}
