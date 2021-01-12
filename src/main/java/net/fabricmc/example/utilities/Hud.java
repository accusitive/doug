package net.fabricmc.example.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;


import org.lwjgl.glfw.GLFW;

import net.fabricmc.example.Client;
import net.fabricmc.example.DougMod;
import net.fabricmc.example.RenderUtils;
import net.fabricmc.example.Utility;
import net.fabricmc.example.value.BoolValue;
import net.fabricmc.example.value.Value;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

public class Hud extends Utility {
    List<Utility> sortedUtilities = new ArrayList<>();
    // HashMap<String, Value> settings = new HashMap<>();

    public Hud() {
        super("Hud", GLFW.GLFW_KEY_MINUS, Category.Render);
        // this.toggle();
        this.settings.put("ShowFps", new BoolValue(true));
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
        // int height = mc.getWindow().getScaledHeight();
        int y = 0;
        RenderUtils.drawBorderedRectHori(matrices, 4, 4, 90, 20, 2, Client.panelColor(), -1);
        DrawableHelper.drawCenteredString(matrices, fr, "Doug b1", 45, 8, Client.mainColor());
        if (((BoolValue) this.settings.get("ShowFps")).get()) {
            fr.drawWithShadow(matrices, String.format("FPS: %s", mc.fpsDebugString.split(" ")[0]), 1, fr.fontHeight * 5,
                    Client.mainColor());
        }
        // Arraylist
        int lastNameWidth = 0;
        for (Utility u : sortedUtilities) {
            if(u.enabled()) {
                String name = u.getName();
                int nameWidth = fr.getWidth(name);
                if(lastNameWidth == 0) {
                    //this is to fix the top module having a long white line
                    lastNameWidth = nameWidth;
                }
                int color = Client.mainColor();
                DrawableHelper.fill(matrices, width - nameWidth - 2, y, width, y + fr.fontHeight + 2, 0x80000000);
                DrawableHelper.fill(matrices, width-nameWidth-3, y, width-nameWidth-2, y + fr.fontHeight + 2, Client.mainColor());

                DrawableHelper.fill(matrices, width-nameWidth-3, y, width-(lastNameWidth)-3, y + 1, Client.mainColor());

                fr.drawWithShadow(matrices, name, width - nameWidth, y + 1, color);
                y += fr.fontHeight + 2;
                lastNameWidth = nameWidth;
            }
        }
        DrawableHelper.fill(matrices, width-lastNameWidth-3, y, width, y+1, Client.mainColor());
        // mc.getTextureManager().bindTexture(TitleScreen.OPTIONS_BACKGROUND_TEXTURE);
        // GlStateManager.texParameter(3553, 10241, 9729);
        // GlStateManager.texParameter(3553, 10240, 9729);
        // GL11C.glCopyTexSubImage2D(target, level, xoffset, yoffset, x, y, width,
        // height);

        // int i = GlStateManager.getFramebufferDepthAttachment();

        // GlStateManager.bindTexture(i);
        // GlStateManager.bindFramebuffer(FramebufferInfo.FRAME_BUFFER,
        // mc.getFramebuffer().fbo);
        // GlStateManager.bindFramebuffer(FramebufferInfo.FRAME_BUFFER,
        // mc.getFramebuffer().fbo);

        // GlStateManager.copyTexSubImage2d(3553, 0, 0, 0, 0, 0, 256, 256);
        // RenderSystem.defaultBlendFunc();
        // RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.SRC_ALPHA,
        // GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA,
        // GlStateManager.SrcFactor.ONE, GlStateManager.DstFactor.ZERO);

        // GlStateManager.colorMask(true, true, true, false);

        // Tessellator tessellator = Tessellator.getInstance();
        // BufferBuilder bufferbuilder = tessellator.getBuffer();
        // RenderSystem.enableBlend();
        // RenderSystem.disableTexture();
        // bufferbuilder.begin(7, VertexFormats.POSITION_COLOR);
        // float x1 = 100;
        // float y1 = 100;
        // float x2 = 200;
        // float y2 = 200;
        // // int i = 3;
        // // for (int j = 0; j < 3; ++j)
        // // {
        // // float f = 1.0F / (float)(j + 1);
        // // int k = width;
        // // int l = height;
        // // float f1 = (float)(j - 1) / 256.0F;
        // float zLevel = 2;
        // bufferbuilder.vertex(matrices.peek().getModel(), x1, y2, zLevel).color(1.0F,
        // 1.0F, 1.0F, 1f).next();
        // bufferbuilder.vertex(matrices.peek().getModel(), x2, y2, zLevel).color(1.0F,
        // 1.0F, 1.0F, 1f).next();
        // bufferbuilder.vertex(matrices.peek().getModel(), x2, y1, zLevel).color(1.0F,
        // 1.0F, 1.0F, 1f).next();
        // bufferbuilder.vertex(matrices.peek().getModel(), x1, y1, zLevel).color(1.0F,
        // 1.0F, 1.0F, 1f).next();

        // // bufferbuilder.
        // // bufferbuilder.vertex((float)k, (float)l,
        // (float)zLevel).texture((float)(0.0F + f1), 1.0F).color(1.0F, 1.0F, 1.0F,
        // f).next();
        // // bufferbuilder.vertex((float)k, 0.0F, (float)zLevel).texture((float)(1.0F +
        // f1), 1.0F).color(1.0F, 1.0F, 1.0F, f).next();
        // // bufferbuilder.vertex(0.0F, 0.0F, (float)zLevel).texture((float)(1.0F +
        // f1), 0.0F).color(1.0F, 1.0F, 1.0F, f).next();
        // // bufferbuilder.vertex(0.0F, (float)l, (float)zLevel).texture((float)(0.0F +
        // f1), 0.0F).color(1.0F, 1.0F, 1.0F, f).next();
        // // }
        // // BufferRenderer.draw(bufferBuilder);
        // bufferbuilder.end();
        // GlStateManager.colorMask(true, true, true, true);
        // RenderSystem.disableBlend();
        // RenderSystem.enableTexture();
        // BufferRenderer.draw(bufferbuilder);
        // // GlStateManager.bindTexture(j);

        // tessellator.draw();
    }

    @Override
    public Boolean enabled() {
        ArrayList<Utility> utils = new ArrayList<Utility>(DougMod.client.utilities().values());
        MinecraftClient mc = MinecraftClient.getInstance();
        TextRenderer fr = mc.textRenderer;
        Collections.sort(utils, new Comparator<Utility>() {
            @Override
            public int compare(Utility arg0, Utility arg1) {
                int width = fr.getWidth(arg0.getName());
                int otherWidth = fr.getWidth(arg1.getName());
                // int width = arg0.getName().length();
                // int otherWidth = arg1.getName().length();
                if (width < otherWidth) {
                    return 1;
                } else {
                    return -1;
                }
            }

        });
        this.sortedUtilities = utils;
        return super.enabled();
    }

}
