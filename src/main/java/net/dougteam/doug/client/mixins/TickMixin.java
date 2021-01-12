
package net.dougteam.doug.client.mixins;

import net.dougteam.doug.DougMod;
import net.minecraft.client.network.ClientPlayerEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class TickMixin {
	@Inject(at = @At("HEAD"), method= "tick()V")
	public void tick(CallbackInfo info) {
		DougMod.client.tick();
	}
}