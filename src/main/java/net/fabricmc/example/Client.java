package net.fabricmc.example;

import java.util.ArrayList;
import java.util.HashMap;

import net.fabricmc.example.clickgui.ClickguiScreen;
import net.fabricmc.example.clickgui.component.CategoryComponent;
import net.fabricmc.example.utilities.Flight;
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
    ClickguiScreen clickguiScreen;
    public void init() {
        this.categoryComponents = new ArrayList<>();
        this.utilities = new HashMap<>();
        this.utilities.put("sprint", new AutoSprint());
        // this.utilities.put("sprint2", new AutoSprint());
        // this.utilities.put("sprint3", new AutoSprint());
        // this.utilities.put("sprint4", new AutoSprint());
        // this.utilities.put("sprint5", new AutoSprint());
        // this.utilities.put("sprint6", new AutoSprint());
        this.utilities.put("hud", new Hud());
        this.utilities.put("click_gui", new Clickgui());
        this.utilities.put("flight", new Flight());

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
    public ClickguiScreen getClickguiScreen () {
        return this.clickguiScreen;
    }
    public void setClickguiScreen (ClickguiScreen cgs) {
        this.clickguiScreen = cgs;
    }
    public static int panelColor() {
        return 0x80000000;
    }
    public static int panelText() {
        return -1;
    }
    public static int panelSelectedColor() {
        return 0x80ffffff;
    }
}
