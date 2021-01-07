package net.fabricmc.example.clickgui.component;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.example.Utility;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.widget.ButtonListWidget.ButtonEntry;
import net.minecraft.client.util.math.MatrixStack;

public class ModuleComponent extends Component {
    public Utility utility;
    private boolean click;
    private boolean state = false;
    private int mouseX, mouseY;
    CategoryComponent parent;

    public ModuleComponent(int x, int y) {
        // this.categoryComponent = categoryComponent;
        super(x, y, 140, 14);
    }

    public ModuleComponent setModule(Utility utility) {
        this.utility = utility;
        return this;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        MinecraftClient.getInstance().textRenderer.drawWithShadow(matrixStack,
                String.format("pdrag: %s", parent.dragging), 100, click ? 110 : 100, -1);
        DrawableHelper.fill(matrixStack, this.x, this.y, this.x + width, this.y + this.height,
                hovered(mouseX, mouseY) ? 0xff38a9ff : 0xff777777);
        for (int i = 0; i < this.height; i++) {
            DrawableHelper.fill(matrixStack, this.x, this.y + i, this.x + 2, this.y + i + 1,
                    hovered(mouseX, mouseY) ? -1 : 0xff0000);
        }
        // matrixStack.rotate(new Quaternion(0, 0, mouseY, 0));
        MinecraftClient.getInstance().inGameHud.getFontRenderer().drawWithShadow(matrixStack, utility.getName(),
                this.x + 4, this.y + 4, utility.enabled() ? 0xff38a9ff : -1);
        super.render(matrixStack, mouseX, mouseY);
    }

    public void mousePress(int button, int action) {
        if (this.hovered(mouseX, mouseY)) {
            if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
                if (action == GLFW.GLFW_PRESS) {
                    this.utility.toggle();
                }
            }
        }
    }
}
