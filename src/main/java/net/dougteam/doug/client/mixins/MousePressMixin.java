package net.dougteam.doug.client.mixins;


import com.darkmagician6.eventapi.EventManager;

import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.dougteam.doug.events.MouseEvent;
import net.minecraft.client.Mouse;
@Mixin(Mouse.class)
public class MousePressMixin {
    /* public void onKey(long window, int key, int scancode, int i, int j) */
    @Inject(at = @At("HEAD"), method = "onMouseButton(JIII)V")
    public void onMouseButton(long window, int key, int action, int mods, CallbackInfo info){
        if(action == GLFW.GLFW_PRESS) {
            EventManager.call(new MouseEvent(key, action));
        }
    }
}
