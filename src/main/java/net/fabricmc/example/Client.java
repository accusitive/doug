package net.fabricmc.example;

import java.util.ArrayList;
import java.util.HashMap;

import net.fabricmc.example.clickgui.ClickguiScreen;
import net.fabricmc.example.clickgui.component.CategoryComponent;
import net.fabricmc.example.utilities.AutoSprint;
import net.fabricmc.example.utilities.Clickgui;
import net.fabricmc.example.utilities.Hud;
import net.fabricmc.loom.providers.MinecraftAssetsProvider;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public class Client {
    String name;
    String version;
    HashMap<String, Utility> utilities;
    ArrayList<CategoryComponent> categoryComponents;

    public void init() {
        this.categoryComponents = new ArrayList<>();
        this.utilities = new HashMap<>();
        this.utilities.put("sprint", new AutoSprint());
        this.utilities.put("hud", new Hud());
        this.utilities.put("click_gui", new Clickgui());
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
            utilities.values().stream().filter(u -> u.state).forEach(u -> u.tick());
        }
    }

    public ArrayList<CategoryComponent> categoryComponents() {
        return this.categoryComponents;
    }

	public void mousePress(int button, int action) {
        System.out.println(this.categoryComponents());
        if(MinecraftClient.getInstance().currentScreen instanceof ClickguiScreen){
            ClickguiScreen cgs = (ClickguiScreen) MinecraftClient.getInstance().currentScreen;

            for(CategoryComponent cg : cgs.categoryComponents) {
                System.out.println(cg);
                cg.mousePress(button, action);
            }
        }
        
	}
}
