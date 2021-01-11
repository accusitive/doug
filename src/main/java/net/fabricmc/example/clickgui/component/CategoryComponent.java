package net.fabricmc.example.clickgui.component;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.example.Client;
import net.fabricmc.example.DougMod;
import net.fabricmc.example.RenderUtils;
import net.fabricmc.example.Utility;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
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
        for (Utility m : getModsForCategory(category)) {
            ModuleComponent mc = new ModuleComponent(this.x, this.y);
            mc.setModule(m);
            mc.parent = this;
            moduleComponents.add(mc);
        }
        return this;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        MinecraftClient mc = MinecraftClient.getInstance();
        if (GLFW.glfwGetMouseButton(MinecraftClient.getInstance().getWindow().getHandle(), 0) == 0) {
            this.dragging = false;
        }

        if (dragging) {
            this.x = (start_of_click_x + mouseX);
            this.y = (start_of_click_y + mouseY);
        }
        String s = category.name();
        int w = mc.inGameHud.getFontRenderer().getWidth(s);
        // DrawableHelper.fill(matrixStack, this.x, this.y, this.x + width, this.y + this.height(),
        //         hovered(mouseX, mouseY) ? Client.panelSelectedColor() : Client.panelColor());
        if(!this.open) {
        RenderUtils.drawBorderedRect(matrixStack, this.x, this.y, this.x + this.width, this.y + this.height(), 1, Client.panelColor(), hovered(mouseX, mouseY) ? Client.panelSelectedColor() : -1);
        }else {
        RenderUtils.drawBorderedRectHoriTop(matrixStack, this.x, this.y, this.x + this.width, this.y + this.height(), 1, Client.panelColor(), hovered(mouseX, mouseY) ? Client.panelSelectedColor() : -1);

        }
        mc.inGameHud.getFontRenderer().drawWithShadow(matrixStack,s,
                this.x + (this.width - w) / 2, this.y + 3, this.dragging ? Client.mainColor() : -1);

        if (moduleComponents != null && this.open) {
            int y = height;
            for (ModuleComponent c : moduleComponents) {
                c.x = this.x;
                c.y = this.y + y;
                c.render(matrixStack, mouseX, mouseY);
                y += c.height();
            }
        }
        super.render(matrixStack, mouseX, mouseY);
    }

    public void mousePress(int button, int action) {
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
        ArrayList<Utility> temp = new ArrayList<>();
        if (DougMod.client.utilities() == null) {
            return temp;
        }
        for (Utility u : DougMod.client.utilities().values()) {

            if (u.category() == c) {
                temp.add(u);
            }
        }
        System.out.println(temp);
        return temp;
    }

}
