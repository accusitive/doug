package net.dougteam.doug.client.utility.utilities;

import java.util.HashMap;

import com.darkmagician6.eventapi.EventTarget;

import org.apache.commons.lang3.event.EventUtils;
import org.lwjgl.glfw.GLFW;
// import org.lwjgl.util.vector.Matrix3f;
// import org.lwjgl.util.vector.Vector2f;
// import org.lwjgl.util.vector.Vector3f;

import net.dougteam.doug.client.utility.Utility;
import net.dougteam.doug.client.value.FloatValue;
import net.dougteam.doug.client.value.ModeValue;
import net.dougteam.doug.client.value.Value;
import net.dougteam.doug.events.TickEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public class Velocity extends Utility {

    public Velocity() {
        super("Velocity", GLFW.GLFW_KEY_0, Category.Combat);
        this.settings.put("CubecraftAmount", new FloatValue(85, 0, 100));
        this.settings.put("Mode", new ModeValue("Cubecraft", "Packet"));
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
    public void tick(TickEvent e) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if(((ModeValue)this.settings.get("Mode")).current() == "Cubecraft"){
            if(mc.player.hurtTime > 2) {
                float amt = ((FloatValue) this.settings.get("CubecraftAmount")).get();
                mc.player.setVelocity(mc.player.getVelocity().multiply(amt/100));
            }
        }
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