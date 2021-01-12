package net.dougteam.doug.client.mixins;


import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.dougteam.doug.DougMod;
import net.minecraft.client.Keyboard;
@Mixin(Keyboard.class)
public class KeyPressMixin {
    /* public void onKey(long window, int key, int scancode, int i, int j) */
    @Inject(at = @At("HEAD"), method = "onKey(JIIII)I")
    public void onKey(long window, int key, int scancode, int action, int mods, CallbackInfo info){
        if(action == GLFW.GLFW_PRESS) {
            DougMod.client.keyPress(key);
        }
    }
}
