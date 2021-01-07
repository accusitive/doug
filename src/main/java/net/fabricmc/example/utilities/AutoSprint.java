package net.fabricmc.example.utilities;


import org.lwjgl.glfw.GLFW;

import net.fabricmc.example.Utility;

public class AutoSprint extends Utility {
    public AutoSprint() {
        super("Sprint", GLFW.GLFW_KEY_P);
        
    }
    @Override
    public void init() {
        System.out.println("Sprint initialized");
        super.init();
    }
    
}
