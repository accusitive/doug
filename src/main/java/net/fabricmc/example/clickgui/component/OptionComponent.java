package net.fabricmc.example.clickgui.component;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.example.BoolValue;
import net.fabricmc.example.NumberValue;
import net.fabricmc.example.Utility;
import net.fabricmc.example.Value;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.widget.ButtonListWidget.ButtonEntry;
import net.minecraft.client.util.math.MatrixStack;

public class OptionComponent extends Component {
    public Value val;
    public String valname;
    private boolean click;
    private boolean state = false;
    private int mouseX, mouseY;
    ModuleComponent parent;

    public OptionComponent(int x, int y) {
        // this.categoryComponent = categoryComponent;
        super(x, y, 140, 14);
    }

    public OptionComponent setVal(String valname, Value  val) {
        this.valname = valname;
        this.val = val;
        return this;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;

        DrawableHelper.fill(matrixStack, this.x, this.y, this.x + width, this.y + this.height,
                hovered(mouseX, mouseY) ? 0x8038a9ff : 0x80777777);
        if(this.val instanceof BoolValue) {
            BoolValue boolValue = (BoolValue) this.val;
            MinecraftClient.getInstance().inGameHud.getFontRenderer().drawWithShadow(matrixStack,String.format("%s: %s", this.valname, boolValue.get()), this.x + 4, this.y + 4, -1);
        }else if (this.val instanceof NumberValue) {
            NumberValue numValue = (NumberValue) this.val;
            matrixStack.push();
            matrixStack.scale(0.80000f, 0.80000f, 0.80000f);
            MinecraftClient.getInstance().inGameHud.getFontRenderer().drawWithShadow(matrixStack,String.format("%s: %.1f", this.valname, numValue.get()), (this.x + 4) * 1.25f, (this.y + 3) * 1.25f, -1);
            matrixStack.pop();
            float zidth = map(numValue.get(), numValue.min(), numValue.max(), 0, this.width);
            DrawableHelper.fill(matrixStack, this.x, this.y + (this.height - 2), this.x + (int) zidth, this.y + height, -1);
            if(hovered(mouseX, mouseY)) {
                if(GLFW.glfwGetMouseButton(MinecraftClient.getInstance().getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == 1) {
                    NumberValue nv = (NumberValue) this.val;
                    float num = map(this.mouseX, this.x, this.x + this.width, nv.min()-0.1f, nv.max()+0.1f);
                    nv.set(num);
                }
            }
        }
        super.render(matrixStack, mouseX, mouseY);
    }
    private float map(float n, float start1, float stop1, float start2, float stop2) {
        return ((n-start1)/(stop1-start1)) * (stop2-start2) + start2;
    }
    public void mousePress(int button, int action) {
        if (this.hovered(mouseX, mouseY)) {
            if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
                if (action == GLFW.GLFW_PRESS) {
                    if(this.val instanceof BoolValue) {
                        BoolValue boolValue = (BoolValue) this.val;
                        boolValue.set(!boolValue.get());
                    }
                }
            }
        }
    }
}
