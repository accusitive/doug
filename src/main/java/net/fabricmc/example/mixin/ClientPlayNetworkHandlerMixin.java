package net.fabricmc.example.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.fabricmc.example.DougMod;
import net.fabricmc.example.utilities.Velocity;
import net.fabricmc.example.value.ModeValue;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    // public void onVelocityUpdate(EntityVelocityUpdateS2CPacket packet) {}
    @Inject(at=@At("HEAD"), cancellable = true, method = "onVelocityUpdate(Lnet/minecraft/network/packet/s2c/play/EntityVelocityUpdateS2CPacket;)V")
    public void onVelocityUpdate(EntityVelocityUpdateS2CPacket packet, CallbackInfo info){
        Velocity v = (Velocity) DougMod.client.utilities().get("velocity");
        if(v.enabled() && ((ModeValue) v.getSettings().get("Mode")).current() == "Packet") {
            info.cancel();
        }
    }
}
