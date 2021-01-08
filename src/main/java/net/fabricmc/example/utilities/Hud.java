package net.fabricmc.example.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.example.DougMod;
import net.fabricmc.example.Utility;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

public class Hud extends Utility {
    List<Utility> sortedUtilities = new ArrayList<>();

    public Hud() {
        super("Hud", GLFW.GLFW_KEY_MINUS, Category.Render);
        this.toggle();

    }

    /*
     * Collections.sort(utils, new Comparable<Utility>(){
     * 
     * @Override public int compareTo(Utility other) { int width =
     * fr.getWidth(getName()); int otherWidth = fr.getWidth(other.getName()); return
     * width; }
     * 
     * }); }
     */
    @Override
    public void render(MatrixStack matrices, float tickDelta) {
        super.render(matrices, tickDelta);
        MinecraftClient mc = MinecraftClient.getInstance();
        TextRenderer fr = mc.inGameHud.getFontRenderer();
        int width = mc.getWindow().getScaledWidth();
        int y = 0;
        // matrices.push();
        matrices.scale(4, 4, 4);
        fr.draw(matrices, "Doug Client", 0.75f, 0.75f, 0);
        fr.draw(matrices, "Doug Client", 1, 1, -1);
        matrices.scale(0.25f, 0.25f, 0.25f);
        fr.drawWithShadow(matrices, String.format("FPS: %s", mc.fpsDebugString.split(" ")[0]), 1, fr.fontHeight * 5, 0x00ff00);
        // Arraylist
        for (Utility u : sortedUtilities) {
            String name = u.getName();
            int nameWidth = fr.getWidth(name);
            int color = u.enabled() ? 0x00FF00 : 0xff0000;
            DrawableHelper.fill(matrices, width-nameWidth, y, width, y+fr.fontHeight, 0x80000000);
            fr.drawWithShadow(matrices, name, width - nameWidth, y, color);
            y += fr.fontHeight;
        }
    }

    @Override
    public Boolean enabled() {
        ArrayList<Utility> utils = new ArrayList<Utility>(DougMod.client.utilities().values());
        MinecraftClient mc = MinecraftClient.getInstance();
        if(mc.inGameHud == null) {
            Collections.sort(utils, new Comparator<Utility>() {
                @Override
                public int compare(Utility arg0, Utility arg1) {
                    if (arg0.getName().length() < arg1.getName().length()) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
    
            });
            this.sortedUtilities = utils;
        }else {
            TextRenderer fr = mc.inGameHud.getFontRenderer();
            Collections.sort(utils, new Comparator<Utility>() {
                @Override
                public int compare(Utility arg0, Utility arg1) {
                    int width = fr.getWidth(arg0.getName());
                    int otherWidth = fr.getWidth(arg1.getName());
                    if (width < otherWidth) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
    
            });
            this.sortedUtilities = utils;
        }
        
        return super.enabled();
    }
}
