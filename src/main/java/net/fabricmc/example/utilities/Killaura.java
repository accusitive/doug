package net.fabricmc.example.utilities;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.example.Utility;
import net.fabricmc.example.value.BoolValue;
import net.fabricmc.example.value.NumberValue;
import net.fabricmc.example.value.Value;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket.Flag;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;

public class Killaura extends Utility {
    HashMap<String, Value> settings = new HashMap<>();

    public Killaura() {
        super("Killaura", GLFW.GLFW_KEY_R, Category.Combat);
        this.settings.put("Range", new NumberValue(3.4f, 1, 10));
        this.settings.put("HideSwing", new BoolValue(false));
        this.settings.put("Quiet", new BoolValue(true));

    }

    @Override
    public void tick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        
        for (Entity e : mc.world.getEntities()) {
            if (mc.player.getAttackCooldownProgress(0f) < 1) {
                return;
            }
            
            if (e instanceof LivingEntity && e != mc.player) {
                if(((LivingEntity)e).hurtTime >= 0.1f) {
                    return;
                }
                LivingEntity le = (LivingEntity) e;
                if(le.hurtTime > 0) {
                    return;
                }
                if (mc.player.distanceTo(le) <= ((NumberValue)this.settings.get("Range")).get()) {

                    double d = le.getPos().x - mc.player.getPos().x;
                    double e2 = le.getEyeY() - mc.player.getEyeY();
                    double f = le.getPos().z - mc.player.getPos().z;
                    double g = (double)MathHelper.sqrt(d * d + f * f);
                    float pitch = MathHelper.wrapDegrees((float)(-(MathHelper.atan2(e2, g) * 57.2957763671875D)));
                    float yaw = MathHelper.wrapDegrees((float)(MathHelper.atan2(f, d) * 57.2957763671875D) - 90.0F);
                    if(!((BoolValue)this.settings.get("Quiet")).get()) {
                        mc.player.pitch = pitch;
                        mc.player.yaw = yaw;
                    } else {
                        // Set<Flag> s = new HashSet<Flag>();
                        // s.add(Flag.X_ROT);
                        mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.LookOnly(yaw, pitch, mc.player.isOnGround()));
                    }
                    

                    mc.interactionManager.attackEntity(mc.player, e);
                    if(!((BoolValue)this.settings.get("HideSwing")).get()) {
                        mc.player.swingHand(Hand.MAIN_HAND);
                    }else {
                        mc.player.networkHandler.sendPacket(new HandSwingC2SPacket(Hand.MAIN_HAND));
                    }
                }
            }
        }
        super.tick();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public HashMap<String, Value> getSettings() {
        return settings;
    }
}
