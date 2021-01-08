package net.fabricmc.example;

import java.util.HashMap;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.util.math.MatrixStack;

public class Utility {
    String name;
    int bind;
    Boolean state;
    Category category;
    public enum Category {
        Combat, Render, Movement, Player
    }
    public Utility(String name, int bind, Category category) {
        this.name = name;
        this.bind = bind;
        this.state = false;
        this.category = category;
    }

    public void init() {

    }

    public String name() {
        return this.name;
    }

    public int bind() {
        return this.bind;
    }

    public HashMap<String, Value> getSettings() {
        return new HashMap<>();
    }

    public Boolean enabled() {
        return this.state;
    }

    public void onEnable() {

    }

    public void onDisable() {

    }

    public void toggle() {
        System.out.printf("Module %s toggled.", this.name);
        if (this.enabled()) {
            this.state = false;
            this.onDisable();
        } else {
            this.state = true;
            this.onEnable();
        }

    }

	public void render(MatrixStack matrices, float tickDelta) {
    }
    public void tick(){

    }
    public String getName() {
        return  String.format("[%s] %s", GLFW.glfwGetKeyName(this.bind(), 1), this.name());
 
    }

	public Category category() {
		return this.category;
	}
}
