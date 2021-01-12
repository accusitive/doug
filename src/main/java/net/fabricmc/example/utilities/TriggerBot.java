package net.fabricmc.example.utilities;

import java.util.HashMap;
import org.lwjgl.glfw.GLFW;
import net.fabricmc.example.Client;
import net.fabricmc.example.Utility;
import net.fabricmc.example.value.BoolValue;
import net.fabricmc.example.value.Value;
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

    @Override
    public void render(MatrixStack matrices, float tickDelta) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (e != null) {
            DrawableHelper.fill(matrices, 4, 100, 104, 200, Client.panelColor());
            mc.textRenderer.drawWithShadow(matrices, e.getDisplayName(), 8, 100, -1);
        }
        // TODO Auto-generated method stub
        super.render(matrices, tickDelta);
    }

    @Override
    public void tick() {
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
        super.tick();
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