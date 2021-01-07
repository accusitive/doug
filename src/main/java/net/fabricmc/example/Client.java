package net.fabricmc.example;

import java.util.HashMap;

import net.fabricmc.example.utilities.AutoSprint;
import net.fabricmc.example.utilities.Hud;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public class Client {
    String name;
    String version;
    HashMap<String, Utility> utilities;

    public void init() {
        this.utilities = new HashMap<>();
        this.utilities.put("sprint", new AutoSprint());
        this.utilities.put("hud", new Hud());
        
        for (Utility u : this.utilities.values()) {
            u.init();
        }
    }

    public void keyPress(int key) {
        if (utilities != null) {
            for (Utility u : this.utilities.values()) {
                if (key == u.bind) {
                    u.toggle();
                }
            }
        }
    }

    public void render(MatrixStack matrices, float tickDelta) {
        if (utilities != null) {
            utilities.values().stream().filter(u -> u.state).forEach(u -> u.render(matrices, tickDelta));
        }
    }
    public HashMap<String, Utility> utilities() {
        return this.utilities;
    }

	public void tick() {
        if (utilities != null) {
            utilities.values().stream().filter(u -> u.state).forEach(u -> u. tick());
        }
    }
}
