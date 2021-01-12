package net.dougteam.doug.client.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.dougteam.doug.DougMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
    @Inject(at=@At("HEAD"), method="tick()V")
    public void onPreMotionUpdate(CallbackInfo info){
        //      if (this.world.isChunkLoaded(new BlockPos(this.getX(), 0.0D, this.getZ()))) {
            MinecraftClient mc = MinecraftClient.getInstance();
            if(mc.world.isChunkLoaded(mc.player.getBlockPos())){
                DougMod.client.onPreMotionUpdate();
            }
    }

    @Inject(at=@At("TAIL"), method="tick()V")
    public void onPostMotionUpdate(CallbackInfo info){
        //      if (this.world.isChunkLoaded(new BlockPos(this.getX(), 0.0D, this.getZ()))) {
            MinecraftClient mc = MinecraftClient.getInstance();
            if(mc.world.isChunkLoaded(mc.player.getBlockPos())){
                DougMod.client.onPostMotionUpdate();
            }
    }
} 
