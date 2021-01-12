package net.dougteam.doug.client.utility.utilities;

import java.util.HashMap;

import com.darkmagician6.eventapi.EventTarget;

import org.lwjgl.glfw.GLFW;

import net.dougteam.doug.client.utility.Utility;
import net.dougteam.doug.client.value.BoolValue;
import net.dougteam.doug.client.value.FloatValue;
import net.dougteam.doug.client.value.IntValue;
import net.dougteam.doug.events.PreMotionUpdatesEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;

public class Killaura extends Utility {

    public Killaura() {
        super("Killaura", GLFW.GLFW_KEY_R, Category.Combat);
        this.settings.put("Range", new FloatValue(3.4f, 1f, 10f));
        this.settings.put("MaxAngle", new IntValue(180, 1, 360));

        this.settings.put("HideSwing", new BoolValue(false));
        this.settings.put("Quiet", new BoolValue(true));

    }

    @EventTarget
    public void onPreMotionUpdatesEvent(PreMotionUpdatesEvent pmue) {
        MinecraftClient mc = MinecraftClient.getInstance();

        for (Entity e : mc.world.getEntities()) {
            if (mc.player.getAttackCooldownProgress(0f) < 1) {
                return;
            }

            if (e instanceof LivingEntity && e != mc.player) {
                if (((LivingEntity) e).hurtTime >= 0.1f) {
                    return;
                }
                LivingEntity le = (LivingEntity) e;
                if (le.hurtTime > 0) {
                    return;
                }
                // if(!le.isOnGround()) {
                // return;
                // }
                if (mc.player.distanceTo(le) <= ((FloatValue) this.settings.get("Range")).get()) {

                    float[] rotations = getRotations(le);
                    float pitch = rotations[0];
                    float yaw = rotations[1];

                    float currentYaw = MathHelper.wrapDegrees(mc.player.yaw);
                    float currentPitch = MathHelper.wrapDegrees(mc.player.pitch);

                    float diffYaw = currentYaw - yaw;
                    float diffPitch = currentPitch - pitch;

                    float angleDiff = diffYaw * diffYaw + diffPitch * diffPitch;
                    System.out.println(angleDiff % 360);
                    // if (angleDiff > ((IntValue) this.settings.get("MaxAngle")).get()) {
                    //     return;
                    // }
                    if (!((BoolValue) this.settings.get("Quiet")).get()) {
                        mc.player.pitch = pitch;
                        mc.player.yaw = yaw;
                    } else {
                        mc.player.networkHandler
                                .sendPacket(new PlayerMoveC2SPacket.LookOnly(yaw, pitch, mc.player.isOnGround()));
                    }

                    mc.interactionManager.attackEntity(mc.player, e);
                    if (!((BoolValue) this.settings.get("HideSwing")).get()) {
                        mc.player.swingHand(Hand.MAIN_HAND);
                    } else {
                        mc.player.networkHandler.sendPacket(new HandSwingC2SPacket(Hand.MAIN_HAND));
                    }
                }
            }
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }


    public static float[] getRotations(Entity le) {
        MinecraftClient mc = MinecraftClient.getInstance();
        double d = le.getPos().x - mc.player.getPos().x;
        double e2 = le.getEyeY() - mc.player.getEyeY();
        double f = le.getPos().z - mc.player.getPos().z;
        double g = (double) MathHelper.sqrt(d * d + f * f);
        float pitch = MathHelper.wrapDegrees((float) (-(MathHelper.atan2(e2, g) * 57.2957763671875D)));
        float yaw = MathHelper.wrapDegrees((float) (MathHelper.atan2(f, d) * 57.2957763671875D) - 90.0F);
        return new float[] { pitch, yaw };
    }
}
