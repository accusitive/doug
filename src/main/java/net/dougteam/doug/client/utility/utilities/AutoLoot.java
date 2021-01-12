package net.dougteam.doug.client.utility.utilities;

import java.util.HashMap;

import org.lwjgl.glfw.GLFW;
// import org.lwjgl.util.vector.Matrix3f;
// import org.lwjgl.util.vector.Vector2f;
// import org.lwjgl.util.vector.Vector3f;

import net.dougteam.doug.client.utility.Utility;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.ClickSlotC2SPacket;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;

public class AutoLoot extends Utility {

    public AutoLoot() {
        super("AutoLoot", GLFW.GLFW_KEY_GRAVE_ACCENT, Category.Player);

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
        // TODO Auto-generated method stub
        super.render(matrices, tickDelta);
    }

    @Override
    public void tick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.currentScreen instanceof GenericContainerScreen) {
            GenericContainerScreen gcs = (GenericContainerScreen) mc.currentScreen;
            for (Slot s : gcs.getScreenHandler().slots) {
                System.out.println(s.id);
                if(mc.player.age % 10 == s.id % 10) {
                if (s.id <= 27) {
                    if (s.getStack().getItem().isDamageable() || s.getStack().getItem().isFood()
                            || s.getStack().getItem() == Items.GOLDEN_APPLE || s.getStack().getItem() == Items.BEEF) {
                            mc.getNetworkHandler().sendPacket(new ClickSlotC2SPacket(gcs.getScreenHandler().syncId, s.id, 1,
                                    SlotActionType.QUICK_MOVE, s.getStack(), (short) s.id));
                        }
                    }
                }
               
            }
            // mc.currentScreen.onClose();
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