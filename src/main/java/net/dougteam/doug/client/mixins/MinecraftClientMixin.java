package net.dougteam.doug.client.mixins;

import net.dougteam.doug.client.utils.IMinecraftClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Session;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin implements IMinecraftClient{
	@Shadow
	private Session session;

	@Override
	public void setSession(Session s) {
		this.session = s;
	}
}
