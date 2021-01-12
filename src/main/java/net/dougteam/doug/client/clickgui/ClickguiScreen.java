package net.dougteam.doug.client.clickgui;

import java.util.ArrayList;

import net.dougteam.doug.client.clickgui.component.CategoryComponent;
import net.dougteam.doug.client.utility.Utility;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

public class ClickguiScreen extends Screen {
    public ArrayList<CategoryComponent> categoryComponents = new ArrayList<>();

    public ClickguiScreen() {
        super(new LiteralText("Clickgui"));
        for (Utility.Category c : Utility.Category.values()) {
            CategoryComponent cg = new CategoryComponent(0 + (c.ordinal() * 140), 0);
            cg.setCategory(c);
            categoryComponents.add(cg);
        }
    }

    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        if (categoryComponents != null) {
            categoryComponents.forEach(cc -> cc.render(matrixStack, mouseX, mouseY));
        }
    }
}
