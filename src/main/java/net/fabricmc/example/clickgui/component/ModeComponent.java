package net.fabricmc.example.clickgui.component;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.example.Client;
import net.fabricmc.example.RenderUtils;
import net.fabricmc.example.value.ModeValue;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

public class ModeComponent extends Component {
    String tag;
    ModeValue mode;
    int mouseX, mouseY;

    public ModeComponent(int x, int y) {
        super(x, y, 140, 14);
    }

    public void setMode(ModeValue mode) {
        this.mode = mode;
    }

    public void setTag(String t) {
        this.tag = t;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;

        MinecraftClient mc = MinecraftClient.getInstance();
        boolean enabled = this.mode.current() == this.tag;
        RenderUtils.drawBorderedRectHori(matrixStack, this.x+1, this.y, this.x + this.width, this.y + this.height(), 1,
                this.hovered(mouseX, mouseY) ? Client.panelSelectedColor() : Client.panelColor(), -1);
        DrawableHelper.drawStringWithShadow(matrixStack, mc.textRenderer, this.tag, this.x + 4, this.y + 4,
                enabled ? Client.mainColor() : Client.panelText());
        super.render(matrixStack, mouseX, mouseY);
    }

    public void mousePress(int button, int action) {
        if (this.hovered(mouseX, mouseY)) {
            if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
                this.mode.set(this.mode.tags().indexOf(this.tag));
            }
        }
    }

}
