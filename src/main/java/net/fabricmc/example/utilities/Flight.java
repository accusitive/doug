package net.fabricmc.example.utilities;

import java.util.HashMap;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.example.Utility;
import net.fabricmc.example.value.NumberValue;
import net.fabricmc.example.value.Value;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.data.client.model.BlockStateSupplier;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class Flight extends Utility {
    HashMap<String, Value> settings = new HashMap<>();

    public Flight() {
        super("Flight", GLFW.GLFW_KEY_G, Category.Movement);
        this.settings.put("Speed", new NumberValue(5, 1, 10));
    }
    @Override
    public void tick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        mc.player.addVelocity(0, -mc.player.getVelocity().y, 0);

        super.tick();
    }
    @Override
    public void onDisable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        mc.player.abilities.flying = false;
        mc.player.abilities.setFlySpeed(0.1f);

        super.onDisable();
    }
    @Override
    public HashMap<String, Value> getSettings() {
        return settings;
    }
}
