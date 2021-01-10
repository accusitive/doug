package net.fabricmc.example.utilities;

import java.util.HashMap;
import java.util.Vector;

import org.lwjgl.glfw.GLFW;
// import org.lwjgl.util.vector.Matrix3f;
// import org.lwjgl.util.vector.Vector2f;
// import org.lwjgl.util.vector.Vector3f;

import net.fabricmc.example.Utility;
import net.fabricmc.example.value.BoolValue;
import net.fabricmc.example.value.ModeValue;
import net.fabricmc.example.value.NumberValue;
import net.fabricmc.example.value.Value;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3d;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;

public class Velocity extends Utility {
    HashMap<String, Value> settings = new HashMap<>();

    public Velocity() {
        super("Velocity", GLFW.GLFW_KEY_0, Category.Combat);
        this.settings.put("Amount", new NumberValue(85, 0, 100));

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
        if(mc.player.hurtTime > 2) {
            float amt = ((NumberValue) this.settings.get("Amount")).get();
            mc.player.setVelocity(mc.player.getVelocity().multiply(amt/100));
        }
        super.tick();
    }

    @Override
    public HashMap<String, Value> getSettings() {
        return settings;
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