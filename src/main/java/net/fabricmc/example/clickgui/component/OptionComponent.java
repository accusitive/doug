package net.fabricmc.example.clickgui.component;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.example.value.BoolValue;
import net.fabricmc.example.Client;
import net.fabricmc.example.value.ModeValue;
import net.fabricmc.example.value.NumberValue;
import net.fabricmc.example.value.Value;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

public class OptionComponent extends Component {
    public Value val;
    public String valname;
    private int mouseX, mouseY;
    boolean open;
    ArrayList<ModeComponent> modeComponents;

    ModuleComponent parent;

    public OptionComponent(int x, int y) {
        super(x, y, 140, 14);
        this.modeComponents = new ArrayList<>();
    }

    public OptionComponent setVal(String valname, Value val) {
        this.valname = valname;
        this.val = val;
        if(this.val instanceof ModeValue) {
            for(String s: ((ModeValue)this.val).tags()) {
                ModeComponent mc = new ModeComponent(this.x, this.y);
                mc.setMode((ModeValue) this.val);
                mc.setTag(s);
                this.modeComponents.add(mc);
            }
        }
        return this;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        MinecraftClient mc = MinecraftClient.getInstance();
        TextRenderer fr = mc.inGameHud.getFontRenderer();
        DrawableHelper.fill(matrixStack, this.x, this.y, this.x + width, this.y + this.height(),
                hovered(mouseX, mouseY) ? Client.panelSelectedColor() : Client.panelColor());
        if (this.val instanceof BoolValue) {
            BoolValue boolValue = (BoolValue) this.val;
            String s = String.format("%s: %s", this.valname, boolValue.get() ? "§a✔" : "§c✖");
            int w = fr.getWidth(s);
            fr.drawWithShadow(matrixStack, s, (this.x + (this.width - w) / 2), this.y + 3, -1);
        } else if (this.val instanceof NumberValue) {
            NumberValue numValue = (NumberValue) this.val;
            matrixStack.push();
            matrixStack.scale(0.80000f, 0.80000f, 0.80000f);
            String s = String.format("%s: %.1f", this.valname, numValue.get());
            int w = fr.getWidth(s);
            fr.drawWithShadow(matrixStack, s, (this.x + (this.width - w) / 2) * 1.25f, (this.y + 3) * 1.25f, -1);
            matrixStack.pop();
            float zidth = map(numValue.get(), numValue.min(), numValue.max(), 0, this.width);
            DrawableHelper.fill(matrixStack, this.x, this.y + (this.height() - 2), this.x + (int) zidth, this.y + height,
                    -1);
            if (hovered(mouseX, mouseY)) {
                if (GLFW.glfwGetMouseButton(mc.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == 1) {
                    NumberValue nv = (NumberValue) this.val;
                    float num = map(this.mouseX, this.x, this.x + this.width, nv.min() - 0.1f, nv.max() + 0.1f);
                    nv.set(num);
                }
            }
        }else if(this.val instanceof ModeValue) {
            ModeValue mv = (ModeValue) this.val;
            String s = String.format("%s: %s", valname, mv.current());
            int w = fr.getWidth(s);
            fr.drawWithShadow(matrixStack, s, (this.x + (this.width - w) / 2), (this.y + 3), -1);
        }
        int y = 0;
        if(this.open){
            for(ModeComponent mc_ : this.modeComponents) {
                mc_.render(matrixStack, mouseX, mouseY);
                mc_.x = this.x + this.width;
                mc_.y = this.y + y;
                y += mc_.height();
            }
        }

       
        super.render(matrixStack, mouseX, mouseY);
    }

    private float map(float n, float start1, float stop1, float start2, float stop2) {
        return ((n - start1) / (stop1 - start1)) * (stop2 - start2) + start2;
    }

    public void mousePress(int button, int action) {
        if (this.hovered(mouseX, mouseY)) {
            if (action == GLFW.GLFW_PRESS) {
            if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
                    if (this.val instanceof BoolValue) {
                        BoolValue boolValue = (BoolValue) this.val;
                        boolValue.set(!boolValue.get());
                    }else if(this.val instanceof ModeValue) {
                        ModeValue mv = (ModeValue) this.val; 
                        mv.set((mv.currentTag() + 1) % (mv.tagsLen()));
                    }
                }else if (button == GLFW.GLFW_MOUSE_BUTTON_RIGHT) {
                    this.open = !this.open;
                }
            }
        }
        for (ModeComponent mc : this.modeComponents) {
            mc.mousePress(button, action);
        }
    }
    @Override
    public int height() {
        return 14;   
    }
}
