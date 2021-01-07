package net.fabricmc.example.clickgui.component;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.example.DougMod;
import net.fabricmc.example.Utility;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Util;

import java.awt.event.MouseListener;
import java.util.ArrayList;

public class CategoryComponent extends Component {
    int start_of_click_x = 0;
    int start_of_click_y = 0;
    boolean open;
    boolean dragging;
    boolean bingus;
    public Utility.Category category;
    public ArrayList<ModuleComponent> moduleComponents;
    int mouseX, mouseY;

    public CategoryComponent(int x, int y) {
        super(x, y, 140, 14);
        this.moduleComponents = new ArrayList<>();
        this.open = false;
    }

    public CategoryComponent setCategory(Utility.Category category) {
        this.category = category;
        System.out.println("setting category to");
        System.out.println(category);
        for (Utility m : getModsForCategory(category)) {
            System.out.println(m);
            System.out.println("About to add module compoenents");
            ModuleComponent mc = new ModuleComponent(this.x, this.y);
            mc.setModule(m);
            mc.parent = this;
            System.out.println("About to add to modulecOmpoemnets array");
            moduleComponents.add(mc);
            System.out.println("Adde to modulecompoenents array");
        }
        return this;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY) {

        this.mouseX = mouseX;
        this.mouseY = mouseY;

        if (GLFW.glfwGetMouseButton(MinecraftClient.getInstance().getWindow().getHandle(), 0) == 0) {
            this.dragging = false;
        }

        if (dragging) {
            this.x = (start_of_click_x + mouseX);
            this.y = (start_of_click_y + mouseY);
        }

        DrawableHelper.fill(matrixStack, this.x, this.y, this.x + width, this.y + this.height,
                hovered(mouseX, mouseY) ? 0x8038a9ff : 0x80777777);
        MinecraftClient.getInstance().inGameHud.getFontRenderer().drawWithShadow(matrixStack, category.name(),
                this.x + 4, this.y + 3, hovered(mouseX, mouseY) ? 0xFF0000 : -1);

        if (moduleComponents != null && this.open) {
            int y = height;
            for (ModuleComponent c : moduleComponents) {
                c.x = this.x;
                c.y = this.y + y;
                c.render(matrixStack, mouseX, mouseY);
                y += c.height;
            }
        }
        super.render(matrixStack, mouseX, mouseY);
    }

    public void mousePress(int button, int action) {
        System.out.println("mouse press");
        if (this.hovered(this.mouseX, this.mouseY)) {
            if (button == GLFW.GLFW_MOUSE_BUTTON_RIGHT && action == GLFW.GLFW_PRESS) {
                this.open = !this.open;
            }
            start_of_click_x = this.x - this.mouseX;
            start_of_click_y = this.y - this.mouseY;
            dragging = true;
        } else {
            dragging = false;
        }
        for (ModuleComponent mb : moduleComponents) {
            mb.mousePress(button, action);
        }
    }

    public ArrayList<Utility> getModsForCategory(Utility.Category c) {
        System.out.println("getting mods for category");
        ArrayList<Utility> temp = new ArrayList<>();
        if (DougMod.client.utilities() == null) {
            return temp;
        }
        for (Utility u : DougMod.client.utilities().values()) {
            System.out.println("Utility u in loop");
            System.out.println(u);
            System.out.println(c);

            if (u.category() == c) {
                System.out.println("Category is c");
                temp.add(u);
            } else {
                System.out.printf("Category %s is not %s\n", c, u.category());
            }
        }
        System.out.println(temp);
        return temp;
    }

}
