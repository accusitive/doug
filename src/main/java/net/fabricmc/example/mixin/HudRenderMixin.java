package net.fabricmc.example.mixin;


import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.fabricmc.example.DougMod;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
@Mixin(InGameHud.class)
public class HudRenderMixin {
    // public void render(MatrixStack matrices, float tickDelta) {
    @Inject(at = @At("TAIL"), method = "render(Lnet/minecraft/client/util/math/MatrixStack;F)V")
    public void render(MatrixStack matrices, float tickDelta,  CallbackInfo info){
        // MinecraftClient mc = MinecraftClient.getInstance();
        DougMod.client.render(matrices, tickDelta);
        // mc.inGameHud.getFontRenderer().drawWithShadow(matrices, "Doug2", 1, 1, -1);
        // MinecraftClient.getInstance().inGameHud.getFontRenderer().drawWithShadow(matrices, "Doug", 1, 1, -1);
    }
    // public void render2(MatrixStack matrices, float tickDelta){

    // }
}
