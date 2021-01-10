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

public class TestingUtil extends Utility {
    HashMap<String, Value> settings = new HashMap<>();

    public TestingUtil() {
        super("TestingUtil", GLFW.GLFW_KEY_C, Category.Movement);
        this.settings.put("SomeNumber1", new NumberValue(5, 1, 10));
        this.settings.put("SomeNumber2", new NumberValue(5, 1, 10));
        this.settings.put("SomeNumber3", new NumberValue(5, 1, 10));
        this.settings.put("SomeBool1", new BoolValue(true));
        this.settings.put("SomeBool2", new BoolValue(false));
        this.settings.put("SomeBool3", new BoolValue(true));
        this.settings.put("SomeMode1", new ModeValue("1", "2", "Three", "Four (4)"));
        this.settings.put("SomeMode2", new ModeValue("beans", "doug", "client", "on top"));

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
        if(mc.player.hurtTime == 7) {
            mc.player.setVelocity(mc.player.getVelocity().multiply(0.87f));
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