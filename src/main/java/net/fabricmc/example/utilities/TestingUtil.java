package net.fabricmc.example.utilities;

import java.util.HashMap;

import org.lwjgl.glfw.GLFW;
import net.fabricmc.example.Utility;
import net.fabricmc.example.value.BoolValue;
import net.fabricmc.example.value.ModeValue;
import net.fabricmc.example.value.NumberValue;
import net.fabricmc.example.value.Value;

public class TestingUtil extends Utility {
    HashMap<String, Value> settings = new HashMap<>();

    public TestingUtil() {
        super("TestingUtil", GLFW.GLFW_KEY_G, Category.Movement);
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
    public HashMap<String, Value> getSettings() {
        return settings;
    }
}
