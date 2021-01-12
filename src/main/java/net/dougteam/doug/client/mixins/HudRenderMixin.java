package net.dougteam.doug.client.mixins;


import com.darkmagician6.eventapi.EventManager;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.dougteam.doug.DougMod;
import net.dougteam.doug.events.Render2DEvent;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
@Mixin(InGameHud.class)
public class HudRenderMixin {
    // public void render(MatrixStack matrices, float tickDelta) {
    @Inject(at = @At("TAIL"), method = "render(Lnet/minecraft/client/util/math/MatrixStack;F)V")
    public void render(MatrixStack matrices, float tickDelta,  CallbackInfo info){
        EventManager.call(new Render2DEvent(matrices, tickDelta));
    }
}
