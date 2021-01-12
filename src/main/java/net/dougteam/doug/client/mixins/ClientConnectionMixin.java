package net.dougteam.doug.client.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.dougteam.doug.DougMod;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.PacketListener;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {
   @Inject(at = @At("HEAD"), method = "handlePacket(Lnet/minecraft/network/Packet;Lnet/minecraft/network/listener/PacketListener;)V", cancellable = true)
    private static <T extends PacketListener> void handlePacket(Packet<T> packet, PacketListener listener, CallbackInfo info) {
      if(DougMod.client.packetReceive(packet)) {
         info.cancel();
      }
     }
  
     @Inject(at = @At("HEAD"), method = "send(Lnet/minecraft/network/Packet;)V", cancellable = true)
     public void send(Packet<?> packet, CallbackInfo info) {
        if(DougMod.client.packetSend(packet)) {
           info.cancel();
        }
        // this.send(packet, (GenericFutureListener)null);
     }   
}
