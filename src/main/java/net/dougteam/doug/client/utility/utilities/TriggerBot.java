package net.dougteam.doug.client.utility.utilities;

import java.util.HashMap;

import com.darkmagician6.eventapi.EventTarget;

import org.lwjgl.glfw.GLFW;

import net.dougteam.doug.client.utility.Utility;
import net.dougteam.doug.client.value.BoolValue;
import net.dougteam.doug.events.PreMotionUpdatesEvent;
import net.dougteam.doug.events.PreMotionUpdatesEvent;
import net.dougteam.doug.events.TickEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.util.Hand;

public class TriggerBot extends Utility {
    LivingEntity e;
    public TriggerBot() {
        super("TriggerBot", GLFW.GLFW_KEY_C, Category.Combat);
        this.settings.put("HideSwing", new BoolValue(false));

    }

    @Override
    public void onDisable() {
        // TODO Auto-generated method stub
        super.onDisable();
    }

    @Override
    public Boolean enabled() {
        // TODO Auto-generated method stub
        return super.enabled();
    }


    @EventTarget
    public void onPreMotionUpdatesEvent(PreMotionUpdatesEvent ev) {
        MinecraftClient mc = MinecraftClient.getInstance();
        // HitResult hr = mc.player.raycast(reach, 0, false);
        // if(hr.getType() == HitResult.Type.ENTITY) {
        //     hr.
        // }
        if(mc.targetedEntity != null) {
            this.e = (LivingEntity) mc.targetedEntity;
            if (mc.player.getAttackCooldownProgress(0f) < 1) {
                return;
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

/*
 * abstract private static bool CalcAngle(Vec3 source, Vec3 target, out Vec2
 * viewAngles) { Vec2 angles;
 * 
 * Vec3 delta = source - target; float hyp = Vec3.Distance(source, target);
 * angles.X = (float)(Math.Atan(delta.Z / hyp) * 180.0f / Math.PI); angles.Y =
 * (float)(Math.Atan(delta.Y / delta.X) * 180.0f / Math.PI);
 * 
 * if (delta.X >= 0.0f) angles.Y += 180.0f;
 * 
 * viewAngles = angles;
 * 
 * return true; }
 */