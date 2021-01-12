package net.fabricmc.example;

import java.util.ArrayList;
import java.util.HashMap;

import net.fabricmc.example.clickgui.ClickguiScreen;
import net.fabricmc.example.clickgui.component.CategoryComponent;
import net.fabricmc.example.utilities.Flight;
import net.fabricmc.example.utilities.Fullbright;
import net.fabricmc.example.utilities.AutoLoot;
import net.fabricmc.example.utilities.AutoSprint;
import net.fabricmc.example.utilities.Clickgui;
import net.fabricmc.example.utilities.Hud;
import net.fabricmc.example.utilities.Killaura;
import net.fabricmc.example.utilities.Velocity;
import net.fabricmc.example.utilities.TriggerBot;
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
        this.utilities.put("click_gui", new Clickgui());
        this.utilities.put("flight", new Flight());
        this.utilities.put("velocity", new Velocity());
        this.utilities.put("fullbright", new Fullbright());
        this.utilities.put("killaura", new Killaura());
        this.utilities.put("triggerbot", new TriggerBot());
        this.utilities.put("chams", new Utility("Chams", 0, net.fabricmc.example.Utility.Category.Render));
        this.utilities.put("autollot", new AutoLoot());

        // this needs to go at the bottom
        this.utilities.put("hud", new Hud());


        for (Utility u : this.utilities.values()) {
            u.init();
        }
    }

    public void keyPress(int key) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (utilities != null && mc.currentScreen == null) {
            for (Utility u : this.utilities.values()) {
                if (key == u.bind) {
                    u.toggle();
                }
            }
        }else if(mc.currentScreen instanceof ClickguiScreen){
            ClickguiScreen cgs = (ClickguiScreen) mc.currentScreen;
            for(CategoryComponent cg : cgs.categoryComponents) {
                cg.keyPress(key);
            }
        }

        
    }

    public void render(MatrixStack matrices, float tickDelta) {
        if (utilities != null) {
            utilities.values().stream().filter(u -> u.state).forEach(u -> u.render(matrices, tickDelta));
        }
    }
    public void onPreMotionUpdate(){
        if (utilities != null) {
            utilities.values().stream().filter(u -> u.state).forEach(u -> u.onPreMotionUpdate());
        }
    }
    public void onPostMotionUpdate(){
        if (utilities != null) {
            utilities.values().stream().filter(u -> u.state).forEach(u -> u.onPostMotionUpdate());
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
        MinecraftClient mc = MinecraftClient.getInstance();
        if(mc.currentScreen instanceof ClickguiScreen){
            ClickguiScreen cgs = (ClickguiScreen) mc.currentScreen;
            for(CategoryComponent cg : cgs.categoryComponents) {
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
        return 0xee000000;
    }
    public static int panelText() {
        return -1;
    }
    public static int panelSelectedColor() {
        return 0xeedddddd;
    }
    public static int mainColor() {
        return 0xff00cc66;
    }
}
